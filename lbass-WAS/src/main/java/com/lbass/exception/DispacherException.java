package com.lbass.exception;

public class DispacherException extends Exception {
	
	public DispacherException(String message) {
		super(message);
	}

	public DispacherException(String message, Exception e) {
		super(message, e);
	}
}
