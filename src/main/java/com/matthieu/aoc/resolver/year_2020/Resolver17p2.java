package com.matthieu.aoc.resolver.year_2020;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.DimensionalGameOfLife;

public class Resolver17p2 extends Resolver17p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.dimensionLength = 25;
		this.dimensionNumber = 4;
		
		this.game = new DimensionalGameOfLife(dimensionNumber, dimensionLength);
		
		initializationOffset = (dimensionLength / 2) - (values.size() / 2);
		
		for (int i = 0; i < values.size(); i++) {
			for (int j = 0; j < values.get(0).length(); j++) {
				if(values.get(i).charAt(j) == '#') {
					game.makeAlive(j + initializationOffset, i + initializationOffset, initializationOffset, initializationOffset);
				}
			}
		}
	}
	
}
