package com.matthieu.aoc.resolver.year_2020;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.SolveException;

public class Resolver5p2 extends Resolver5p1 {

	protected Integer mySeatId;
	
	@Override
	public boolean solve() throws SolveException {
		
		super.solve();
		
		List<Entry<Integer, String>> seatsList = super.seats.entrySet().stream().collect(Collectors.toList());
		
		for (int i = 0; i < seatsList.size() - 2; i++) {
			
			Entry<Integer, String> beforeSeat = seatsList.get(i);
			Entry<Integer, String> afterSeat = seatsList.get(i + 1);
			
			if(beforeSeat.getKey() + 2 == afterSeat.getKey()) {
				mySeatId = beforeSeat.getKey() + 1;
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public String get() {
		return String.valueOf(mySeatId);
	}
	
	

}
