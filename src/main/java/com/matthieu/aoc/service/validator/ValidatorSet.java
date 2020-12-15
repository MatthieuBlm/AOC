package com.matthieu.aoc.service.validator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.model.tuple.Tuple;

public class ValidatorSet {

	private Map<String, Validator> validators;
	
	public ValidatorSet() {
		this.validators = new HashMap<>();
	}
	
	public Validator get(String key) {
		if(this.exists(key)) {
			return this.validators.get(key);
		}
		throw new IllegalArgumentException("No validator found for key " + key);
	}
	
	public boolean exists(String key) {
		return this.validators.get(key) != null;
	}
	
	public void put(String key, Validator validator) {
		this.validators.put(key, validator);
	}
	
	public void remove(String key) {
		this.validators.remove(key);
	}
	
	public boolean validate(String key, String value) {
		return this.get(key).validate(value);
	}
	
	public boolean validate(Tuple<String, String> values) {
		return this.validate(values.getKey(), values.getValue());
	}
	
	public boolean validate(String key, String... values) {
		Validator validator = this.get(key);
		return Arrays.asList(values).stream()
					.allMatch(validator::validate);
	}
	
	public boolean validate(List<Tuple<String, String>> values) {
		return values.stream()
					.map(this::validate)
					.allMatch(b -> b);
	}
	
}
