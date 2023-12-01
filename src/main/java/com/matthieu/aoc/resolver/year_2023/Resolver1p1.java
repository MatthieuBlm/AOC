package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver1p1 implements Resolver {
	
	private int result;
	protected List<Integer> calibration = new ArrayList<>();

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		values.stream().forEach(line -> {
			String n = "";
			
			for (int i = 0; i < line.length(); i++) {
				if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
					n += Integer.parseInt(line.charAt(i) + "");
					break;
				}
			}

			for (int i = line.length() - 1; i >= 0; i--) {
				if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
					n += Integer.parseInt(line.charAt(i) + "");
					break;
				}
			}
			
			calibration.add(Integer.parseInt(n));
		});
	}

	@Override
	public boolean solve() throws SolveException {
		this.result = this.calibration.stream().mapToInt(Integer::intValue).sum();
		return true;
	}

	@Override
	public String get() {
		return this.result + "";
	}

}
