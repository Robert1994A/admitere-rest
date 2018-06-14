package ro.inf.ucv.admitere.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.entity.AppliedSession;
import ro.inf.ucv.admitere.handler.AppplicationExceptionHandler;
import ro.inf.ucv.admitere.utils.ListUtils;
import ro.inf.ucv.admitere.wrapper.Response;

@Controller
@RequestMapping("/applied_sessions")
public class AppliedSessionControllerRest extends BaseController {

	@GetMapping
	private ResponseEntity<Response> getUserAppliedSessions(Principal principal) {
		try {
			List<AppliedSession> appliedSessions = this.appliedSessionService
					.findAppliedSessionsByUserOrderByDateDESC(principal.getName());
			if (!ListUtils.isEmpty(appliedSessions)) {
				return new ResponseEntity<Response>(new Response(appliedSessions), HttpStatus.OK);
			}
			return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return AppplicationExceptionHandler.catchExceptions(e);
		}
	}
}
