package com.mindgeek.exception;

/**
 * The Class GenericException is common exception being used in this project.
 * 
 * @author Taran
 */
public class GenericException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new generic exception.
	 *
	 * @param message the message
	 */
	public GenericException(String message) {
		this.message = message;
	}

}
