package com.matthieu.aoc_2020.service.resolver;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;

public class Resolver6p1 implements Resolver {

	protected List<List<Character>> answers;
	protected int result;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.answers = new ArrayList<>();
		this.result = 0;
		
		List<Character> group = new ArrayList<>();
		this.answers.add(group);
		for (String line : values) {

			if(Strings.isBlank(line)) {
				group = new ArrayList<>();
				this.answers.add(group);
			} else {
				for (Character character : line.toCharArray()) {
					group.add(character);
				}
			}
		}

		
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (List<Character> group : this.answers) {
			int[] yesAnswers = new int[26];

			for (Character c : group) {
				yesAnswers[((int) c) - 97]++;
			}
			
			for (int i : yesAnswers) {
				if(i > 0) {
					this.result++;
				}
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.result);
	}

}
