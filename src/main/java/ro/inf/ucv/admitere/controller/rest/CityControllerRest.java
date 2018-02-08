package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.wrapper.Response;

@Controller
public class CityControllerRest extends BaseController {

	@GetMapping("/cities")
	private ResponseEntity<Response> cities(@RequestParam(value = "stateId", required = false) Integer stateId) {
		List<City> cities = cityService.findByState(stateId);
		if (cities != null && !cities.isEmpty()) {
			return new ResponseEntity<Response>(new Response(cities), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}
}