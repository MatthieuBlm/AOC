package com.matthieu.aoc.resolver.year_2025;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver1p1 implements Resolver {

	protected List<Duo<Character, Integer>> rotations;
	protected int position;
	protected int zeroCount;
	protected int totalClick;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.totalClick = 100;
		this.position = totalClick * 10_000 + 50;
		this.rotations = values.stream()
				.map(line -> new Duo<>(line.charAt(0), Integer.parseInt(line.substring(1))))
				.toList();
	}

	@Override
	public boolean solve() throws Exception {
		for (Duo<Character, Integer> rotation : rotations) {

			if(rotation.a() == 'L') {
				position -= rotation.b();
			} else {
				position += rotation.b();
			}
			
			if(position % 100 == 0) {
				zeroCount++;
			}
			
		}
		
		return true;
	}

	@Override
	public String get() {
		return zeroCount + "";
	}
	

}
