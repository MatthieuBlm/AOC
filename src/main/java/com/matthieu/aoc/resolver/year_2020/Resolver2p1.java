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

public class Resolver2p1 implements Resolver {

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
													.toArray(Integer[]::new);
		
		for (Row<String> row : this.data.getRows()) {
			Integer[] bounds = row.get(0, boundParser);
			char letter = row.get(1, s -> s.charAt(0));
			
			int frequency = 0;
			
			for(char c : row.get(2, String::toCharArray)) {
				if(c == letter) {
					frequency++;
				}
			}
			
			if(frequency >= bounds[0] && frequency <= bounds[1]) {
				this.validPassword++;
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.validPassword);
	}

}
