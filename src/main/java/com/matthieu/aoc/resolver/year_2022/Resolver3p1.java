package com.matthieu.aoc.resolver.year_2022;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver3p1 implements Resolver {
	
	protected List<String> ruckstacks;
	protected long result;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		ruckstacks = values;
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (String ruckstack : ruckstacks) {
			String part1 = ruckstack.substring(0, ruckstack.length() / 2);
			String part2 = ruckstack.substring(ruckstack.length() / 2, ruckstack.length());
			
			char commonItem = '0';
			
			for (char c : part1.toCharArray()) {
				if(part2.contains(c + "")) {
					commonItem = c;
					break;
				}
			}
			
			if(commonItem != '0') {
				result += getPriority(commonItem);
			} else {
				System.err.println("No common item found");
			}
			
		}
		
		return true;
	}

	@Override
	public String get() {
		return result + "";
	}

	public static void main(String[] args) {
		System.out.println((long) 'a');
		System.out.println((long) 'A');
	}
	protected int getPriority(char c) {
		if(c >= 'a' && c <= 'z') {
			return c - 'a' + 1;
		} else {
			return c - 'A' + 27;
		}
	}
}
