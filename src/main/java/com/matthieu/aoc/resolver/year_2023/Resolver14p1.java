package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver14p1 implements Resolver {

	protected CharMatrix map;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new CharMatrix(values);
	}

	@Override
	public boolean solve() throws Exception {
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				
				if(map.get(x, y) == 'O') {
					for (int newY = y - 1; newY >= 0 && map.get(x, newY) != '#' && map.get(x, newY) != 'O'; newY--) {
						this.map.set(x, newY, 'O');
						this.map.set(x, newY + 1, '.');
					}
				}
				
			}
		}
		
		System.out.println(map);
		
		return true;
	}

	@Override
	public String get() {
		return IntStream.range(0, this.map.getHeight())
				.map(y -> map.getRow(y).stream()
						.filter(c -> c == 'O')
						.mapToInt(rock -> map.getHeight() - y)
						.sum())
				.sum() + "";
	}

}
