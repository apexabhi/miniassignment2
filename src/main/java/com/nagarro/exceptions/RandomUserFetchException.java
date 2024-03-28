package com.nagarro.exceptions;

public class RandomUserFetchException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RandomUserFetchException(String msg) {
		super(msg);
	}
}
