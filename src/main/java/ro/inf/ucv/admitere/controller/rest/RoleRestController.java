package ro.inf.ucv.admitere.controller.rest;

import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Controller
@RequestMapping("/roles")
public class RoleRestController extends BaseController {

	private static final Logger logger = Logger.getLogger(RoleRestController.class);

	@GetMapping
	private ResponseEntity<Response> roles(@ModelAttribute("searchModel") SearchModel searchModel) {
		Page<Role> roles = roleService.search(searchModel);
		if (roles != null && roles.hasContent()) {
			return new ResponseEntity<Response>(new Response(roles), HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Response> deleteRole(@PathVariable("id") Long id) {
		try {
			roleService.deleteById(id);
		} catch (Exception e) {
			logger.error("Cannot delete role with id: " + id + " : " + e.getMessage());
			return new ResponseEntity<Response>(new Response("Error was thrown when we try to delete the role."),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Response>(new Response("Role was deleted successfully."), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<Response> findRole(@PathVariable("id") Long id) {
		try {
			Role role = roleService.findById(id);
			if (role != null) {
				return new ResponseEntity<Response>(new Response(role), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Cannot find role with id: " + id + " : " + e.getMessage());
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	private ResponseEntity<Object> saveRole(@Valid @RequestBody Role role, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			role.setId(null);
			role.setCreationDate(new Date());
			roleService.save(role);
		} catch (Exception e) {
			logger.error("Cannot save role: " + e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PutMapping
	private ResponseEntity<Response> editRole(@Valid @RequestBody Role role, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Response response = new Response(null, null, bindingResult.getAllErrors());
				return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
			}
			roleService.save(role);
		} catch (Exception e) {
			logger.error("Cannot edit role with id: " + role.getId() + " :" + e.getMessage());
			return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Response>(HttpStatus.OK);
	}
}
