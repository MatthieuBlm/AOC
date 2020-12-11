package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.model.tuple.Tuple;

public class Resolver11p2 extends Resolver11p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		super.freeThreshold = 5;
	}

	@Override
	protected long getNeightbourCount(int x, int y) {
		List<Tuple<Integer, Integer>> vectors = Arrays.asList(
													new Tuple<>(-1, -1), new Tuple<>(0, -1), new Tuple<>(1, -1),
											        new Tuple<>(-1, 0), 					 new Tuple<>(1, 0), 
											        new Tuple<>(-1, 1), new Tuple<>(0, 1),  new Tuple<>(1, 1));
		long result = 0;
		
		for (Tuple<Integer, Integer> v : vectors) {
			Tuple<Integer, Integer> pos = new Tuple<>(x + v.getKey(), y + v.getValue());
			
			loop:
			while(pos.getKey() >= 0 && pos.getValue() >= 0 && pos.getKey() <= seats.getMaxX() && pos.getValue() <= seats.getMaxY()) {
				switch (seats.get(pos.getKey(), pos.getValue()).getState()) {
				case OCCUPIED:
					result++;
				case EMPTY:
					break loop;
				case NONE:
					break;
				}
				
				pos.setKey(pos.getKey() + v.getKey());
				pos.setValue(pos.getValue() + v.getValue());
			}
		}
		
		return result;
	}

	
}
