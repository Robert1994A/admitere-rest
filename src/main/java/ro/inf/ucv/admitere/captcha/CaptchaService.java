package ro.inf.ucv.admitere.captcha;

import java.net.URI;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import ro.inf.ucv.admitere.captcha.GoogleResponse;
import ro.inf.ucv.admitere.captcha.AttemptsService;
import ro.inf.ucv.admitere.exceptions.ReCaptchaInvalidException;
import ro.inf.ucv.admitere.exceptions.ReCaptchaUnavailableException;

@Service("captchaService")
public class CaptchaService {

	@Value("${captcha.secretKey}")
	String captchaSecretKey;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private AttemptsService attemptService;

	private RestOperations restTemplate = new RestTemplate();

	private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

	public void processResponse(final String response) {

		if (attemptService.isBlocked(getClientIP())) {
			throw new ReCaptchaInvalidException("Client exceeded maximum number of failed attempts");
		}

		if (!responseSanityCheck(response)) {
			throw new ReCaptchaInvalidException("Response contains invalid characters");
		}

		final URI verifyUri = URI.create(
				String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
						captchaSecretKey, response, getClientIP()));
		try {
			final GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);

			if (!googleResponse.isSuccess()) {
				if (googleResponse.hasClientError()) {
					attemptService.reCaptchaFailed(getClientIP());
				}
				throw new ReCaptchaInvalidException("reCaptcha was not successfully validated");
			}
		} catch (RestClientException rce) {
			throw new ReCaptchaUnavailableException("Registration unavailable at this time.  Please try again later.",
					rce);
		}
		attemptService.reCaptchaSucceeded(getClientIP());
	}

	private boolean responseSanityCheck(final String response) {
		return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
	}

	private String getClientIP() {
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
