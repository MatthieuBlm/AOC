package com.matthieu.aoc_2020.service.validator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc_2020.model.Tuple;

public class ValidatorSet {

	private Map<String, Validator> validators;
	
	public ValidatorSet() {
		this.validators = new HashMap<>();
	}
	
	
	public void put(String key, Validator validator) {
		this.validators.put(key, validator);
	}
	
	public void remove(String key) {
		this.validators.remove(key);
	}
	
	public boolean validate(String key, String value) {
		return this.validators.get(key).validate(value);
	}
	
	public boolean validate(Tuple<String, String> values) {
		return this.validate(values.getKey(), values.getValue());
	}
	
	public boolean validate(String key, String... values) {
		return Arrays.asList(values).stream()
					.allMatch(validators.get(key)::validate);
	}
	
	public boolean validate(List<Tuple<String, String>> values) {
		return values.stream()
					.map(this::validate)
					.allMatch(b -> b);
	}
	
}
