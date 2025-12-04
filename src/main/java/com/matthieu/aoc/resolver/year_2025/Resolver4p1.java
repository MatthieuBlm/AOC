package com.matthieu.aoc.resolver.year_2025;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver4p1 implements Resolver {

	protected CharMatrix map;
	protected long accessibleRolls;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new CharMatrix(values);
	}

	@Override
	public boolean solve() throws Exception {
		
		this.map.forEach((x, y, c) -> {
			if(c != '@') {
				return;
			}
			
			AtomicInteger neightboursRolls = new AtomicInteger();
			
			this.map.forEachNeigthbours(x, y, (xn, yn, cn) -> {
				if(cn == '@') {
					neightboursRolls.incrementAndGet();
				}
			});
			
			if(neightboursRolls.intValue() < 4) {
				accessibleRolls++;
			}
		});
		
		return true;
	}

	@Override
	public String get() {
		return accessibleRolls + "";
	}

}
