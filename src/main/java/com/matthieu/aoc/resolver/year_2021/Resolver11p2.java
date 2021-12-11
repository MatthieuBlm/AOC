package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver11p2 extends Resolver11p1 {

	private int flashSimultanously;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.steps = 1000;
	}

	@Override
	public boolean solve() throws SolveException {
		for (int i = 0; i < this.steps; i++) {
			this.haveFlashed = new Matrix<>(10, 10, () -> Boolean.FALSE);
			this.increaseEnergy();
			
			this.matrix.forEach(this::tryFlash);

			if(this.matrix.stream().allMatch(o -> o.greaterThan(9))) {
				this.flashSimultanously = i + 1;
				return true;
			}
			
			this.resetOcotpusFlashing();
		}
		
		return false;
	}

	@Override
	public String get() {
		return String.valueOf(this.flashSimultanously);
	}
}
