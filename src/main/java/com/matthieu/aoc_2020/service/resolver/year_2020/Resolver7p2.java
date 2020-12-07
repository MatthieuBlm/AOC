package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.Map;

import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.BagRule;

public class Resolver7p2 extends Resolver7p1 {

	private int result = 0;
	
	@Override
	public boolean solve() throws SolveException {
		
		BagRule shinyGold = this.mapRules.get("shiny gold");
		
		this.result = this.getNbBag(shinyGold.getColor(), 1) - 1;
		
		return true;
	}
	
	@Override
	public String get() {
		return String.valueOf(result);
	}
	
	private int getNbBag(String bagColor, Integer nbOfThisBag) {
		BagRule bag = this.mapRules.get(bagColor);
		
		if(bag.getContent().isEmpty()) {
			return nbOfThisBag;
		}
		
		int nbOfInnerBag = 1;
		for (Map.Entry<String, Integer> rule : bag.getContent().entrySet()) {
			nbOfInnerBag += this.getNbBag(rule.getKey(), rule.getValue());
		}
		
		return nbOfInnerBag * nbOfThisBag;
	}
	
}
