package com.matthieu.aoc_2020.model.seat;

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
