package com.matthieu.aoc.model.year_2021.bits.impl;

public enum LengthTypeID {

	TOTAL_LENGTH(15),
	PACKET_NUMBER(11);

	private int length;
	
	private LengthTypeID(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public static LengthTypeID valueOf(int i) {
		if(i == 0) {
			return TOTAL_LENGTH;
		} else if(i == 1) {
			return PACKET_NUMBER;
		}
		
		throw new IllegalArgumentException("Unknown length type ID "+ i);
	}
}
