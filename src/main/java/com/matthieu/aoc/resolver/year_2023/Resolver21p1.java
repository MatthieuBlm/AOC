package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver21p1 implements Resolver {

	protected CharMatrix map;
	protected int steps;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new CharMatrix(values);
		this.steps = 64;
		
		// Replace starting position by 'O'
		this.map.forEach((x, y, c) -> {
			if(c == 'S') {
				this.map.set(x, y, 'O');
			}
		});
	}

	@Override
	public boolean solve() throws Exception {
		
		for (int i = 0; i < steps; i++) {
			simulateOneStep();
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return this.map.stream().filter(c -> c == 'O').count() + "";
	}

	public void simulateOneStep() {
		CharMatrix newMap = new CharMatrix(map.getWidth(), map.getHeight(), (x, y) -> this.map.get(x, y));
		
		this.map.forEach((x, y, c) -> {
			if(c == 'O') {
				this.map.forEachNeigthboursCross(x, y, (nx, ny, nc) -> {
					if(nc == '.') {
						newMap.set(nx, ny, 'O');
					}
				});
				newMap.set(x, y, '.');
			}
		});
		
		this.map = newMap;
	}
	
}
