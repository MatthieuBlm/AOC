package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2021.SnailfishNumber;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver18p1 implements Resolver {

	protected List<String> values;
	protected SnailfishNumber snailfishNumber;
	protected List<String> numbersToAdd;
	protected int magnitude;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.values = values;
		
		snailfishNumber = new SnailfishNumber(values.get(0));

		this.numbersToAdd = new ArrayList<>();
		this.numbersToAdd.addAll(values);
		this.numbersToAdd.remove(0);
	}

	@Override
	public boolean solve() throws SolveException {

		for (String number : this.numbersToAdd) {
			this.snailfishNumber.add(number);
			
			this.snailfishNumber.reduce();
		}
		System.out.println(this.snailfishNumber);
		this.magnitude = this.snailfishNumber.getMagnitude();
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.magnitude);
	}
	
	
}
