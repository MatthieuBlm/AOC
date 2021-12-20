package com.matthieu.aoc.model.year_2021;

import com.matthieu.aoc.model.matrix.Row;

public class BeaconDistance {

	private double value;
	private Row<Integer> a;
	private Row<Integer> b;
	
	
	public BeaconDistance(double value, Row<Integer> a, Row<Integer> b) {
		this.value = value;
		this.a = a;
		this.b = b;
	}
	
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Row<Integer> getA() {
		return a;
	}
	public void setA(Row<Integer> a) {
		this.a = a;
	}
	public Row<Integer> getB() {
		return b;
	}
	public void setB(Row<Integer> b) {
		this.b = b;
	}
	
	
}
