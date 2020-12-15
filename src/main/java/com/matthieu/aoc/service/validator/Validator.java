package com.matthieu.aoc.service.validator;

@FunctionalInterface
public interface Validator {

	boolean validate(String s);
	
}
