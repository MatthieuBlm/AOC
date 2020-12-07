package com.matthieu.aoc_2020.model.travel_bag;

import java.util.HashMap;
import java.util.Map;

public class BagRule {

	private String color;
	private Map<String, Integer> content;
	
	public BagRule(String rule) {
		this.content = new HashMap<>();
		
		String[] splitA = rule.split("bags contain");
		this.color = splitA[0].strip();
		
		String[] containsRules = splitA[1].split(",");
		
		for(String bagRule : containsRules) {
			String [] rules = bagRule.replace("bags", "")
										.replace("bag", "")
										.strip()
										.split(" ");
			
			if(!rules[0].equals("no")) {
				content.put(rules[1] + " " + rules[2], Integer.parseInt(rules[0]));
			}
		}
	}
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Map<String, Integer> getContent() {
		return content;
	}
	public void setContent(Map<String, Integer> content) {
		this.content = content;
	}
}
