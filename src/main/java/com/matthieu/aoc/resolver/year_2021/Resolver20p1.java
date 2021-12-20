package com.matthieu.aoc.resolver.year_2021;

import java.util.List;
import java.util.stream.Collectors;

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

		this.image.forEach((x, y, v) -> {
			
		});
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.image.stream().filter(c -> c == '#').count());
	}
	
	
	protected String getBinary(int x, int y) {
		return this.image.getRegion(x, y).stream().map(c -> c == '#' ? "1" : "0").collect(Collectors.joining());
	}
	
	protected Integer binaryToInteger(String binary) {
		return Integer.parseInt(binary, 2);
	}
	
	protected Character getAlgorithmeChar(int i) {
		return this.enhancementAlgorithm.charAt(i);
	}
	

}
