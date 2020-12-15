package com.matthieu.aoc.resolver.year_2020;

import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.matrix.Row;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;
import com.matthieu.aoc.service.parser.Parsers;

public class Resolver2p2 implements Resolver {

	private Matrix<String> data;
	private int validPassword;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.data = new Matrix<>(values, s -> s);
		this.validPassword = 0;
	}

	@Override
	public boolean solve() throws SolveException {
		Parser<Integer[]> boundParser = s -> Arrays.asList(s.split("-")).stream()
												.map(Parsers.toInt()::parse)
												.map(i -> i - 1)
												.toArray(Integer[]::new);
		
		for(Row<String> row : data.getRows()) {
			Integer[] bounds = row.get(0, boundParser);
			char c = row.get(1, s -> s.charAt(0));
			
			boolean lowerBoundOk = (row.get(2).length() - 1) >= bounds[0];
			boolean upperBoundOk = (row.get(2).length() - 1) >= bounds[1];
			
			lowerBoundOk &= row.get(2).charAt(bounds[0]) == c;
			upperBoundOk &= row.get(2).charAt(bounds[1]) == c;
			
			if(lowerBoundOk ^ upperBoundOk)
				validPassword++;
			
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.validPassword);
	}
}
