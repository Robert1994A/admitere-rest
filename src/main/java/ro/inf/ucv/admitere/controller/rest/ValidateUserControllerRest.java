package ro.inf.ucv.admitere.controller.rest;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFoundException;
import ro.inf.ucv.admitere.wrapper.Response;

@RestController
public class ValidateUserControllerRest extends BaseController {

	private static final Logger logger = Logger.getLogger(ValidateUserControllerRest.class);

	@GetMapping("/validateAccount")
	public ResponseEntity<Response> validateAccount(@RequestParam("registerToken") String registerToken)
			throws UserNotFoundException {
		try {
			User user = userService.findByRegisterToken(registerToken);
			if (user == null) {
				return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
			}
			user.setEnabled(true);
			user.setRegisterToken(securityUtils.getEncodedRandomString());
			userService.save(user, true);
			HashMap<String, String> context = new HashMap<>();
			context.put("username", user.getUsername());
			mailer.sendMail(Arrays.asList(user.getEmail()), null, "Account validated succesfully.",
					"mailAcountValidated.vm", context);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Try to validate user account: " + e);
		}
		return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}