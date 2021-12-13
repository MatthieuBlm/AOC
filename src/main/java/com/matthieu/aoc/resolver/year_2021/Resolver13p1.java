package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver13p1 implements Resolver {

	protected Matrix<Character> paper;
	protected List<Duo<Integer, Integer>> points;
	protected List<Duo<Character, Integer>> foldInstructions;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.paper = new Matrix<>(2000, 2000, () -> '.');
		this.points = new ArrayList<>();
		this.foldInstructions = new ArrayList<>();
		
		for (String line : values) {
			if(line.length() == 0)
				continue;
			
			if(line.startsWith("fold")) {
				String coord = line.split(" ")[2];
				String[] splitted = coord.split("=");
				
				this.foldInstructions.add(new Duo<>(splitted[0].charAt(0), Integer.parseInt(splitted[1])));
			} else {
				String[] coords = line.split(",");
				
				this.points.add(new Duo<>(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
			}
		}
	}

	@Override
	public boolean solve() throws SolveException {

		this.drawDots();
		
		Duo<Character, Integer> fold = foldInstructions.get(0);
		
		this.foldX(fold.b());
		
		return true;
	}

	@Override
	public String get() {
		long pointsNumber = this.paper.stream().filter(c -> c == '#').count();
		
		return String.valueOf(pointsNumber);
	}
	
	protected void drawDots() {
		for (Duo<Integer, Integer> coords : this.points) {
			this.paper.set(coords.a(), coords.b(), '#');
		}
	}
	
	protected void foldX(int i) {
		
		this.paper.forEach((int x, int y, Character c) -> {
			if(x > i && c == '#') {
				this.paper.set(x, y, '.');
				this.paper.set(i - (x - i), y, c);
			}
		});
	}

}
