package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver3p1 implements Resolver {

	protected Matrix<Character> schema;
	protected List<Integer> partNumber = new ArrayList<>();
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		schema = new Matrix<>(values, s -> s.charAt(0), "");
	}

	@Override
	public boolean solve() throws SolveException {
		
		this.schema.forEach((x, y, c) -> {
			if(!isNumber(c) && c.charValue() != '.') {
				this.partNumber.addAll(this.getNumerAround(x, y));
			}
		});
		
		return true;
	}

	@Override
	public String get() {
		return this.partNumber.stream().mapToInt(Integer::intValue).sum() + "";
	}
	
	protected List<Integer> getNumerAround(int x, int y) {
		List<Integer> numbers = new ArrayList<>();
		
		this.schema.forEachNeigthbours(x, y, (xn, yn, cn) -> {
			int n = this.getNumber(xn, yn);
			
			if(n != 0) {
				numbers.add(n);
			}
		});
		
		return numbers;
	}
	
	protected int getNumber(int x, int y) {
		if(!isNumber(schema.get(x, y))) {
			return 0;
		}
		
		int xHead = x;
		
		while(--xHead >= 0 && isNumber(schema.get(xHead, y))) {}
		
		String n = "";
		Character pointer;

		while(++xHead <= this.schema.getMaxX() && isNumber(pointer = schema.get(xHead, y))) {
			n += pointer;
			this.schema.set(xHead, y, '.');
		}
		
		if(n.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(n);
		}
	}

	protected boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}
}
