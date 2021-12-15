package com.matthieu.aoc.model.year_2020;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.tuple.Duo;

public class TicketField {
	private String name;
	
	private List<Duo<Integer, Integer>> possibleValues;
	
	public TicketField(String name, String possibleValueA, String possibleValueB) {
		this.name = name.replace(" ", "-");
		
		String [] possibleA = possibleValueA.split("-");
		String [] possibleB = possibleValueB.split("-");

		possibleValues = new ArrayList<>();

		possibleValues.add(new Duo<>(Integer.parseInt(possibleA[0]), Integer.parseInt(possibleA[1])));
		possibleValues.add(new Duo<>(Integer.parseInt(possibleB[0]), Integer.parseInt(possibleB[1])));
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public boolean isPossibleValue(int value) {
		for (Duo<Integer, Integer> duo : possibleValues) {
			if(value >= duo.a() && value <= duo.b())
				return true;
		}
		
		return false;
	}
}
