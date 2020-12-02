package com.matthieu.aoc_2020.service.resolver;

import java.util.List;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.Matrix;

public class Resolver2p1 implements Resolver {

	private Matrix data;
	private int validPassword;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.data = new Matrix(values);
		this.validPassword = 0;
	}

	@Override
	public boolean solve() throws SolveException {
		for (String [] row : data.getDatas()) {
			String[] splitedLimit = row[0].split("-");
			int a = Integer.parseInt(splitedLimit[0]);
			int b = Integer.parseInt(splitedLimit[1]);
			
			char letter = row[1].charAt(0);
			
			int frequency = 0;
			
			for (char c : row[2].toCharArray()) {
				if(c == letter) {
					frequency++;
				}
			}
			
			if(frequency >= a && frequency <= b) {
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
