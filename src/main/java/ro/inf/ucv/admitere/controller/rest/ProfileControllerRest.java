package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.exceptions.FieldValidationException;
import ro.inf.ucv.admitere.service.ProfileService;
import ro.inf.ucv.admitere.wrapper.ProfileWrapper;
import ro.inf.ucv.admitere.wrapper.Response;

@RestController
@RequestMapping("/profile")
public class ProfileControllerRest extends BaseController {

	@Autowired
	protected ProfileService profileService;

	@GetMapping
	private ResponseEntity<Response> getProfile(Principal principal) throws Exception {
		Profile profile = userService.getProfile(principal);
		if (profile != null && profile.getId() != null) {
			return new ResponseEntity<Response>(new Response(profile), HttpStatus.OK);
		}
		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> saveProfile(@Valid @RequestBody Profile profile, BindingResult bindingResult,
			Principal principal) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}

		Profile savedProfile = this.profileService.save(profile, principal);
		if (savedProfile != null) {
			return new ResponseEntity<Response>(HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/data")
	private ResponseEntity<ProfileWrapper> getProfileDetails() throws Exception {
		return new ResponseEntity<ProfileWrapper>(createProfileWrapper(), HttpStatus.OK);
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(this.profileValidator);
	}
}
