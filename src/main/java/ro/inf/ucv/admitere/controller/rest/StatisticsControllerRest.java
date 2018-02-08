package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.utils.search.CriteriaParser;
import ro.inf.ucv.admitere.utils.search.GenericSpecification;
import ro.inf.ucv.admitere.utils.search.GenericSpecificationsBuilder;
import ro.inf.ucv.admitere.wrapper.Response;

@RestController
public class StatisticsControllerRest extends BaseController {

	@GetMapping("/statistics")
	public ResponseEntity<Response> statistics(@RequestParam("search") String searchParameters) {
		CriteriaParser parser = new CriteriaParser();
		GenericSpecificationsBuilder<User> specBuilder = new GenericSpecificationsBuilder<>();
		Specification<User> specification = specBuilder.build(parser.parse(searchParameters),
				GenericSpecification::new);
		List<User> users = userService.searchAdvanced(specification);
		return new ResponseEntity<Response>(new Response(users), HttpStatus.OK);
	}
}
