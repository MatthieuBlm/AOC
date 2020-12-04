package com.matthieu.aoc_2020.service.validator;

@FunctionalInterface
public interface Validator {

	boolean validate(String s);
	
}
