package com.matthieu.aoc.resolver.year_2020;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.DimensionalGameOfLife;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver17p1 implements Resolver {

	protected DimensionalGameOfLife game;
	protected int dimensionLength;
	protected int dimensionNumber;
	protected int initializationOffset;
	
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.dimensionLength = 25;
		this.dimensionNumber = 3;
		
		this.game = new DimensionalGameOfLife(dimensionNumber, dimensionLength);
		
		initializationOffset = (dimensionLength / 2) - (values.size() / 2);
		
		for (int i = 0; i < values.size(); i++) {
			for (int j = 0; j < values.get(0).length(); j++) {
				if(values.get(i).charAt(j) == '#') {
					game.makeAlive(j + initializationOffset, i + initializationOffset, initializationOffset);
				}
			}
		}
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < 6; i++) {
			this.game.makeCycle();
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.game.getNumberOfAliveCell());
	}
	
	private void printDimension(int z) {
		for (int i = 0; i < dimensionLength; i++) {
			for (int j = 0; j < dimensionLength; j++) {
				
				if(this.game.isAlive(j, i, z)) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
			}
			System.out.println("");
		}
		
		System.out.println("\n");
	}

}
