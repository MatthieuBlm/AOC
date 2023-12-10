package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.CustomBaseNumber;

public class Resolver7p2 extends Resolver7p1 {
	
	private static final char[] CARDS = new char[] {'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'};

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		part = 2; // To customise getCardWeight behaviour
		this.hands = values.stream()
				.map(line -> line.split(" "))
				.map(line -> new Hand(line[0], Integer.parseInt(line[1])))
				.map(hand -> hand.setMax(maximise(hand)))
				.toList();
	}
	
	private char[] maximise(Hand hand) {
		if(new String(hand.getValue()).indexOf("J") == -1) {
			return hand.value;
		}
		
		int jCount = (int) new String(hand.getValue()).chars().filter(c -> c == 'J').count();
		CustomBaseNumber jSwitch = new CustomBaseNumber(jCount, CARDS);
		Hand best = hand;
		
		while(!jSwitch.isMaxValue()) {
			String newValue = new String(hand.getValue());
			
			// Replace all 'J'
			for (int j = 0; j < jCount; j++) {
				newValue = newValue.replaceFirst("J", jSwitch.get(j) + "");
			}
			
			Hand tmp = new Hand(newValue, best.bid);
			
			if(tmp.compareTo(best) > 0) {
				best = tmp;
			}
			
			// Increment switches indexes
			jSwitch.increment();
		}
		
		return best.value;
	}
	
}
