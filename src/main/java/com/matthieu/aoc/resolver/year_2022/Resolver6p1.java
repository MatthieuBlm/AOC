package com.matthieu.aoc.resolver.year_2022;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver6p1 implements Resolver {

	protected String packet;
	protected int result;
	protected int sequnenceSize;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.packet = values.get(0);
		this.sequnenceSize = 4;
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < packet.length() - 4; i++) {
			if(areAllUnique(getSequence(i, sequnenceSize))) {
				result = i + sequnenceSize;
				break;
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(result);
	}
	
	protected boolean areAllUnique(char[] sequence) {
		for (int i = 0; i < sequence.length; i++) {
			for (int j = 0; j < sequence.length; j++) {
				if(i != j && sequence[i] == sequence[j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	protected char[] getSequence(int index, int size) {
		char[] sequence = new char[size];
		
		for (int j = 0; j < sequence.length; j++) {
			sequence[j] = packet.charAt(index + j);
		}
		
		return sequence;
	}

}
