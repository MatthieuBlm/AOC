package com.matthieu.aoc_2020.model.tuple;

public class StringTuple extends Tuple<String, String> {

	public StringTuple(String key, String value) {
		super(key, value);
	}

	public StringTuple(String[] values) {
		super(values[0], values[1]);
	}

}