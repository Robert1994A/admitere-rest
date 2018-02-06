package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.exceptions.FieldValidationException;

import ro.inf.ucv.admitere.wrapper.ProfileWrapper;
import ro.inf.ucv.admitere.wrapper.Response;

@Controller
public class ProfileRestController extends BaseController {

	@GetMapping("/profile")
	private ResponseEntity<Profile> profile(Principal principal) throws Exception {
		Profile profile = userService.getProfile(principal);
		if (profile == null) {
			return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}

	@PostMapping(value = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> saveProfile(@Valid @RequestBody Profile profile, BindingResult bindingResult,
			Principal principal) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}
		this.profileService.save(profile, principal);
		return new ResponseEntity<Response>(HttpStatus.OK);
	}

	@GetMapping("/profile/data")
	private ResponseEntity<ProfileWrapper> getProfileDetails() throws Exception {
		return new ResponseEntity<ProfileWrapper>(createProfileWrapper(), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	protected ProfileWrapper createProfileWrapper() {
		ProfileWrapper profileWrapper = new ProfileWrapper();
		profileWrapper.setCitizenships(citizenshipService.findAll());
		profileWrapper.setEthnicities(ethnicityService.findAll());
		profileWrapper.setFamilySituations(familySituationService.findAll());
		profileWrapper.setGenders(genderService.findAll());
		profileWrapper.setMaritalStatus(maritalStatusService.findAll());
		profileWrapper.setMedicalConditions(medicalConditionService.findAll());
		profileWrapper.setReligions(religionService.findAll());
		profileWrapper.setSocialSituations(socialSituationService.findAll());

		return profileWrapper;
	}
}
