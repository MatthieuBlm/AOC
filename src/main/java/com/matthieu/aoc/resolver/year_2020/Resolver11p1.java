package com.matthieu.aoc.resolver.year_2020;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.year_2020.seat.Seat;
import com.matthieu.aoc.model.year_2020.seat.SeatState;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver11p1 implements Resolver {

	protected Matrix<Seat> seats;
	protected int freeThreshold;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<Seat> seatParser = s -> {
			if(s.equals(".")) {
				return new Seat(SeatState.NONE);
			}
			return new Seat(SeatState.EMPTY);
		};
		
		seats = new Matrix<>(values, seatParser);
		freeThreshold = 4;
	}

	@Override
	public boolean solve() throws SolveException {
		do {
			// Update seat state
			seats.stream().forEach(seat -> seat.setState(seat.getNextState()));

			this.applyRules();
		
		} while(seats.stream().anyMatch(seat -> seat.getState() != seat.getNextState()));
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.seats.stream().filter(seat -> seat.getState() == SeatState.OCCUPIED).count());
	}

	protected void applyRules() {
		for (int i = 0; i <= seats.getMaxX(); i++) {
			for (int j = 0; j <= seats.getMaxY(); j++) {
				Seat seat = seats.get(i, j);
				long neightbour = this.getNeightbourCount(i, j);
				
				if(seat.getState() == SeatState.EMPTY && neightbour == 0) {
					seat.setNextState(SeatState.OCCUPIED);
				} else if(seat.getState() == SeatState.OCCUPIED && neightbour >= freeThreshold) {
					seat.setNextState(SeatState.EMPTY);
				}
			}
		}
	}
	
	protected long getNeightbourCount(int x, int y) {
		return seats.getNeightbours(x, y).stream().filter(s -> s.getState() == SeatState.OCCUPIED).count();
	}
	
}
