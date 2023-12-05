package com.matthieu.aoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {
	
	private static final Pattern MATCH_NUMBERS = Pattern.compile("\\d+");

	private Extractor() {}
	
	public static List<Long> extractNumbers(String line) {
		 List<Long> numbers = new ArrayList<>();

        Matcher matcher = MATCH_NUMBERS.matcher(line);

        while (matcher.find()) {
            String match = matcher.group();
            numbers.add(Long.parseLong(match));
        }

        return numbers;
	}
}
