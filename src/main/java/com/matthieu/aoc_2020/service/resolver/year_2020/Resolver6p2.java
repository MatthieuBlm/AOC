package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;

public class Resolver6p2 extends Resolver6p1 {

	private List<List<List<Character>>> answers;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.answers = new ArrayList<>();
		this.result = 0;
		
		List<List<Character>> group = new ArrayList<>();
		this.answers.add(group);
		for (String line : values) {

			if(Strings.isBlank(line)) {
				group = new ArrayList<>();
				this.answers.add(group);
			} else {
				List<Character> person = new ArrayList<>();
				for (Character character : line.toCharArray()) {
					person.add(character);
				}
				group.add(person);
			}
		}
	}
	
	@Override
	public boolean solve() throws SolveException {
		
		for (List<List<Character>> group : this.answers) {
			int[] yesAnswers = new int[26];

			for (List<Character> person : group) {
				for (Character c : person) {
					yesAnswers[((int) c) - 97]++;
				}
			}
			
			for (int i : yesAnswers) {
				if(i == group.size()) {
					this.result++;
				}
			}
		}
		
		return true;
	}
	
}
