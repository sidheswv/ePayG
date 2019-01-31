package com.timeinc.tcs.epayG.exception;

/**
 * 
 * @author poduril
 *
 *         Custom Exception for Validating URL
 *         Written 03/16/17
 */

public class ECConnectionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	 private String message;

	public ECConnectionException() {
		super();
	}

   public ECConnectionException(String message){
   	super(message);
   	this.message = message;
   }
   
	public ECConnectionException(String message, String url,Throwable cause) {
		super(message, cause);
		this.message = message;
		
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String getMessage() {
		return super.getMessage() + " for :" + message;
	}

	public String geturl() {
		return message;
	}
	

}
