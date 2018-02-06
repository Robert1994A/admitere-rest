package ro.inf.ucv.admitere.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationUtils {

	public static final String DATE_FORMAT = "dd-MMMM-yyyy";

	@Value("${captcha.siteKey}")
	private String captchaSiteKey;

	@Value("${captcha.secretKey}")
	private String captchaSecretKey;

	@Value("${file.maxUploadSize}")
	private Long maxUploadSize;

	@Value("${users.perPage:25}")
	private Integer usersPerPage = 25;

	@Value("${attempts.maxAttempts}")
	private Integer maxAttempts;

	@Value("${attempts.interval}")
	private Integer attemptsInterval;

	@Value("${pagination.perPage:25}")
	private Integer paginationPerPage = 25;

	public String getCaptchaSiteKey() {
		return captchaSiteKey;
	}

	public String getCaptchaSecretKey() {
		return captchaSecretKey;
	}

	public Long getMaxUploadSize() {
		return maxUploadSize;
	}

	public Integer getUsersPerPage() {
		return usersPerPage;
	}

	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	public Integer getAttemptsInterval() {
		return attemptsInterval;
	}

	public Integer getPaginationPerPage() {
		return paginationPerPage;
	}
}
