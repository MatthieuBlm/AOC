package com.matthieu.aoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {
	
	private static final Pattern MATCH_NUMBERS = Pattern.compile("-?\\d+");
	private static final Pattern MATCH_LETTERS = Pattern.compile("\\w+");

	private Extractor() {}
	
	public static List<Long> extractNumbers(String line) {
		 List<Long> numbers = new ArrayList<>();

        Matcher matcher = MATCH_NUMBERS.matcher(line);

        while (matcher.find()) {
            numbers.add(Long.parseLong(matcher.group()));
        }

        return numbers;
	}
	
	public static List<String> extractWords(String line) {
		List<String> words = new ArrayList<>();
		
		Matcher matcher = MATCH_LETTERS.matcher(line);
		
		while (matcher.find()) {
			words.add(matcher.group());
		}
		
		return words;
	}
}
