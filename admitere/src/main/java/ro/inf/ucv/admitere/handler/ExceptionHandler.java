package ro.inf.ucv.admitere.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.exceptions.FieldValidationException;
import ro.inf.ucv.admitere.exceptions.UserNotFoundException;
import ro.inf.ucv.admitere.wrapper.Response;

@ControllerAdvice
class AppplicationExceptionHandler extends BaseController {

	private static final Logger logger = Logger.getLogger(AppplicationExceptionHandler.class);

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> userNotFoundException(UserNotFoundException ex) {
		logger.error("User not found: ", ex);
		return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { FieldValidationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response> fieldValidationExceptions(FieldValidationException ex, HttpServletRequest request) {
		logger.error("Field validation exception: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getMessage(), null, ex.getFieldErrors()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> customExceptions(Exception ex, HttpServletRequest request) {
		logger.error("Exception: ", ex);
		return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
