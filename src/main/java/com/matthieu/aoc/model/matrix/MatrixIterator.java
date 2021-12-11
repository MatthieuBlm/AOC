package com.matthieu.aoc.model.matrix;

@FunctionalInterface
public interface MatrixIterator<T> {

	void iterate(int x, int y, T value);
}
