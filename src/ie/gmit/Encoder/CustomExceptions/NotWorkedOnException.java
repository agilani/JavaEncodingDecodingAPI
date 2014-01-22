package ie.gmit.Encoder.CustomExceptions;

/**
 * @author A Gilani
 * This exception gets thrown when the invoked method has
 * <br>no implementation in this API. Was just messing around with
 * <br>custom exceptions.
 */
public class NotWorkedOnException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotWorkedOnException() {
		// TODO Auto-generated constructor stub
	}

	public NotWorkedOnException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotWorkedOnException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NotWorkedOnException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotWorkedOnException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
