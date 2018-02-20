package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFoundException;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@RestController
public class UsersControllerRest extends BaseController {

	private static final Logger logger = Logger.getLogger(UsersControllerRest.class);

	@GetMapping("/users")
	public ResponseEntity<Response> getUsers(@Valid @ModelAttribute("searchModel") SearchModel searchModel)
			throws Exception {
		Page<User> users = userService.inteligentPagination(searchModel);
		if (users != null && users.hasContent()) {
			return new ResponseEntity<Response>(new Response(users), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Response> getUserDetails(@PathVariable("id") String id) throws UserNotFoundException {
		try {
			User user = userService.findOne(id);
			if (user != null) {
				return new ResponseEntity<Response>(new Response(user), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Cannot find user with id: " + id + ": " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users/{id}/profile")
	public ResponseEntity<Response> getUserProfile(@PathVariable("id") String id) throws UserNotFoundException {
		try {
			User user = userService.findOne(id);
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

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable("id") List<String> ids) throws UserNotFoundException {
		try {
			userService.deleteUsers(ids);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Cannot delete users with id: " + ids + ": " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}
}