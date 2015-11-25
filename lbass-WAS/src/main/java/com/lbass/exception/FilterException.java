package com.lbass.exception;

public class FilterException extends Exception {

	public FilterException(String message) {
		super(message);
	}

	public FilterException(String message, Exception e) {
		super(message, e);
	}
}
