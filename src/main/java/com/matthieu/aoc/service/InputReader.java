package com.matthieu.aoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class InputReader {

	public List<String> getInputAsStringArray(String data) {
		Scanner scanner = new Scanner(data);
		List<String> result = new ArrayList<>();
		
		while(scanner.hasNextLine()) {
			result.add(scanner.nextLine());
		}
		
		scanner.close();
		return result;
	}

	public List<Integer> getInputAsIntegerArray(String data) {
		return this.getInputAsStringArray(data).stream()
						.map(Integer::valueOf)
						.collect(Collectors.toList());
	}
	
}
