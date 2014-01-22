package ie.gmit.Encoder.CustomExceptions;

/**
 * @author A Gilani
 * This exception gets thrown when user's provided
 * <br>Encoding Type argument is not recognized
 */
public class NoSuchCodingTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchCodingTypeException() {
		// TODO Auto-generated constructor stub
	}

	public NoSuchCodingTypeException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoSuchCodingTypeException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoSuchCodingTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NoSuchCodingTypeException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
}
