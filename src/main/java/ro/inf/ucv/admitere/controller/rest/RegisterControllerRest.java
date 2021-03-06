package ro.inf.ucv.admitere.controller.rest;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.utils.URLUtils;
import ro.inf.ucv.admitere.wrapper.RegisterUserForm;
import ro.inf.ucv.admitere.wrapper.Response;

@RestController
public class RegisterControllerRest extends BaseController {

	@GetMapping("/get_captcha_site_key")
	public ResponseEntity<Response> registerGET() {
		return new ResponseEntity<Response>(new Response((Object) configurationUtils.getCaptchaSiteKey()),
				HttpStatus.OK);
	}

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registerPOST(@Valid @RequestBody RegisterUserForm registerUserForm,
			BindingResult result, HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			return new ResponseEntity<Response>(new Response(result.getFieldError()), HttpStatus.BAD_REQUEST);
		} else {
			try {
				// String responseToken = request.getParameter("g-recaptcha-response");
				// captchaService.processResponse(responseToken);
				Role roleUser = roleService.findByName("ROLE_USER");
				List<Role> roles = new ArrayList<>();
				roles.add(roleUser);
				String registerToken = securityUtils.getEncodedRandomString();
				String recoverPasswordToken = securityUtils.getEncodedRandomString();
				User user = new User();
				user.setUsername(registerUserForm.getUsername());
				user.setEmail(registerUserForm.getEmail());
				user.setPassword(securityUtils.encode(registerUserForm.getPassword()));
				user.setPhoneNumber(registerUserForm.getPhoneNumber());
				user.setEnabled(false);
				user.setCreationDate(new Date());
				user.setRecoverPaswordToken(recoverPasswordToken);
				user.setRegisterToken(registerToken);
				user.setRoles(roles);

				HashMap<String, String> velocityContext = new HashMap<>();
				velocityContext.put("linkToValidate",
						URLUtils.getBaseUrl(request) + "/validateAccount?registerToken="
								+ URLEncoder.encode(registerToken, "UTF-8") + "&email="
								+ URLEncoder.encode(user.getEmail(), "UTF-8"));
				boolean emailSend = this.mailer.sendMail(Arrays.asList(user.getEmail()), null, "Validate account",
						"mailValidateAccount.vm", velocityContext);
				if (emailSend) {
					this.userService.save(user, true);
				} else {
					return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch (Exception e) {
				throw e;
			}
		}

		return new ResponseEntity<Response>(HttpStatus.OK);
	}
}
