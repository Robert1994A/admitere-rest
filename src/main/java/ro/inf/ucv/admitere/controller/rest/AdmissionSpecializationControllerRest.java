package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.handler.AppplicationExceptionHandler;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.Statistics;

@RestController
@RequestMapping("/admission_specialization")
public class AdmissionSpecializationControllerRest extends BaseController {

	@GetMapping("/{id}/statistics")
	private ResponseEntity<Response> getAdmisisonSpecializationStatistics(
			@PathVariable(value = "id", required = true) String admissionSpecializationId) {
		try {
			List<Statistics> stats = this.admissionSpecializationService.getStatistics(admissionSpecializationId);
			if (stats == null || stats.isEmpty()) {
				return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Response>(new Response(stats), HttpStatus.OK);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}

	@GetMapping("/{id}/users")
	private ResponseEntity<Response> getAdmisisonSpecializationUsers(
			@PathVariable(value = "id", required = true) String admissionSpecializationId) {
		try {
			List<User> users = this.admissionSpecializationService.getUsers(admissionSpecializationId);
			if (users == null || users.isEmpty()) {
				return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Response>(new Response(users), HttpStatus.OK);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}

	@PostMapping("/{id}/apply")
	private ResponseEntity<Response> applyAtAdmisisonSessison(
			@PathVariable(value = "id", required = true) String admissionSpecializationId, Principal principal) {
		try {
			this.appliedSessionService.applyAtAdmissionSession(admissionSpecializationId, principal.getName());
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}
}
