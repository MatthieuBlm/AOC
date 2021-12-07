package com.matthieu.aoc.resolver.year_2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver7p1 implements Resolver {

	protected List<Integer> positions;
	protected long minFuel;
	protected long optimizedPosition;
	protected int maxPosition;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.positions = Arrays.asList(values.get(0).split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
		this.minFuel = Long.MAX_VALUE;
		this.maxPosition = this.positions.stream().reduce((a, b) -> Math.max(a, b)).get();
	}

	@Override
	public boolean solve() throws SolveException {

		for (int i = 0; i <= this.maxPosition; i++) {
			long fuel = 0l;
			
			for (Integer pos : positions) {
				fuel += this.calcFuel(pos, i);
			}
			
			if(fuel < this.minFuel) {
				this.minFuel = fuel;
				this.optimizedPosition = i;
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.minFuel);
	}
	
	protected long calcFuel(int pos, int i) {
		return Math.abs(pos - i);
	}

}
