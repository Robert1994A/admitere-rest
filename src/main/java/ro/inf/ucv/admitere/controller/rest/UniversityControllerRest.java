package ro.inf.ucv.admitere.controller.rest;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.AdmissionSession;
import ro.inf.ucv.admitere.entity.Faculty;
import ro.inf.ucv.admitere.entity.FacultyDomainNomenclature;
import ro.inf.ucv.admitere.entity.FacultySpecializationNomenclature;
import ro.inf.ucv.admitere.entity.University;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@RestController
public class UniversityControllerRest extends BaseController {

	@GetMapping("/universities")
	public ResponseEntity<Response> getUniversities(@ModelAttribute("searchModel") SearchModel searchModel)
			throws Exception {
		Page<University> universities = this.universityService.inteligentPagination(searchModel);
		if (universities != null && universities.hasContent()) {
			return new ResponseEntity<Response>(new Response(universities), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/universities/{universityId}")
	public ResponseEntity<Response> getUniversityById(@PathVariable("universityId") Integer universityId)
			throws Exception {
		University university = this.universityService.findById(universityId);
		if (university != null) {
			return new ResponseEntity<Response>(new Response(university), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/universities/{id}/faculties")
	public ResponseEntity<Response> getFaculties(@PathVariable("id") Integer universityId) throws Exception {
		List<Faculty> faculties = this.universityService.getFaculties(universityId);
		if (faculties != null && !faculties.isEmpty()) {
			return new ResponseEntity<Response>(new Response(faculties), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/universities/{universityId}/faculties/{facultyId}")
	public ResponseEntity<Response> getFacultyById(@PathVariable("universityId") Integer universityId,
			@PathVariable("facultyId") Integer facultyId) throws Exception {
		Faculty faculty = this.universityService.getFacultyByUniversityAndFacultyId(universityId, facultyId);
		if (faculty != null) {
			return new ResponseEntity<Response>(new Response(faculty), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/universities/{universityId}/faculties/{facultyId}/domains")
	public ResponseEntity<Response> getDomainsByFaculty(@PathVariable("universityId") Integer universityId,
			@PathVariable("facultyId") Integer facultyId) throws Exception {
		List<FacultyDomainNomenclature> facultyDomainNomenclatures = this.universityService.getFacultyDomains(universityId,
				facultyId);
		if (facultyDomainNomenclatures != null && !facultyDomainNomenclatures.isEmpty()) {
			return new ResponseEntity<Response>(new Response(facultyDomainNomenclatures), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/universities/{universityId}/faculties/{facultyId}/specializations")
	public ResponseEntity<Response> getSpecializationsByFaculty(@PathVariable("universityId") Integer universityId,
			@PathVariable("facultyId") Integer facultyId) throws Exception {
		List<FacultySpecializationNomenclature> facultySpecializationNomenclatures = this.universityService
				.getFacultySpecializations(universityId, facultyId);
		if (facultySpecializationNomenclatures != null && !facultySpecializationNomenclatures.isEmpty()) {
			return new ResponseEntity<Response>(new Response(facultySpecializationNomenclatures), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/universities/{universityId}/faculties/{facultyId}/sessions")
	public ResponseEntity<Response> getSessionsByFaculty(@PathVariable("universityId") Integer universityId,
			@PathVariable("facultyId") Integer facultyId) throws Exception {
		List<AdmissionSession> admissionSessions = this.universityService.getFacultySessions(universityId, facultyId);
		if (admissionSessions != null && !admissionSessions.isEmpty()) {
			return new ResponseEntity<Response>(new Response(admissionSessions), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}
}