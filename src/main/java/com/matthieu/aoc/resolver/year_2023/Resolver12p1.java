package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.CustomBaseNumber;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver12p1 implements Resolver {
	
	protected List<ConditionRecords> conditionsRecords;
	protected List<Integer> possibilities;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.conditionsRecords = values.stream()
				.map(value -> value.split(" "))
				.map(splittedValue -> new ConditionRecords(splittedValue[0], Extractor.extractNumbers(splittedValue[1])))
				.toList();
	}
	
	@Override
	public boolean solve() throws Exception {
		possibilities = conditionsRecords.stream().map(records -> {
			CustomBaseNumber cbn = new CustomBaseNumber(records.groups().size(), records.conditions.length());
			Set<String> recordsPossibilities = new HashSet<>();
			
			while(!cbn.isMaxValue()) {
				// Search for next usable number
				while(!isPossiblePositionNumber(cbn.increment(), records) && !cbn.isMaxValue()) {};
				
				String possibility = generateConditions(records.conditions().length(), records.groups(), cbn);
				
				if(isValidSprings(possibility, records)) {
					recordsPossibilities.add(possibility);
				}
			}
			
			return recordsPossibilities.size();
		}).toList();
		
		return true;
	}

	@Override
	public String get() {
		return this.possibilities.stream().mapToInt(Integer::intValue).sum() + "";
	}
	
	// Ex: ???.### 1,1,3
	private boolean isPossiblePositionNumber(CustomBaseNumber n, ConditionRecords condition) {
		for (int i = 0; i < n.length() - 1; i++) {
			// Make sure to have each group in right order
			if(n.getDecimalValue(i) >= n.getDecimalValue(i + 1)) {
				return false;
			}
		}
			
		for (int i = 0; i < n.length() - 1; i++) {
			// Make sure there is no overlap between groups
			if(n.getDecimalValue(i) + condition.groups().get(i) >= n.getDecimalValue(i + 1)) {
				return false;
			}
		}

		return true;
	}
	
	protected String generateConditions(int conditionLength, List<Long> groups, CustomBaseNumber step) {
		StringBuilder result = new StringBuilder();
		
		// Build empty conditions
		for (int i = 0; i < conditionLength; i++) {
			result.append('.');
		}
		
		for (int i = 0; i < groups.size(); i++) {
			int group = groups.get(i).intValue();
			
			// Build group string to insert
			StringBuilder groupStringBuilder = new StringBuilder();
			for (int j = 0; j < group; j++) {
				groupStringBuilder.append('#');
			}
			
			String groupString = groupStringBuilder.toString(); // Something like '###'
			int insertIndex = Math.min(step.getDecimalValue(i), result.length() - groupString.length());
			
			// Insert groupString at right position
			result.replace(insertIndex, insertIndex + group, groupString);
		}
		
		return result.toString();
	}

	protected boolean isValidSprings(String conditionsToValidate, ConditionRecords records) {
	List<Long> foundGroups = new ArrayList<>();
		long currentGroupSize = 0;
		
		for (int i = 0; i < conditionsToValidate.length(); i++) {
			char expected = records.conditions.charAt(i);
			char toValidate = conditionsToValidate.charAt(i);
			
			// '.' and '#' should stay
			if((expected == '.' && toValidate != '.') || (expected == '#' && toValidate != '#')) {
				return false;
			}

			// Build found groups
			if(toValidate == '#') {
				currentGroupSize++;
				
			} else if(toValidate == '.' && currentGroupSize > 0) {
				foundGroups.add(currentGroupSize);
				currentGroupSize = 0;
			}
		}
		
		// Add last group is any
		if(currentGroupSize > 0) {
			foundGroups.add(currentGroupSize);
		}
		
		// All expected groups should be present
		return foundGroups.equals(records.groups());
	}
	
	protected record ConditionRecords(String conditions, List<Long> groups) {}

}
