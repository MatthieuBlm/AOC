package com.matthieu.aoc.model.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.matthieu.aoc.model.tuple.Duo;

public class DigitDisplay {

	private Map<Character, Character> translation;
	
	private List<Character> oneChars;
	private List<Character> fourChars;
	private List<Character> sevenChars;
	
	private List<Character> segmentLeftToAssociate;
	
	
	public DigitDisplay(String one, String four, String seven) {
		this.translation = new HashMap<>();
		this.segmentLeftToAssociate = new ArrayList<>();
		
		this.segmentLeftToAssociate.add('a');
		this.segmentLeftToAssociate.add('b');
		this.segmentLeftToAssociate.add('c');
		this.segmentLeftToAssociate.add('d');
		this.segmentLeftToAssociate.add('e');
		this.segmentLeftToAssociate.add('f');
		this.segmentLeftToAssociate.add('g');
		
		this.oneChars = this.toCharList(one.toCharArray());
		this.fourChars = this.toCharList(four.toCharArray());
		this.sevenChars = this.toCharList(seven.toCharArray());
	}
	
	public void initialize(List<String> values) {
		List<Character> sevenMinusOne = this.soustract(sevenChars, oneChars);
		
		translation.put(sevenMinusOne.get(0), 'a');
		this.found(sevenMinusOne.get(0));
		
		List<String> sixCandidate = values.stream().filter(s -> s.length() == 6).collect(Collectors.toList());
		
		Duo<Character, Character> six = this.findThatNotContainOneOf(sixCandidate, this.oneChars.get(0), this.oneChars.get(1));
		
		translation.put(six.a(), 'f');
		translation.put(six.b(), 'c');
		this.found(six.a());
		this.found(six.b());
		
		
		List<Character> fourMinusOne = this.soustract(this.fourChars, this.oneChars);
		
		Duo<Character, Character> zero = this.findThatNotContainOneOf(sixCandidate, fourMinusOne.get(0), fourMinusOne.get(1));
		
		translation.put(zero.a(), 'b');
		translation.put(zero.b(), 'd');
		this.found(zero.a());
		this.found(zero.b());
		
		Duo<Character, Character> nine = this.findThatNotContainOneOf(sixCandidate, this.segmentLeftToAssociate.get(0), this.segmentLeftToAssociate.get(1));
		
		translation.put(nine.a(), 'g');
		translation.put(nine.b(), 'e');
	}
	
	public String translate(String segments) {
		StringBuilder builder = new StringBuilder();
		
		for (char c : segments.toCharArray()) {
			builder.append(this.translation.get(c));
		}
		
		return builder.toString();
	}
	
	public int getDigit(String segments) {
		String translated = this.translate(segments);
		
		char[] ch = translated.toCharArray();
		Arrays.sort(ch);
		String s = String.valueOf(ch);
		
		switch(s) {
		case "abcefg":
			return 0;
		case "cf":
			return 1;
		case "acdeg":
			return 2;
		case "acdfg":
			return 3;
		case "bcdf":
			return 4;
		case "abdfg":
			return 5;
		case "abdefg":
			return 6;
		case "acf":
			return 7;
		case "abcdefg":
			return 8;
		case "abcdfg":
			return 9;
		default:
			return -1;
		}
	}
	
	private List<Character> toCharList(char[] chars) {
		List<Character> result = new ArrayList<>();
		
		for (char c : chars) {
			result.add(c);
		}
		
		return result;
	}
	
	private List<Character> soustract(List<Character> a, List<Character> b) {
		List<Character> result = new ArrayList<>();
		
		result.addAll(a);
		
		for (Character character : b) {
			result.remove(character);
		}
		
		return result;
	}
	
	// Duo<present, missing>
	private Duo<Character, Character> findThatNotContainOneOf(List<String> values, Character a, Character b) {
		
		for (String v : values) {
			if(v.indexOf((char)a) < 0 && v.indexOf((char) b) >= 0) {
				return new Duo<>(b, a);
			} else if(v.indexOf((char)a) >= 0 && v.indexOf((char) b) < 0) {
				return new Duo<>(a, b);
			}
		}
		
		return null;
	}
	
	private void found(Character c) {
		this.segmentLeftToAssociate.remove(c);
	}
}
