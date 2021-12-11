package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.I;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver11p1 implements Resolver {

	protected Matrix<I> matrix;
	protected Matrix<Boolean> haveFlashed;
	protected long flashNumber;
	protected int steps;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.matrix = new Matrix<>(values, s -> new I(Integer.parseInt(s)));
		this.steps = 100;
	}

	@Override
	public boolean solve() throws SolveException {

		for (int i = 0; i < this.steps; i++) {
			this.haveFlashed = new Matrix<>(10, 10, () -> Boolean.FALSE);
			this.increaseEnergy();
			
			this.matrix.forEach(this::tryFlash);
			
			this.resetOcotpusFlashing();
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.flashNumber);
	}
	
	protected void increaseEnergy() {
		this.matrix.stream().forEach(I::increase);
	}
	
	protected void resetOcotpusFlashing() {
		this.matrix.stream()
					.filter(o -> o.greaterThan(9))
					.forEach(o -> o.set(0));
	}
	
	protected void tryFlash(int x, int y, I octopus) {
		if(octopus.greaterThan(9) && Boolean.FALSE.equals(this.haveFlashed.getQuietly(x, y))) {
			this.haveFlashed.set(x, y, Boolean.TRUE);
			this.flashNumber++;
			
			this.matrix.neightbours(x, y).forEach(I::increase);
			this.matrix.forEachNeigthbours(x, y, this::tryFlash);
		}
	}
}
