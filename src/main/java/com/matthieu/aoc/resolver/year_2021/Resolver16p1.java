package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver16p1 implements Resolver {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean solve() throws SolveException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

	protected StringBuilder hexToBenary(String value) {
		StringBuilder result = new StringBuilder();
		
		for (char c : value.toCharArray()) {
			result.append(this.getBinary(c));
		}
		
		return result;
	}
	
	protected String getBinary(char hex) {
		switch(hex) {
		case '0':
			return "0000";
		case '1':
			return "0001";
		case '2':
			return "0010";
		case '3':
			return "0011";
		case '4':
			return "0100";
		case '5':
			return "0101";
		case '6':
			return "0110";
		case '7':
			return "0111";
		case '8':
			return "1000";
		case '9':
			return "1001";
		case 'A':
			return "1010";
		case 'B':
			return "1011";
		case 'C':
			return "1100";
		case 'D':
			return "1101";
		case 'E':
			return "1110";
		case 'F':
			return "1111";
		default:
			throw new IllegalArgumentException("Unknown char "+ hex);
		}
	}
}
