package com.matthieu.aoc.model.year_2020.seat;

public class Seat {

	private SeatState state;
	private SeatState nextState;
	
	
	public Seat(SeatState state) {
		this.state = this.nextState = state;
	}
	
	
	public SeatState getState() {
		return state;
	}
	public void setState(SeatState state) {
		this.state = state;
	}
	public SeatState getNextState() {
		return nextState;
	}
	public void setNextState(SeatState nextState) {
		this.nextState = nextState;
	}
	
	
	@Override
	public String toString() {
		return String.valueOf(this.state.getChar());
	}
}
