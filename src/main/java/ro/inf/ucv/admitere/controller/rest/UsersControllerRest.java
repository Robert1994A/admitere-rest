package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFoundException;
import ro.inf.ucv.admitere.utils.search.CriteriaParser;
import ro.inf.ucv.admitere.utils.search.GenericSpecification;
import ro.inf.ucv.admitere.utils.search.GenericSpecificationsBuilder;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@RestController
@RequestMapping("/users")
public class UsersControllerRest extends BaseController {

	private static final Logger logger = Logger.getLogger(UsersControllerRest.class);

	@GetMapping
	public ResponseEntity<Response> getUsers(@Valid @ModelAttribute("searchModel") SearchModel searchModel)
			throws Exception {
		Page<User> users = this.userService.inteligentPagination(searchModel);
		if (users != null && users.hasContent()) {
			return new ResponseEntity<Response>(new Response(users), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getUserDetails(@PathVariable("id") String id) throws UserNotFoundException {
		try {
			User user = this.userService.findOne(id);
			if (user != null) {
				return new ResponseEntity<Response>(new Response(user), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Cannot find user with id: " + id + ": " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}/profile")
	public ResponseEntity<Response> getUserProfile(@PathVariable("id") String id) throws UserNotFoundException {
		try {
			User user = this.userService.findOne(id);
			if (user != null) {
				Profile profile = user.getProfile();
				if (profile != null) {
					return new ResponseEntity<Response>(new Response(profile), HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			logger.error("Cannot find profile for user with id: " + id + ": " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/enable")
	public ResponseEntity<Response> enableUser(@RequestBody List<String> ids) throws UserNotFoundException {
		try {
			this.userService.enableUsers(ids);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Cannot enable users with ids: " + ids + ": " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/disable")
	public ResponseEntity<Response> disableUser(@RequestBody List<String> ids) throws UserNotFoundException {
		try {
			this.userService.disableUsers(ids);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Cannot disable users with ids: " + ids + ": " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping
	public ResponseEntity<Response> deleteUser(@RequestBody List<String> ids) throws UserNotFoundException {
		try {
			this.userService.deleteUsers(ids);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Cannot delete users with ids: " + ids + ": " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/advancedSearch")
	public ResponseEntity<Response> statistics(@RequestParam("search") String searchParameters) {
		CriteriaParser parser = new CriteriaParser();
		GenericSpecificationsBuilder<User> specBuilder = new GenericSpecificationsBuilder<>();
		Specification<User> specification = specBuilder.build(parser.parse(searchParameters),
				GenericSpecification::new);
		List<User> users = this.userService.searchAdvanced(specification);
		if (users != null && !users.isEmpty()) {
			return new ResponseEntity<Response>(new Response(users), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}
}