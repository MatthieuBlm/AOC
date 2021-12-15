package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.graph.DPSolver;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver15p1 implements Resolver {

	protected Matrix<Long> risks;
	protected long minRisk;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.risks = new Matrix<>(values, Long::parseLong);
	}

	@Override
	public boolean solve() throws SolveException {
		
		this.minRisk = DPSolver.calculateMinimumPath(risks) - this.risks.get(0, 0);
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.minRisk);
	}
}
