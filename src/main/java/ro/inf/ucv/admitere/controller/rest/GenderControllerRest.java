package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.exceptions.FieldValidationException;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@RestController
public class GenderControllerRest extends BaseController {

	@GetMapping("/genders")
	public ResponseEntity<Response> getGenders(@ModelAttribute("searchModel") SearchModel searchModel)
			throws Exception {
		Page<Gender> genders = this.genderService.inteligentPagination(searchModel);
		if (genders != null && genders.hasContent()) {
			return new ResponseEntity<Response>(new Response(genders), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/genders")
	public ResponseEntity<Response> saveGender(@Valid @RequestBody Gender gender, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}

		Gender foundedGender = this.genderService.findOne(gender.getId());
		if (foundedGender != null) {
			return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
		}

		Gender savedGender = this.genderService.save(gender, true);
		if (savedGender != null) {
			return new ResponseEntity<Response>(HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/genders")
	public ResponseEntity<Response> updateGender(@Valid @RequestBody Gender gender, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}

		Gender savedGender = this.genderService.save(gender, true);
		if (savedGender != null) {
			return new ResponseEntity<Response>(HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/genders/{genderId}")
	public ResponseEntity<Response> getGenderById(@PathVariable("genderId") Integer genderId) throws Exception {
		Gender gender = this.genderService.findOne(genderId);
		if (gender != null) {
			return new ResponseEntity<Response>(new Response(gender), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/genders")
	public ResponseEntity<Response> deleteGenderByIds(@RequestBody List<Integer> genderIds) throws Exception {
		if (genderIds != null && !genderIds.isEmpty()) {
			Response response = new Response(null);
			response.setWarnings(this.genderService.deleteByIds(genderIds));
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

}