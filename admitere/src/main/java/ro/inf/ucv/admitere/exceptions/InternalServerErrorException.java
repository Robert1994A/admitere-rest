package ro.inf.ucv.admitere.exceptions;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 6559430798199071048L;

	private String message;

	public InternalServerErrorException() {

	}

	public InternalServerErrorException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
