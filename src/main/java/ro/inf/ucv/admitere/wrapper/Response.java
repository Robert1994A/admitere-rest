package ro.inf.ucv.admitere.wrapper;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Response {

	private String message;

	private Object data;

	private Object validation;

	private List<String> warnings;

	private HttpStatus status;

	public Response(Object content) {
		super();
		this.data = content;
	}

	public Response(String message, Object content) {
		super();
		this.message = message;
		this.data = content;
	}

	public Response(String message, Object content, Object validation) {
		super();
		this.message = message;
		this.data = content;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
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

	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
}
