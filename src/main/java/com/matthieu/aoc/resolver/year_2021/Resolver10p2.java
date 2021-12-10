package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;

public class Resolver10p2 extends Resolver10p1 {

	private List<Long> scores;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		
		this.values = values.stream()
							.filter(s -> this.findFirstIllegalChar(s) == null)
							.collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		
		this.scores = values.stream()
							.map(this::getCompletionString)
							.map(this::getScore)
							.collect(Collectors.toList());
		return true;
	}

	@Override
	public String get() {
		Collections.sort(this.scores);
		
		return String.valueOf(this.scores.get(this.scores.size() / 2));
	}

	private String getCompletionString(String line) {
		List<Character> openingChars = new ArrayList<>();
		StringBuilder res = new StringBuilder();
		
		for (Character c : line.toCharArray()) {
			if(this.isOpeningChar(c)) {
				openingChars.add(c);
			} else {
				openingChars.remove(openingChars.size() - 1);
			}
		}
		
		
		for (int i = openingChars.size() - 1; i >= 0; i--) {
			res.append(this.getCorresponding(openingChars.get(i)));
		}
		
		return res.toString();
	}
	
	private long getScore(String value) {
		long s = 0;
		
		for (char c : value.toCharArray()) {
			s *= 5;
			s += this.getScore(c);
		}
		
		return s;
	}
	
	@Override
	protected long getScore(char c) {
		if(c == ')')
			return 1;
		if(c == ']')
			return 2;
		if(c == '}')
			return 3;
		if(c == '>')
			return 4;
		
		throw new IllegalArgumentException("Unknown char "+ c);
	}
	
	protected char getCorresponding(char c) {
		if(c == '(')
			return ')';
		if(c == '[')
			return ']';
		if(c == '{')
			return '}';
		if(c == '<')
			return '>';
		
		throw new IllegalArgumentException("Unknown char "+ c);
	}

}
