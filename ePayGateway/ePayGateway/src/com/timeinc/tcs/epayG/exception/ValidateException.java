package com.timeinc.tcs.epayG.exception;

/**
 * 
 * @author poduril
 *
 *         Custom Exception for Validating PayPal Email ID and using it for
 *         Authentication Written 05/25/15
 */

public class ValidateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private String message;
	private String error_field;
	
	public ValidateException() {

		super();
	}

	public ValidateException(String message, String error_field) {

		super(message);
		this.error_field = error_field;
	}

	/**public ValidateException(String message, String clientId, String mailId) {

		super(message);
		this.mailId = mailId;
		this.clientId = clientId;
	}
**/
	public ValidateException(String message, String error_field,
			Throwable cause) {

		super(message, cause);
		this.error_field = error_field;
		
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String getMessage() {
		return super.getMessage() + " for clientId :" + error_field;
	}

	public String geterror_field() {
		return error_field;
	}


}
