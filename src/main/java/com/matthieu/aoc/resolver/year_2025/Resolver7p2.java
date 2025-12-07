package com.matthieu.aoc.resolver.year_2025;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver7p2 extends Resolver7p1 {

	private Matrix<Long> beamCounts;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		beamCounts = new Matrix<>(map.getWidth(), map.getHeight(), () -> 0l);
	}
	
	@Override
	public boolean solve() throws Exception {
		
		int beamIndex = this.map.getRow(1).get().indexOf('|');
		beamCounts.set(beamIndex, 1, 1l);

		for (int y = 1; y < this.map.getHeight(); y++) {
			for (int x = 0; x < this.map.getWidth(); x++) {
				char c = map.get(x, y);
				
				// Not on last row
				if(y + 1 <= map.getMaxY()) {
					
					// On beam
					if(c == '|') {
						
						// Continue beam
						if(map.get(x, y + 1) == '.' || map.get(x, y + 1) == '|') {
							map.set(x, y + 1, '|');
							beamCounts.set(x, y + 1, beamCounts.get(x, y) + beamCounts.get(x, y + 1));
						}
						
						// Split
						if(map.get(x, y + 1) == '^' && (map.get(x - 1, y + 1) != '|' || map.get(x + 1, y + 1) != '|')) {

							long currentBeamCount = beamCounts.get(x, y);
							
							map.set(x - 1, y + 1, '|');
							map.set(x + 1, y + 1, '|');
							
							beamCounts.set(x - 1, y + 1, beamCounts.get(x - 1, y + 1) + currentBeamCount);
							beamCounts.set(x + 1, y + 1, beamCounts.get(x + 1, y + 1) + currentBeamCount);
						}
					}
				}
				
			}
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return this.beamCounts.getRow(beamCounts.getMaxY()).stream().mapToLong(Long::longValue).sum() + "";
	}
	
	public String printBeamCounts() {
		StringBuilder builder = new StringBuilder();
		
		for (int y = 1; y < this.map.getHeight(); y++) {
			for (int x = 0; x < this.map.getWidth(); x++) {
				
				if(map.get(x, y) == '|') {
					builder.append(beamCounts.get(x, y));
				} else {
					builder.append(map.get(x, y));
				}
				
			}
			
			builder.append("\n");
		}
		
		return builder.toString();
	}
}
