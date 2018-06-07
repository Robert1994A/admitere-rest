package ro.inf.ucv.admitere.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@RestController
@RequestMapping("/roles")
public class RoleControllerRest extends BaseController {

	private static final Logger logger = Logger.getLogger(RoleControllerRest.class);

	@GetMapping
	private ResponseEntity<Response> roles(@ModelAttribute("searchModel") SearchModel searchModel) {
		Page<Role> roles = roleService.search(searchModel);
		if (roles != null && roles.hasContent()) {
			return new ResponseEntity<Response>(new Response(roles), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping
	public ResponseEntity<Response> deleteGenderByIds(@RequestBody List<Integer> roleIds) throws Exception {
		if (roleIds != null && !roleIds.isEmpty()) {
			Response response = new Response(null);
			response.setWarnings(this.roleService.deleteByIds(roleIds));
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}")
	private ResponseEntity<Response> findRole(@PathVariable("id") Integer id) {
		try {
			Role role = roleService.findById(id);
			if (role != null) {
				return new ResponseEntity<Response>(new Response(role), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Cannot find role with id: " + id + " : " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	private ResponseEntity<Response> saveRole(@Valid @RequestBody Role role, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
			}
			roleService.save(role, true);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Cannot save role: " + e.getMessage());

		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping
	private ResponseEntity<Response> editRole(@Valid @RequestBody Role role, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
			}
			roleService.save(role, true);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Cannot edit role with id: " + role.getId() + " :" + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}
}
