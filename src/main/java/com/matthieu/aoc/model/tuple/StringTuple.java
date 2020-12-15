package com.matthieu.aoc.model.tuple;

public class StringTuple extends Duo<String, String> {

	public StringTuple(String a, String b) {
		super(a, b);
	}

	public StringTuple(String[] values) {
		super(values[0], values[1]);
	}

}
