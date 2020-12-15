package com.matthieu.aoc.model.tuple;

public class IntTuple extends Duo<Integer, Integer> {

	public IntTuple(Integer a, Integer b) {
		super(a, b);
	}
	
	public IntTuple(Integer[] values) {
		super(values[0], values[1]);
	}

}
