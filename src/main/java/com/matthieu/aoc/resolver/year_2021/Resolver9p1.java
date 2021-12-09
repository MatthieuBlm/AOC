package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parsers;

public class Resolver9p1 implements Resolver {

	protected Matrix<Integer> matrix;
	private long riskLevel;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.matrix = new Matrix<>(values, Parsers.toInt());
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int y = 0; y < this.matrix.getYSize(); y++) {
			for (int x = 0; x < this.matrix.getXSize(); x++) {
				List<Integer> adjacent = this.matrix.neightboursCross(x, y);
				Integer current = this.matrix.get(x, y);
				
				if(this.isLowestPoint(current, adjacent)) {
					this.processLowestPoint(current, x, y);
				}
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.riskLevel);
	}

	protected boolean isLowestPoint(Integer current, List<Integer> adjacent) {
		return adjacent.stream().noneMatch(i -> i <= current);
	}
	
	protected void processLowestPoint(Integer current, int x, int y) {
		riskLevel += current + 1;
	}
}
