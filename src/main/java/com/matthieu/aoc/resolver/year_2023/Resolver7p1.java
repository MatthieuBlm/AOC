package com.matthieu.aoc.resolver.year_2023;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver7p1 implements Resolver {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean solve() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}
	

	protected class Hand implements Comparable<Hand> {
		
		protected char[] value;
		
		public Hand(String value) {
			this.value = value.toCharArray();
		}
		
		public Map<Character, Integer> getDetails() {
			Map<Character, Integer> details = new HashMap<>();
			
			for (char c : value) {
				details.compute(c, (k, v) -> v == null ? 1 : v + 1);
			}
			
			return details;
		}
		
		public boolean isFiveOfKind() {
			return this.getDetails().size() == 1;
		}
		
		public boolean isFourOfAKind() {
			return this.getDetails().values().stream().anyMatch(n -> n == 4);
		}
		
		public boolean isFullHouse() {
			return this.getDetails().values().stream().allMatch(n -> n == 3 || n == 2);
		}
		
		public boolean isThreeOfAKind() {
			Collection<Integer> detailsValues = this.getDetails().values();
			return detailsValues.stream().anyMatch(n -> n == 3) && 
					detailsValues.stream().filter(n -> n != 3).allMatch(n -> n == 1);
		}

		public boolean hasTwoPair() {
			Collection<Integer> detailsValues = this.getDetails().values();
			return detailsValues.stream().anyMatch(n -> n == 1) &&
					detailsValues.stream().filter(n -> n != 1).allMatch(n -> n == 2);
		}
		
		public boolean hasOnePair() {
			Collection<Integer> detailsValues = this.getDetails().values();
			return detailsValues.stream().anyMatch(n -> n == 2) &&
					detailsValues.stream().filter(n -> n != 2).allMatch(n -> n == 1);
		}
		
		public boolean isHighCard() {
			return this.getDetails().values().stream().allMatch(n -> n == 1);
		}
		
		public int getWeight() {
			if(this.isFiveOfKind()) {
				return 6;
			} else if(this.isFourOfAKind()) {
				return 5;
			} else if(this.isFullHouse()) {
				return 4;
			} else if(this.isThreeOfAKind()) {
				return 3;
			} else if(this.hasTwoPair()) {
				return 2;
			} else if(this.hasOnePair()) {
				return 1;
			} else if(this.isHighCard()) {
				return 0;
			}
			
			throw new IllegalStateException();
		}
		
		public static int getCardWeight(char c) {
			// Is letter
			if(c >= 65) {
				if(c == 'T')
					return 58;
				if(c == 'J')
					return 59;
				if(c == 'Q')
					return 60;
				if(c == 'K')
					return 61;
				if(c == 'A')
					return 62;
			}
			
			// Is number
			return c;
		}
		
		@Override
		public int compareTo(Hand o) {
			int comparaison = Integer.compare(this.getWeight(), o.getWeight());
			
			if(comparaison != 0) {
				return comparaison;
			}
			
			for (int i = 0; i < 4; i++) {
				int cardComparaison = Integer.compare(getCardWeight(value[i]), getCardWeight(o.value[i]));
				
				if(cardComparaison != 0) {
					return cardComparaison;
				}
			}
			
			return 0;
		}
	}
}
