package com.matthieu.aoc.model;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DimensionalGameOfLife {
	
	private final Object o = new Object();
	private final int dimensionNumber;
	private final int dimensionLength;
	
	private List<Object> cases;
	private List<Object> nextStates;
	
	public DimensionalGameOfLife(int dimensionNumber, int dimensionLength) {
		this.dimensionNumber = dimensionNumber;
		this.dimensionLength = dimensionLength;
		
		this.cases = IntStream.range(0, (int) Math.pow(dimensionLength, dimensionNumber))
								.mapToObj(i -> null).collect(Collectors.toList());
		this.nextStates = IntStream.range(0, (int) Math.pow(dimensionLength, dimensionNumber))
									.mapToObj(i -> null).collect(Collectors.toList());
		
		
	}
	
	public void makeCycle() {
		this.calcNextStates();
		this.applyNextStates();
	}
	
	private void calcNextStates() {
		for (int i = 0; i < cases.size(); i++) {
			int[] coords = this.getCoordsByIndex(i);
			Object cell = this.get(coords);
			
			int neightbour = this.getNeghtbourOf(coords);
			
			if((cell != null && neightbour == 2 || neightbour == 3) || (cell == null && neightbour == 3)) {
				nextStates.set(i, o);
			}
		}
	}
	
	private void applyNextStates() {
		this.cases = nextStates.stream().collect(Collectors.toList());
		this.nextStates = IntStream.range(0, (int) Math.pow(dimensionLength, dimensionNumber))
								.mapToObj(i -> null).collect(Collectors.toList());
	}
	
	public boolean isAlive(int...coords) {
		return this.get(coords) != null;
	}
	
	public void makeAlive(int...coords) {
		this.set(o, coords);
	}
	
	public void makeDead(int...coords) {
		this.set(null, coords);
	}
	
	private void set(Object object, int...coords) {
		Optional<Integer> index = this.getIndexByCoords(coords);

		if(index.isPresent()) {
			this.cases.set(index.get(), object);
		}
	}
	
	private Object get(int...coords) {
		Optional<Integer> index = this.getIndexByCoords(coords);
		
		if(index.isPresent()) {
			return this.cases.get(index.get());
		}
		
		return null;
	}
	
	private Optional<Integer> getIndexByCoords(int...coords) {
		int objectIndex = 0;
		
		for (int i = 0; i < coords.length; i++) {
			if(coords[i] < 0 || coords[i] >= this.dimensionLength) {
				return Optional.empty();
			}
			
			objectIndex += Math.pow(this.dimensionLength, i) * coords[i]; 
		}
		
		return Optional.of(objectIndex);
	}
	
	private int[] getCoordsByIndex(int index) {
		int[] coords = new int[this.dimensionNumber];
		
		for (int i = 0; i < coords.length; i++) {
			coords[i] =  (index / (int) Math.pow(this.dimensionLength, i)) % dimensionLength;
		}
		
		return coords;
	}
	
	public long getNumberOfAliveCell() {
		return this.cases.stream().filter(c -> c != null).count();
	}
	
	private int getNeghtbourOf(int...coords) {
		int result = 0;
		Iterable<String> flatCoords = this.getFlatCoord(3, coords.length);
		
		for (String flatCoord : flatCoords) {
			if(!flatCoord.chars().allMatch(c -> c == '1')) {
				
				int[] flattenCoords = this.applyFlatCoord(flatCoord, coords);
				
				if(this.isAlive(flattenCoords)) {
					result++;
				}
			}
		}
		
		
		return result;
	}
	
	private int[] applyFlatCoord(String flatCoord, int...coords) {
		int[] result = new int[coords.length];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = coords[i] + Character.getNumericValue(flatCoord.charAt(i)) - 1;
		}
		
		return result;
	}
	
	private Iterable<String> getFlatCoord(final int radix, final int digits) {
		return () -> new Iterator<String>() {

					private final String pad;
					{
						final StringBuilder buf = new StringBuilder(digits);
						for (int n = digits; n >= 0; --n) {
							buf.append('0');
						}
						pad = buf.toString();
					}

					private final int hi = (int) Math.pow(radix, digits);
					private int cursor;

					@Override
					public boolean hasNext() {
						return cursor < hi;
					}

					@Override
					public String next() {
						final String rsl = Integer.toString(cursor++, radix);
						return pad.substring(0, digits - rsl.length()) + rsl;
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
	}
}
