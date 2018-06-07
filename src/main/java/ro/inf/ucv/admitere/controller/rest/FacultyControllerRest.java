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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Faculty;
import ro.inf.ucv.admitere.exceptions.FieldValidationException;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@RestController
@RequestMapping("/faculties")
public class FacultyControllerRest extends BaseController {

	@GetMapping
	public ResponseEntity<Response> getFaculties(@ModelAttribute("searchModel") SearchModel searchModel)
			throws Exception {
		Page<Faculty> faculties = this.facultyService.inteligentPagination(searchModel);
		if (faculties != null && faculties.hasContent()) {
			return new ResponseEntity<Response>(new Response(faculties), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Response> saveFaculty(@Valid @RequestBody Faculty faculty, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}

		Faculty foundedFaculty = this.facultyService.findOne(faculty.getId());
		if (foundedFaculty != null) {
			return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
		}

		Faculty savedFaculty = this.facultyService.save(faculty, true);
		if (savedFaculty != null) {
			return new ResponseEntity<Response>(HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping
	public ResponseEntity<Response> updateFaculty(@Valid @RequestBody Faculty faculty, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}

		int facultyId = faculty.getId();
		Faculty foundedFaculty = this.facultyService.findOne(facultyId);
		if (foundedFaculty != null) {
			faculty.setCreationDate(foundedFaculty.getCreationDate());
			Faculty savedFacullty = this.facultyService.save(faculty, true);
			if (savedFacullty != null) {
				return new ResponseEntity<Response>(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{facultyId}")
	public ResponseEntity<Response> getFacultyById(@PathVariable("facultyId") Integer facultyId) throws Exception {
		Faculty faculty = this.facultyService.findOne(facultyId);
		if (faculty != null) {
			return new ResponseEntity<Response>(new Response(faculty), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping
	public ResponseEntity<Response> deleteFacultyByIds(@RequestBody List<Integer> facultyIds) throws Exception {
		if (facultyIds != null && !facultyIds.isEmpty()) {
			Response response = new Response(null);
			response.setWarnings(this.facultyService.deleteByIds(facultyIds));
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}
}