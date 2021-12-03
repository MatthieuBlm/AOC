package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver3p2 extends Resolver3p1 {

	private List<Integer> oxygen;
	private List<Integer> co2;
	

	@Override
	public boolean solve() throws SolveException {
		
		this.calculateOxygen();
		this.calculateCo2();
		
		return true;
	}

	@Override
	public String get() {
		String oxygenString = oxygen.stream().map(String::valueOf).reduce((a,b) -> a + b).orElseThrow(NoSuchElementException::new);
		String co2String = co2.stream().map(String::valueOf).reduce((a,b) -> a + b).orElseThrow(NoSuchElementException::new);
		
		return String.valueOf(Integer.parseInt(oxygenString, 2) * Integer.parseInt(co2String, 2));
	}
	
	private void calculateOxygen() {
		List<List<Integer>> oxygenValues = new ArrayList<>();
		oxygenValues.addAll(this.values);
		
		for (int i = 0; i < values.get(0).size(); i++) {
			int mostCommon = -1;
			
			Duo<Integer, Integer> count = this.getBinaryCount(oxygenValues, i);
			
			if(count.a() <= count.b()) {
				mostCommon = 1;
			} else {
				mostCommon = 0;
			}
			
			final int mc = mostCommon;
			final int f = i;
			
			oxygenValues = oxygenValues.stream().filter(array -> array.get(f) == mc).collect(Collectors.toList());
			
			if(oxygenValues.size() <= 1) {
				break;
			}
		}

		this.oxygen = oxygenValues.get(0);
	}

	private void calculateCo2() {
		List<List<Integer>> co2Values = new ArrayList<>();
		co2Values.addAll(this.values);
		
		for (int i = 0; i < values.get(0).size(); i++) {
			int leastCommon = -1;
			
			Duo<Integer, Integer> count = this.getBinaryCount(co2Values, i);
			
			if(count.a() <= count.b()) {
				leastCommon = 0;
			} else {
				leastCommon = 1;
			}
			
			final int lc = leastCommon;
			final int f = i;
			
			co2Values = co2Values.stream().filter(array -> array.get(f) == lc).collect(Collectors.toList());
			
			if(co2Values.size() <= 1) {
				break;
			}
		}
		
		co2 = co2Values.get(0);
	}

}
