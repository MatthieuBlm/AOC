package com.matthieu.aoc.model.matrix;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(value, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		return Objects.equals(value, other.value) && x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", value=" + value + "]";
	}
}
