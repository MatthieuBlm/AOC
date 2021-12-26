package com.matthieu.aoc.resolver.year_2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver24p1 implements Resolver {

	protected Map<Character, Integer> memory;
	protected List<String> program;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.memory = new HashMap<>();
		this.program = values;
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

	
	protected void inp(char var, int value) {
		this.memory.put(var, value);
	}
	
	protected void add(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA + valB;
		
		this.memory.put(a, res);
		
	}
	
	protected void mod(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA % valB;
		
		this.memory.put(a, res);
	}
	
	protected void mul(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);

		int res = valA * valB;
		
		this.memory.put(a, res);
	}
	
	protected void div(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA / valB;

		this.memory.put(a, res);
	}
	
	protected void eql(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA == valB ? 1 : 0;

		this.memory.put(a, res);
	}
	
	protected int getValue(String param) {
		return this.isDigit(param) ? Integer.valueOf(param) : this.memory.get(param.charAt(0));
	}
	
	protected boolean isDigit(String s) {
		for(char c : s.toCharArray()) {
			if(c < '0' || c > '9')
				return false;
		}
		
		return true;
	}
}
