package com.matthieu.aoc.resolver.year_2025;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver7p1 implements Resolver {

	protected CharMatrix map;
	protected long splitCount;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new CharMatrix(values);
		
		int sIndex = this.map.getRow(0).get().indexOf('S');
		
		this.map.set(sIndex, 1, '|');
	}

	@Override
	public boolean solve() throws Exception {
		
		AtomicBoolean hasChanges = new AtomicBoolean(true);
		
		while(hasChanges.get()) {
			hasChanges.set(false);
			
			this.map.forEach((x, y, c) -> {
				// Not on last row
				if(y + 1 <= this.map.getMaxY()) {
					
					// On beam
					if(c == '|') {
						
						// Continue beam
						if(this.map.get(x, y + 1) == '.') {
							this.map.set(x, y + 1, '|');
							hasChanges.set(true);
						}
						
						// Split
						if(this.map.get(x, y + 1) == '^' && (this.map.get(x - 1, y + 1) != '|' || this.map.get(x + 1, y + 1) != '|')) {
							
							splitCount++;
							this.map.set(x - 1, y + 1, '|');
							this.map.set(x + 1, y + 1, '|');
							hasChanges.set(true);
						}
					}
				}
			});
		}
		
		return true;
	}

	@Override
	public String get() {
		return splitCount + "";
	}

}
