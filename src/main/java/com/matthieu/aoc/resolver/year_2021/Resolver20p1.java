package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver20p1 implements Resolver {

	protected Matrix<Character> image;
	protected String enhancementAlgorithm;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.enhancementAlgorithm = values.remove(0);
		
		values.remove(0);	// Remove empty line
		
		Matrix<Character> brutImage = new Matrix<>(values, s -> s.charAt(0));
		
		image = new Matrix<>(brutImage.getWidth() * 2, brutImage.getHeight() * 2, () -> '.');
	}

	@Override
	public boolean solve() throws SolveException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected String getBinary(int x, int y) {
		// TODO 
		return null;
	}

}
