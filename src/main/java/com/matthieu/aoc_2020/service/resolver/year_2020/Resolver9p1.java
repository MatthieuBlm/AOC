package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver9p1 implements Resolver {

	protected int preambleSize;
	protected List<Long> code;
	protected Long firstIntruder;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.preambleSize = 25;
		this.code = values.stream().map(Long::valueOf).collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {

		for (int i = this.preambleSize; i < this.code.size(); i++) {
			Long n = this.code.get(i);
			List<Long> sublist = this.code.subList(i - preambleSize, i);
			
			
			if(!this.isSumOf(n, sublist)) {
				this.firstIntruder = n;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String get() {
		return String.valueOf(this.firstIntruder);
	}
	
	protected boolean isSumOf(Long number, List<Long> list) {
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				
				if(i != j && (list.get(i) + list.get(j)) == number) {
					return true;
				}
				
			}
		}
		
		return false;
	}

}
