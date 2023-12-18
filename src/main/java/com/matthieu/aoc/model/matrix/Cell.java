package com.matthieu.aoc.model.matrix;

public class Cell<T> {

	private int x;
	private int y;
	private T value;
	
	public Cell(int x, int y, T value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	public int x() {
		return x;
	}
	
	public void x(int x) {
		this.x = x;
	}
	
	public int y() {
		return y;
	}
	
	public void y(int y) {
		this.y = y;
	}
	
	public T value() {
		return this.value;
	}
	
	public void value(T value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", value=" + value + "]";
	}
}
