package ro.inf.ucv.admitere.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class FieldValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<FieldError> fieldErrors = null;

	public FieldValidationException(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
