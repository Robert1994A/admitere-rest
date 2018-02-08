package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.wrapper.Response;

@RestController
public class CountryControllerRest extends BaseController {

	@GetMapping("/countries")
	private ResponseEntity<Response> countries() throws Exception {
		List<Country> countries = countryService.findAll();
		if (countries != null && !countries.isEmpty()) {
			return new ResponseEntity<Response>(new Response(countries), HttpStatus.OK);
		}
		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}
}