package com.loukou.auth.exception;

public class AuthRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthRuntimeException(String message) {
		super(message);
	}

	public AuthRuntimeException(String message, Exception e) {
		super(message, e);
	}

}
