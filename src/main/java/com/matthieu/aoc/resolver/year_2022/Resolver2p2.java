package com.matthieu.aoc.resolver.year_2022;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver2p2 extends Resolver2p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		updateRoundsToLose();
	}
	
	
	private void updateRoundsToLose() {
		for (int i = 0; i < rounds.size(); i++) {
			List<Character> round = rounds.get(i);
			
			if(round.get(1) == 'X') {
				round.set(1, toLose(round.get(0)));
			} else if(round.get(1) == 'Y') {
				round.set(1, toDraw(round.get(0)));
			} else {
				round.set(1, toWin(round.get(0)));
			}
		}
	}
	
	private char toWin(char c) {
		if(c == 'A') {
			return 'Y';
		} else if(c == 'B') {
			return 'Z';
		} else {
			return 'X';
		}
	}
	
	private char toLose(char c) {
		if(c == 'A') {
			return 'Z';
		} else if(c == 'B') {
			return 'X';
		} else {
			return 'Y';
		}
	}
	
	private char toDraw(char c) {
		if(c == 'A') {
			return 'X';
		} else if (c == 'B') {
			return 'Y';
		} else {
			return 'Z';
		}
	}


}
