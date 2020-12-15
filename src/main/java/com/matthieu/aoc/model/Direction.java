package com.matthieu.aoc.model;

public enum Direction {
	NORTH(0),
	EAST(90),
	SOUTH(180),
	WEST(270);
	
	private int degree;
	
	private Direction(int degree) {
		this.degree = degree;
	}
	
	public static Direction fromDegree(int degree) {
		degree = degree % 360;
		degree = degree % -360;
		
		if(degree == 0) {
			return Direction.NORTH;
		} else if(degree == 90 || degree == -270) {
			return Direction.EAST;
		} else if(degree == 180 || degree == -180) {
			return Direction.SOUTH;
		} else if(degree == 270 || degree == -90) {
			return Direction.WEST;
		} else {
			throw new IllegalArgumentException("Illegal given degree " + degree);
		}
	}
	
	public int getDegree() {
		return this.degree;
	}
}
