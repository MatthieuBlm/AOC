package com.matthieu.aoc_2020.model.tuple;

public class IntTuple extends Tuple<Integer, Integer> {

	public IntTuple(Integer key, Integer value) {
		super(key, value);
	}
	
	public IntTuple(Integer[] values) {
		super(values[0], values[1]);
	}

}
