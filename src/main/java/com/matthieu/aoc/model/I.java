package com.matthieu.aoc.model;

public class I {
	
	private int value;
	
	public I(int value) {
		this.value = value;
	}
	
	public void set(int value) {
		this.value = value;
	}
	
	public int get() {
		return value;
	}
	
	public void increase() {
		this.value++;
	}
	
	public boolean lowerThan(int i) {
		return this.value < i;
	}
	
	public boolean greaterThan(int i) {
		return this.value > i;
	}
	
	public boolean equal(int i) {
		return this.value == i;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

}
