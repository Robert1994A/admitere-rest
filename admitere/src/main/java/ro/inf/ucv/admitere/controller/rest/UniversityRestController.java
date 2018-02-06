package ro.inf.ucv.admitere.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.University;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Controller
public class UniversityRestController extends BaseController {

	@GetMapping("/universities")
	public ResponseEntity<Response> getUniversities(@ModelAttribute("searchModel") SearchModel searchModel)
			throws Exception {
		Page<University> universities = universityService.inteligentPagination(searchModel);
		if (universities != null && universities.hasContent()) {
			return new ResponseEntity<Response>(new Response(universities), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}
}