package ro.inf.ucv.admitere.wrapper;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {

	private String message;

	private Object content;

	private Object validation;

	private HttpStatus status;

	public Response(Object content) {
		super();
		this.content = content;
	}

	public Response(String message, Object content) {
		super();
		this.message = message;
		this.content = content;
	}

	public Response(String message, Object content, Object validation) {
		super();
		this.message = message;
		this.content = content;
		this.validation = validation;
	}

	public Response(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getValidation() {
		return validation;
	}

	public void setValidation(Object validation) {
		this.validation = validation;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
