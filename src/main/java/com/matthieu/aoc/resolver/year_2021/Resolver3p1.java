package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver3p1 implements Resolver {

	protected List<List<Integer>> values;
	private StringBuilder gamma;
	private StringBuilder epsilon;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<List<Integer>> parser = s -> {
			List<Integer> r = new ArrayList<>();
			for (char c : s.toCharArray()) {
				r.add(Character.getNumericValue(c));
			}
			
			return r;
		};
		
		this.values = values.stream().map(parser::parse).collect(Collectors.toList());
		this.gamma = new StringBuilder();
		this.epsilon = new StringBuilder();
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < values.get(0).size(); i++) {
			Duo<Integer, Integer> count = this.getBinaryCount(this.values, i);
			
			if(count.a() < count.b()) {
				gamma.append("1");
				epsilon.append("0");
			} else {
				gamma.append("0");
				epsilon .append("1");
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(Integer.parseInt(this.gamma.toString(), 2) * Integer.parseInt(this.epsilon.toString(), 2));
	}
	

	protected Duo<Integer, Integer> getBinaryCount(List<List<Integer>> data, int pos) {
		Duo<Integer, Integer> res = new Duo<>(0, 0);
		
		for(List<Integer> n : data) {
			if(n.get(pos) == 0) {
				res.a(res.a() + 1);
			} else {
				res.b(res.b() + 1);
			}
		}
		
		return res;
	}
}
