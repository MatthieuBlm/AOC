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
	protected int enhancementStep;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.enhancementStep = 2;
		this.enhancementAlgorithm = values.remove(0);
		
		values.remove(0);	// Remove empty line
		
		Matrix<Character> brutImage = new Matrix<>(values, s -> s.charAt(0));
		
		image = new Matrix<>(brutImage.getWidth() * 4, brutImage.getHeight() * 4, () -> '.');
		
		int xShift = image.getWidth() / 4;
		int yShift = image.getHeight() / 4;
		brutImage.forEach((x, y, v) -> image.set(x + xShift, y + yShift, v));
	}

	@Override
	public boolean solve() throws SolveException {
		System.out.println(this.image);
		
		for (int i = 0; i < this.enhancementStep; i++) {
			
			Matrix<Character> newImage = new Matrix<>(image.getWidth(), image.getHeight(), () -> null);
			
			this.image.forEach((x, y, v) -> {
				Character newC = getAlgorithmeChar(binaryToInteger(getBinary(x, y)));
				
				newImage.set(x, y, newC);
			});
			
			this.image = newImage;
			System.out.println(this.image);
		}
		
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
