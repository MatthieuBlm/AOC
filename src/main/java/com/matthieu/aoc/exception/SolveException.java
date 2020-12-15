package com.matthieu.aoc.exception;

public class SolveException extends Exception {

	private static final long serialVersionUID = 8588583671321283961L;

	public SolveException(String message) {
		super(message);
	}
	
	public SolveException(String message, Throwable cause) {
		super(message, cause);
	}
}
