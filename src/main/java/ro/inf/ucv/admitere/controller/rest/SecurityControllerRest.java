package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.utils.ListUtils;
import ro.inf.ucv.admitere.wrapper.AuthenticatedUser;
import ro.inf.ucv.admitere.wrapper.Response;

@RestController
public class SecurityControllerRest extends BaseController {

	@GetMapping("/security/roles")
	private ResponseEntity<Response> roles(Principal principal) {
		List<Role> roles = roleService.getUserRoles(principal);
		if (!ListUtils.isEmpty(roles)) {
			return new ResponseEntity<Response>(new Response(roles), HttpStatus.OK);
		}
		return new ResponseEntity<Response>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/security/authenticationDetails")
	private ResponseEntity<Response> isAuthenticated() {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return new ResponseEntity<Response>(
					new Response(AuthenticatedUser.create(SecurityContextHolder.getContext().getAuthentication())),
					HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.UNAUTHORIZED);
	}
}