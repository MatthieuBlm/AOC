package com.matthieu.aoc_2020.exception;

public class PrepareDataException extends Exception {

	private static final long serialVersionUID = -9113975572668966194L;

	public PrepareDataException(String message) {
		super(message);
	}
	
	public PrepareDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
