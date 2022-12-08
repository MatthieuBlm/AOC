package com.matthieu.aoc.resolver.year_2022;

import java.util.Collections;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver8p2 extends Resolver8p1 {

	private Matrix<Long> scenicDistances;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		scenicDistances = new Matrix<>(grid.getWidth(), grid.getHeight(), () -> 0l);
	}

	@Override
	public boolean solve() throws SolveException {
		
		grid.forEach((x, y, h) -> scenicDistances.set(x, y, getScenicDistance(x, y, h)));
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(scenicDistances.stream().sorted(Collections.reverseOrder()).findFirst().get());
	}

	private long getScenicDistance(int x, int y, int height) {
		return getTopDistance(x, y, height) * getBottomDistance(x, y, height) * getRightDistance(x, y, height) * getLeftDistance(x, y, height);
	}
	
	private long getTopDistance(int x, int y, int height) {
		long d = 0;
		
		if(y == 0) {
			return 0;
		}
		
		for (int i = y - 1; i >= 0; i--) {
			d++;
			
			if(this.grid.get(x, i) >= height) {
				return d;
			}
		}
		
		return d;
	}

	private long getBottomDistance(int x, int y, int height) {
		long d = 0;
		
		if(y == grid.getHeight() - 1) {
			return 0;
		}
		
		for (int i = y + 1; i < grid.getHeight(); i++) {
			d++;
		
			if(this.grid.get(x, i) >= height) {
				return d;
			}
		}
		
		return d;
	}

	private long getRightDistance(int x, int y, int height) {
		long d = 0;
		
		if(x == grid.getWidth() - 1) {
			return 0;
		}
		
		for (int i = x + 1; i < grid.getWidth(); i++) {
			d++;

			if(this.grid.get(i, y) >= height) {
				return d;
			}
		}
		
		return d;
	}
	
	private long getLeftDistance(int x, int y, int height) {
		long d = 0;
		
		if(x == 0) {
			return 0;
		}
		
		for (int i = x - 1; i >= 0; i--) {
			d++;
			
			if(this.grid.get(i, y) >= height) {
				return d;
			}
		}
		
		return d;
	}
}
