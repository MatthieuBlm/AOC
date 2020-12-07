package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.BagRule;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver7p1 implements Resolver {

	protected List<BagRule> rules;
	protected Map<String, BagRule> mapRules;
	protected List<String> canContainSearchingColorBag;
	protected String searchingColor;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.rules = values.stream().map(BagRule::new).collect(Collectors.toList());
		this.mapRules = rules.stream().collect(Collectors.toMap(BagRule::getColor, bagRule -> bagRule));
		this.canContainSearchingColorBag = new ArrayList<>();
		this.searchingColor = "shiny gold";
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (BagRule bagRule : rules) {
			if(bagRule.getColor().equals(searchingColor)) 
				continue;
			
			List<BagRule> toExplore = new ArrayList<>();
			toExplore.add(bagRule);
			
			outerloop:
			while(!toExplore.isEmpty()) {
				toExplore = toExplore.stream()
								.map(bag -> bag.getContent().keySet())
								.flatMap(Set::stream)
								.map(key -> mapRules.get(key))
								.filter(Objects::nonNull)
								.collect(Collectors.toList());
				
				for (BagRule rule : toExplore) {
					if(rule.getColor().equals(searchingColor) && !this.canContainSearchingColorBag.contains(rule.getColor())) {
						this.canContainSearchingColorBag.add(bagRule.getColor());
						break outerloop;
					}
				}
				
				toExplore = toExplore.stream().filter(bag -> !bag.getColor().equals(searchingColor)).collect(Collectors.toList());
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.canContainSearchingColorBag.size());
	}

}
