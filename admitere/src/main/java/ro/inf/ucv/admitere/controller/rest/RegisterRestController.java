package ro.inf.ucv.admitere.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.utils.URLUtils;
import ro.inf.ucv.admitere.wrapper.Response;

@Controller
public class RegisterRestController extends BaseController {

	@GetMapping("/get_captcha_site_key")
	public ResponseEntity<Response> registerGET() {
		return new ResponseEntity<Response>(new Response(null, HttpStatus.OK, configurationUtils.getCaptchaSiteKey()),
				HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Response> registerPOST(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ResponseEntity<Response>(new Response(null, null, result.getFieldError()),
					HttpStatus.BAD_REQUEST);
		} else {
			try {
				String responseToken = request.getParameter("g-recaptcha-response");
				captchaService.processResponse(responseToken);
				Role roleUser = roleService.findByName("USER");
				List<Role> roles = new ArrayList<>();
				roles.add(roleUser);
				String registerToken = stringGenerator.getRandomString();
				user.setEnabled(false);
				user.setCreationDate(new Date());
				user.setRecoverPaswordToken(registerToken);
				user.setRegisterToken(registerToken);
				user.setRoles(roles);
				user.setPassword(encoder.encode(user.getPassword()));
				userService.save(user);
				HashMap<String, String> velocityContext = new HashMap<>();
				velocityContext.put("linkToValidate",
						URLUtils.getBaseUrl(request) + "/validateAccount?registerToken=" + registerToken);
				mailer.sendMail(Arrays.asList(user.getEmail()), null, "Validate account", "mailValidateAccount.vm",
						velocityContext);
			} catch (Exception e) {
				throw e;
			}
		}

		return new ResponseEntity<Response>(HttpStatus.OK);
	}
}
