package com.matthieu.aoc.resolver.year_2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver6p1 implements Resolver {

	protected List<Integer> lanternfishes;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.lanternfishes = Arrays.asList(values.get(0).split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < 80; i++) {
			this.simulateDay(this.lanternfishes);
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.lanternfishes.size());
	}

	protected void simulateDay(List<Integer> fishes) {
		int newFishNumber = 0;
		
		for (int i = 0; i < fishes.size(); i++) {
			int fish = fishes.get(i);
			
			if(fish > 0) {
				fish--;
				fishes.set(i, fish);
			} else if(fish == 0) {
				fishes.set(i, 6);
				newFishNumber++;
			}
		}
		
		for (int i = 0; i < newFishNumber; i++) {
			fishes.add(8);
		}
	}
	
	protected void printFishes() {
		this.lanternfishes.forEach(i -> {
			String f = i + ",";
			System.out.print(f);
		});
	}

}
