package com.matthieu.aoc.resolver.year_2020;

import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver11p2 extends Resolver11p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		super.freeThreshold = 5;
	}

	@Override
	protected long getNeightbourCount(int x, int y) {
		List<Duo<Integer, Integer>> vectors = Arrays.asList(
													new Duo<>(-1, -1), new Duo<>(0, -1), new Duo<>(1, -1),
											        new Duo<>(-1, 0), 					 new Duo<>(1, 0), 
											        new Duo<>(-1, 1), new Duo<>(0, 1),  new Duo<>(1, 1));
		long result = 0;
		
		for (Duo<Integer, Integer> v : vectors) {
			Duo<Integer, Integer> pos = new Duo<>(x + v.a(), y + v.b());
			
			loop:
			while(pos.a() >= 0 && pos.b() >= 0 && pos.a() <= seats.getMaxX() && pos.b() <= seats.getMaxY()) {
				switch (seats.get(pos.a(), pos.b()).getState()) {
				case OCCUPIED:
					result++;
				case EMPTY:
					break loop;
				case NONE:
					break;
				}
				
				pos.a(pos.a() + v.a());
				pos.b(pos.b() + v.b());
			}
		}
		
		return result;
	}

	
}
