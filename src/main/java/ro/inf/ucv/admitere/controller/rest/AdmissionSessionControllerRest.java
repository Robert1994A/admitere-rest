package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.handler.AppplicationExceptionHandler;
import ro.inf.ucv.admitere.wrapper.Response;

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

}