package com.matthieu.aoc.resolver.year_2023;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver7p1 implements Resolver {

	protected List<Hand> hands;
	protected long total;
	protected static int part;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Resolver7p1.part = 1; // To customise getCardWeight behaviour
		this.hands = values.stream()
				.map(line -> line.split(" "))
				.map(line -> new Hand(line[0], Integer.parseInt(line[1])))
				.toList();
	}

	@Override
	public boolean solve() throws Exception {
		this.hands = hands.stream().sorted().toList();
		
		this.total = IntStream.range(1, hands.size() + 1)
				.mapToLong(i -> hands.get(i - 1).getBid() * i)
				.sum();
		
		return true;
	}

	@Override
	public String get() {
		return this.total + "";
	}
	
	protected static int getCardWeight(char c) {
		// Is letter
		if(c >= 65) {
			if(c == 'T')
				return 58;
			if(c == 'J')
				return part == 1 ? 59 : 47;
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
	
	protected class Hand implements Comparable<Hand> {
		
		protected char[] value;
		protected int bid;
		protected char[] max;
		
		public Hand(String value, int bid) {
			this.value = value.toCharArray();
			this.bid = bid;
			this.max = null;
		}

		public Map<Character, Integer> getDetails() {
			Map<Character, Integer> details = new HashMap<>();
			
			char[] valueToUse = this.max != null ? this.max :  this.value;
			
			for (char c : valueToUse) {
				details.compute(c, (k, v) -> v == null ? 1 : v + 1);
			}
			
			return details;
		}
		
		public boolean isFiveOfAKind() {
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
			return detailsValues.stream().filter(n -> n == 1).count() == 1 &&
					detailsValues.stream().filter(n -> n == 2).count() == 2;
		}
		
		public boolean hasOnePair() {
			Collection<Integer> detailsValues = this.getDetails().values();
			return detailsValues.stream().filter(n -> n == 2).count() == 1 &&
					detailsValues.stream().filter(n -> n == 1).count() == 3;
		}
		
		public boolean isHighCard() {
			return this.getDetails().values().stream().allMatch(n -> n == 1);
		}
		
		public int getWeight() {
			if(this.isFiveOfAKind()) {
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
		
		@Override
		public int compareTo(Hand o) {
			int comparaison = Integer.compare(this.getWeight(), o.getWeight());
			
			if(comparaison != 0) {
				return comparaison;
			}
			
			for (int i = 0; i < 5; i++) {
				int cardComparaison = Integer.compare(getCardWeight(value[i]), getCardWeight(o.value[i]));
				
				if(cardComparaison != 0) {
					return cardComparaison;
				}
			}
			
			return 0;
		}

		public char[] getValue() {
			return value;
		}

		public void setValue(char[] value) {
			this.value = value;
		}

		public int getBid() {
			return bid;
		}

		public void setBid(int bid) {
			this.bid = bid;
		}
		
		public Hand setMax(char[] value) {
			this.max = value;
			return this;
		}

		@Override
		public String toString() {
			return "[value=" + new String(value) + ", max=" + new String(max) + ", bid=" + bid +"]";
		}
		
		public String detailedString() {
			return this.toString() + "\t" +
					(isFiveOfAKind() ? "1" : "_") +
					(isFourOfAKind() ? "1" : "_") +
					(isFullHouse() ? "1" : "_") +
					(isThreeOfAKind() ? "1" : "_") +
					(hasTwoPair() ? "1" : "_") +
					(hasOnePair() ? "1" : "_") +
					(isHighCard() ? "1" : "_");
		}
	}
}
