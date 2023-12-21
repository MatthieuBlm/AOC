package com.matthieu.aoc.model.matrix;

import java.util.List;
import java.util.function.Supplier;

public class CharMatrix extends Matrix<Character> {

	public CharMatrix(int width, int height, Supplier<Character> defaultValueGetter) {
		super(width, height, defaultValueGetter);
	}

	public CharMatrix(int width, int height, MatrixFunction<Character> defaultValueGetter) {
		super(width, height, defaultValueGetter);
	}
	
	public CharMatrix(List<String> values) {
		super(values, s -> s.charAt(0), "");
	}

}
