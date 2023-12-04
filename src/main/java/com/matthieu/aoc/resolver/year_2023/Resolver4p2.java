package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.SolveException;

public class Resolver4p2 extends Resolver4p1 {

	private List<List<Card>> copyedCards;
	
	@Override
	public boolean solve() throws SolveException {
		this.copyedCards = this.cards.stream().map(c -> {
				List<Card> originalCards = new ArrayList<>();
				originalCards.add(c);
				return originalCards;
			}).toList();
		
		for (int i = 0; i < cards.size(); i++) {
			List<Card> cardToCalc = this.copyedCards.get(i); // All the same cards
			List<Integer> cardWinningNumbers = this.findWinningNumbres(cardToCalc.get(0));
			
			for (Card c : cardToCalc) {
				for (int j = 1; j <= cardWinningNumbers.size(); j++) {
					if(i + j > this.cards.size()) {
						break;
					}
					
					this.copyedCards.get(i + j).add(this.cards.get(i + j));
				}
			}
		}
		
		this.points = this.copyedCards.stream().flatMap(List::stream).count();
		return true;
	}
}
