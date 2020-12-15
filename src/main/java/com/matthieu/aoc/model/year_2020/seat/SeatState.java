package com.matthieu.aoc.model.year_2020.seat;

public enum SeatState {
	EMPTY('L'),
	OCCUPIED('#'),
	NONE('.');
	
	private SeatState(char c) {
		this.c = c;
	}
	
	private char c;
	
	public char getChar() {
		return this.c;
	}
}
