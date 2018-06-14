package ro.inf.ucv.admitere.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.TransactionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ro.inf.ucv.admitere.controller.html.BaseController;
import ro.inf.ucv.admitere.exceptions.AlreadyAppliedException;
import ro.inf.ucv.admitere.exceptions.DisabledAdmissionSessionException;
import ro.inf.ucv.admitere.exceptions.FieldValidationException;
import ro.inf.ucv.admitere.exceptions.NotAuthenticatedException;
import ro.inf.ucv.admitere.exceptions.ProfileNotFoundException;
import ro.inf.ucv.admitere.exceptions.UserNotFoundException;
import ro.inf.ucv.admitere.wrapper.Response;

@ControllerAdvice
public class AppplicationExceptionHandler extends BaseController {

	private static final Logger logger = Logger.getLogger(AppplicationExceptionHandler.class);

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> userNotFoundException(UserNotFoundException ex) {
		logger.error("User not found: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { FieldValidationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response> fieldValidationExceptions(FieldValidationException ex, HttpServletRequest request) {
		logger.error("Field validation exception: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getMessage(), null, ex.getFieldErrors()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { DisabledAdmissionSessionException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> disabledAdmissionSessionException(DisabledAdmissionSessionException ex,
			HttpServletRequest request) {
		logger.error("Disabled admission session exception: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> jpaExceptions(DataIntegrityViolationException ex, HttpServletRequest request) {
		logger.error("DataIntegrityViolationException: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getRootCause().getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { TransactionException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> jpaExceptions(TransactionException ex, HttpServletRequest request) {
		logger.error("TransactionException: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { JpaSystemException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> jpaExceptions(JpaSystemException ex, HttpServletRequest request) {
		logger.error("JpaSystemException: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> customExceptions(Exception ex, HttpServletRequest request) {
		logger.error("Exception: ", ex);
		return new ResponseEntity<Response>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static ResponseEntity<Response> catchExceptions(Exception e) {
		logger.error(e);
		Response response = new Response(e.getMessage());
		if (e instanceof AlreadyAppliedException) {
			return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
		} else if (e instanceof NotAuthenticatedException) {
			return new ResponseEntity<Response>(response, HttpStatus.NOT_ACCEPTABLE);
		} else if (e instanceof ProfileNotFoundException) {
			return new ResponseEntity<Response>(response, HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
