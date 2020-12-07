package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver1p2 implements Resolver {
	private List<Integer> datas;
	private int result;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.datas = values.stream().map(Integer::valueOf).collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		for (Integer a : datas) {
			for (Integer b : datas) {
				for (Integer c : datas) {
					if(a + b + c== 2020) {
						result = a * b * c;
						return true;
					}
				}
			}
		}
		
		throw new SolveException("Hasn't found response");
	}

	@Override
	public String get() {
		return String.valueOf(result);
	}

}
