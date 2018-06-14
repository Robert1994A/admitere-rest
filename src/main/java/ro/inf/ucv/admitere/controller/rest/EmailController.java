package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.exceptions.FieldValidationException;
import ro.inf.ucv.admitere.wrapper.Email;
import ro.inf.ucv.admitere.wrapper.Response;

@RestController
@RequestMapping("email")
public class EmailController extends BaseController {

	@PostMapping
	public ResponseEntity<Response> sendEmail(@Valid @RequestBody Email email, BindingResult bindingResult,
			Principal principal) throws FieldValidationException {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}

		if (this.mailer.sendMail(email, principal)) {
			return new ResponseEntity<Response>(HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
