package ro.inf.ucv.admitere.exceptions;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6690148518639624562L;

	private String message;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
