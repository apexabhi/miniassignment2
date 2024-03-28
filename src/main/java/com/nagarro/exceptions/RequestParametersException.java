package com.nagarro.exceptions;

public class RequestParametersException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequestParametersException(String msg) {
		super(msg);
	}
}
