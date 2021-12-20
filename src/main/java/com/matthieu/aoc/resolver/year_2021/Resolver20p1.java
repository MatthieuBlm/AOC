package com.matthieu.aoc.resolver.year_2021;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver20p1 implements Resolver {

	private static final Logger logger = LoggerFactory.getLogger(Resolver20p1.class);

	protected Matrix<Character> image;
	protected String enhancementAlgorithm;
	protected int enhancementStep;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.enhancementStep = 2;
		this.enhancementAlgorithm = values.remove(0);
		
		values.remove(0);	// Remove empty line
		
		Matrix<Character> brutImage = new Matrix<>(values, s -> s.charAt(0));
		
		image = new Matrix<>(brutImage.getWidth() * 10, brutImage.getHeight() * 10, () -> '.');
		
		int xShift = image.getWidth() / 2 - brutImage.getWidth() / 2;
		int yShift = image.getHeight() / 2 - brutImage.getHeight() / 2;
		brutImage.forEach((x, y, v) -> image.set(x + xShift, y + yShift, v));
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < this.enhancementStep; i++) {
			
			Matrix<Character> newImage = new Matrix<>(image.getWidth(), image.getHeight(), () -> '.');
			
			this.image.forEach((x, y, v) -> {
				if(x > 1 && y > 1 && x < image.getMaxX() && y < image.getMaxY()) {
					Character newC = getAlgorithmeChar(binaryToInteger(getBinary(x, y)));
					
					newImage.set(x, y, newC);
				}
			});
			
			this.image = newImage;
			
			logger.info("Step {} calculated", i);
		}
		
		return true;
	}

	@Override
	public String get() {
		// Remove borders because glitches at the edge of the map appears.
		Matrix<Character> subMatrix = this.image.submatrix(60, this.image.getWidth() - 60, 60, this.image.getHeight() - 60);

		return String.valueOf(subMatrix.stream().filter(c -> c == '#').count());
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
