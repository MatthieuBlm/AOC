package com.matthieu.aoc.model;

public enum Orientation {
	HORIZONTAL(0), VERTICAL(1);

	private int i;
	
	private Orientation(int i) {
		this.i = i;
	}
	
	public int intValue() {
		return i;
	}
}
