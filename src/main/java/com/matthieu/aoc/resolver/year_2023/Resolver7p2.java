package com.matthieu.aoc.resolver.year_2023;

import java.util.Arrays;

public class Resolver7p2 extends Resolver7p1 {
	
	private static final char[] CARDS = new char[] {'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'};

	private Hand maximise(Hand hand) {
		if(new String(hand.getValue()).indexOf("J") == -1) {
			return hand;
		}
		
		int Jcount = (int) new String(hand.getValue()).chars().filter(c -> c == 'J').count();
		int[] switchIndexes = new int[Jcount];
		Hand best = hand;
		
		while(!Arrays.stream(switchIndexes).allMatch(i -> i >= 12)) {
			String newValue = new String(hand.getValue());
			
			// Replace all 'J'
			for (int j = 0; j < Jcount; j++) {
				newValue.replaceFirst("[J]", CARDS[switchIndexes[j]] + "");
			}
			
			Hand tmp = new Hand(newValue, best.bid);
			
			if(tmp.compareTo(best) > 0) {
				best = tmp;
			}
			
			// Increment switches indexes
			for (int j = 0; j < switchIndexes.length; j++) {
				switchIndexes[j]++;
				
				if(switchIndexes[j] >= 12) {
					switchIndexes[j] = 0;
					switchIndexes[j + 1]++;
				}
			}
		}
		
		return best;
	}
	
	public static int getCardWeight(char c) {
		// Is letter
		if(c >= 65) {
			if(c == 'T')
				return 58;
			if(c == 'J')
				return 49; // Different
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
	
}
