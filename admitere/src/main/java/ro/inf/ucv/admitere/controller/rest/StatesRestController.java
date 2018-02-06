package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.State;
import ro.inf.ucv.admitere.wrapper.Response;

@Controller
public class StatesRestController extends BaseController {

	@GetMapping("/states")
	private ResponseEntity<Response> states(@RequestParam(value = "countryId", required = false) Long countryId)
			throws Exception {
		List<State> states = stateService.findByCountry(countryId);
		if (states != null && !states.isEmpty()) {
			return new ResponseEntity<Response>(new Response(states), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}
}