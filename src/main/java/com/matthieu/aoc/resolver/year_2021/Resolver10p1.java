package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver10p1 implements Resolver {

	protected List<String> values;
	protected long score;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.values = values;
	}

	@Override
	public boolean solve() throws SolveException {
		
		this.score = this.values.stream()
								.map(this::findFirstIllegalChar)
								.filter(Objects::nonNull)
								.mapToLong(this::getScore)
								.sum();
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.score);
	}
	
	protected Character findFirstIllegalChar(String line) {
		List<Character> openingChars = new ArrayList<>();
		
		for (Character c : line.toCharArray()) {
			
			if(this.isOpeningChar(c)) {
				openingChars.add(c);
			} else {
				Character opening = openingChars.remove(openingChars.size() - 1);
				
				if(!this.correspond(opening, c))
					return c;
			}
			
		}
		
		return null;
	}

	protected boolean isOpeningChar(char c) {
		return c == '{' || c == '(' || c == '<' || c == '[';
	}
	
	protected boolean isClosingChar(char c) {
		return c == '}' || c == ')' || c == '>' || c == ']';
	}
	
	protected boolean correspond(char opening, char closing) {
		return (opening == '{' && closing == '}') ||
				(opening == '[' && closing == ']') || 
				(opening == '<' && closing == '>') || 
				(opening == '(' && closing == ')'); 
	}
	
	protected long getScore(char c) {
		if(c == ')')
			return 3;
		if(c == ']')
			return 57;
		if(c == '}')
			return 1197;
		if(c == '>')
			return 25137;
		
		throw new IllegalArgumentException("Unknown char "+ c);
	}
	
}
