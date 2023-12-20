package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.matrix.Cell;

public class Resolver16p2 extends Resolver16p1 {

	private long maxEnergized;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
	}
	
	@Override
	public boolean solve() throws Exception {
		
		// Top
		for (int x = 0; x < this.map.getWidth(); x++) {
			solveWithInitialPosition(x, 0, Direction.SOUTH);
		}
		
		// Left
		for (int y = 0; y < this.map.getHeight(); y++) {
			solveWithInitialPosition(map.getMaxX(), y, Direction.WEST);
		}
		
		// Bottom
		for (int x = 0; x < this.map.getWidth(); x++) {
			solveWithInitialPosition(x, map.getMaxY(), Direction.NORTH);
		}

		// Right
		for (int y = 0; y < this.map.getHeight(); y++) {
			solveWithInitialPosition(0, y, Direction.EAST);
		}
		
		return true;
	}
	
	private void solveWithInitialPosition(int x, int y, Direction d) throws Exception {
		this.beams.clear();
		this.beams.add(new Cell<>(x, y, d));
		this.lights.forEach(List::clear);
		
		super.solve();
		
		this.maxEnergized = Math.max(this.getEnergizedCount(), maxEnergized);
	}
	
	@Override
	public String get() {
		return this.maxEnergized + "";
	}
}
