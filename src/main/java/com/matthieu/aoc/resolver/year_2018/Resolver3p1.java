package com.matthieu.aoc.resolver.year_2018;

import java.awt.Rectangle;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver3p1 implements Resolver {

	protected List<Rectangle> rects;
	protected int[][] claims;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<Rectangle> parser = s -> {
			String[] splited = s.split(" ");
			String[] coord = splited[2].replace(":", "").split(",");
			String[] size = splited[3].split("x");
			return new Rectangle(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]), Integer.parseInt(size[0]), Integer.parseInt(size[1]));
		};

		rects = values.stream().map(parser::parse).collect(Collectors.toList());
		claims = new int[1500][1500];
	}

	@Override
	public boolean solve() throws SolveException {
		for (Rectangle r : this.rects) {
			for (int i = r.x; i < r.x + r.getWidth(); i++) {
				for (int j = r.y; j < r.y + r.getHeight(); j++) {
					
					this.claims[i][j]++;
					
				}
			}
		}
		return true;
	}

	@Override
	public String get() {
		int result = 0;
		
		for (int i = 0; i < claims.length; i++) {
			for (int j = 0; j < claims[0].length; j++) {
				if(claims[i][j] >= 2) {
					result++;
				}
			}
		}
		
		return String.valueOf(result);
	}

}
