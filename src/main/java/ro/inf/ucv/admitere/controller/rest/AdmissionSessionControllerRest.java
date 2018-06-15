package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.AdmissionSession;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.handler.AppplicationExceptionHandler;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.Statistics;

@RestController
@RequestMapping("/admission_session")
public class AdmissionSessionControllerRest extends BaseController {
	@GetMapping("/{id}")
	private ResponseEntity<Response> getAdmisisonSession(
			@PathVariable(value = "id", required = true) String admissionSessionId) {
		try {
			AdmissionSession admissionSession = this.admisionSessionService.findById(admissionSessionId);
			if (admissionSession == null) {
				return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Response>(new Response(admissionSession), HttpStatus.OK);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}

	@GetMapping("/{id}/statistics")
	private ResponseEntity<Response> getAdmisisonSessionStatistics(
			@PathVariable(value = "id", required = true) String admissionSessionId, Principal principal) {
		try {
			List<Statistics> stats = this.admisionSessionService.getStatistics(admissionSessionId);
			if (stats == null || stats.isEmpty()) {
				return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Response>(new Response(stats), HttpStatus.OK);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}

	@GetMapping("/{id}/users")
	private ResponseEntity<Response> getAdmisisonSessionUsers(
			@PathVariable(value = "id", required = true) String admissionSessionId, Principal principal) {
		try {
			List<User> users = this.admisionSessionService.getUsers(admissionSessionId);
			if (users == null || users.isEmpty()) {
				return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Response>(new Response(users), HttpStatus.OK);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}
}