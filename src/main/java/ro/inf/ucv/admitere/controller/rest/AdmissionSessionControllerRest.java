package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.handler.AppplicationExceptionHandler;
import ro.inf.ucv.admitere.wrapper.Response;
import ro.inf.ucv.admitere.wrapper.Statistics;

@Controller
public class AdmissionSessionControllerRest extends BaseController {

	@PostMapping("/admission_session/apply/{id}")
	private ResponseEntity<Response> applyAtAdmisisonSessison(
			@PathVariable(value = "id", required = true) String admissionSpecializationId, Principal principal) {
		try {
			appliedSessionService.applyAtAdmissionSession(admissionSpecializationId, principal.getName());
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}

	@GetMapping("/admission_specialization/{id}/statistics")
	private ResponseEntity<Response> getAdmisisonSpecializationStatistics(
			@PathVariable(value = "id", required = true) String admissionSpecializationId, Principal principal) {
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

	@GetMapping("/admission_session/{id}/statistics")
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

}