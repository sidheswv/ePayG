package com.timeinc.tcs.epayG.exception;

/**
 * 
 * @author poduril
 *
 *         Custom Exception for Validating URL
 *         Written 05/25/15
 */

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 private String message;

	public ServiceException() {
		super();
	}

    public ServiceException(String message){
    	super(message);
    	this.message = message;
    }
    
	public ServiceException(String message, String url,Throwable cause) {
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
