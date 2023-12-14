package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.service.Extractor;
import com.matthieu.aoc.service.ListUtils;

public class Resolver14p2 extends Resolver14p1 {

	private static final Direction[] cycleDescriDirections = {Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST};
	private List<String> loads = new ArrayList<>();
	private int northLoad;
	
	@Override
	public boolean solve() throws Exception {
		for (int i = 0; i < 1000; i++) {
			doCycle();
			
			// Look for loop
			// If last calculated load already exists
			if(loads.stream().filter(load -> load.equals(ListUtils.getLast(loads))).count() == 2) {
				String headOfLoop = this.loads.remove(loads.size() - 1);
				
				int startOfLoop = this.loads.indexOf(headOfLoop);
				int loopSize = this.loads.size() - startOfLoop;
				int indexOfNorthLoadInLoop = (1_000_000_000 - startOfLoop) % loopSize;
				
				northLoad = Integer.parseInt(Extractor.extractWords(this.loads.get(indexOfNorthLoadInLoop + startOfLoop - 1)).get(3));
				
				break;
			}
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return northLoad + "";
	}
	
	private void doCycle() {
		StringBuilder loadBuilder = new StringBuilder();
		
		for (Direction direction : cycleDescriDirections) {
			this.tilt(direction);
			loadBuilder.append(getNorthLoad()).append(" ");
		}
		
		this.loads.add(loadBuilder.toString());
	}
}
