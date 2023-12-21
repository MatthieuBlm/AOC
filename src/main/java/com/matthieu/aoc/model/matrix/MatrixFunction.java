package com.matthieu.aoc.model.matrix;

@FunctionalInterface
public interface MatrixFunction<T> {

	T call(int x, int y);
	
}
