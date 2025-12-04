package com.matthieu.aoc.resolver.year_2025;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.matthieu.aoc.model.matrix.CharMatrix;

public class Resolver4p2 extends Resolver4p1 {
	
	private CharMatrix tmp;

	@Override
	public boolean solve() throws Exception {
		AtomicBoolean haveChanges = new AtomicBoolean(true);
		
		while(haveChanges.get()) {
			haveChanges.set(false);
			tmp = new CharMatrix(map.getWidth(), map.getHeight(), map::get);
		
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
					tmp.set(x, y, '.');
					haveChanges.set(true);
					
				}
			});
			
			map = tmp;
		}
		
		return true;
	}
	
}
