package ro.inf.ucv.admitere.exceptions;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1570715165015489522L;
	
	private String message;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
