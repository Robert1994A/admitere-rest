package ro.inf.ucv.admitere.controller.rest;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFoundException;
import ro.inf.ucv.admitere.utils.URLUtils;
import ro.inf.ucv.admitere.wrapper.Response;

@Controller
public class RecoverPasswordControllerRest extends BaseController {

	private static final Logger logger = Logger.getLogger(RecoverPasswordControllerRest.class);

	@PostMapping("/recover")
	public ResponseEntity<Response> recoverPOST(@RequestParam(value = "username") String username,
			HttpServletRequest request) throws UserNotFoundException {
		try {
			String responseToken = request.getParameter("g-recaptcha-response");
			captchaService.processResponse(responseToken);
			User user = userService.findByUsernameOrEmail(username, username);
			String recoverToken = user.getRecoverPaswordToken();
			user.setEnabled(false);
			userService.save(user, true);
			HashMap<String, String> velocityContext = new HashMap<>();
			velocityContext.put("linkToRecover",
					URLUtils.getBaseUrl(request) + "/recoverPassword?recoverToken=" + recoverToken);
			mailer.sendMail(Arrays.asList(user.getEmail()), null, "Recover Password","mailRecoverPassword.vm",
					velocityContext);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to recover password: " + e);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/recoverPassword")
	public ResponseEntity<Response> recoverPasswordPOST(@RequestParam(value = "recoverToken") String recoverToken,
			@RequestParam(value = "newPassword") String newPassword,
			@RequestParam(value = "retypeNewPassword") String retypeNewPassword) {
		try {
			if (newPassword.equals(retypeNewPassword)) {
				User user = userService.findByRecoverPaswordToken(recoverToken);
				if (user != null) {
					user.setPassword(securityUtils.encode(newPassword));
					user.setRecoverPaswordToken(securityUtils.getEncodedRandomString());
					user.setEnabled(true);
					userService.save(user, true);
					return new ResponseEntity<Response>(HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to recover password: " + e);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}
}
