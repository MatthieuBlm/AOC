package com.matthieu.aoc.resolver.year_2022;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver2p1 implements Resolver {

	protected long result;
	protected List<List<Character>> rounds;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		rounds = values.stream()
						.map(s -> s.split(" "))
						.map(array -> Arrays.asList(array[0].charAt(0), array[1].charAt(0)))
						.collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		
		result = rounds.stream().mapToInt(round -> {
			Boolean isWin = this.win(round.get(0), round.get(1));
			int r = 0;
			
			if(isWin == Boolean.TRUE) {
				r += 6;
			} else if(isWin == null) {
				r += 3;
			}
			
			if(round.get(1) == 'X') {
				r += 1; 
			} else if(round.get(1) == 'Y') {
				r += 2;
			} else {
				r += 3;
			}
			
			return r;
		}).sum();
		
		return true;
	}

	@Override
	public String get() {
		return result + "";
	}

	protected Boolean win(char a, char b) {
		if(a == 'A') {
			if(b == 'Y') {
				return true;
			} else if(b == 'X') {
				return null;
			} else {
				return false;
			}
		}
		
		if(a == 'B') {
			if(b == 'Y') {
				return null;
			} else if(b == 'X') {
				return false;
			} else {
				return true;
			}
		}
		
		if(a == 'C') {
			if(b == 'Y') {
				return false;
			} else if(b == 'X') {
				return true;
			} else {
				return null;
			}
		}
		
		return null;
	}
}
