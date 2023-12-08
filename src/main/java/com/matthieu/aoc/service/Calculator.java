package com.matthieu.aoc.service;

import java.math.BigInteger;

public class Calculator {
	
	private Calculator() {}

	public static long leastCommonMultiple(long a, long b) {
		if(a == 0 || b == 0) {
			return 0;
		}
		
		long absoluteA = Math.abs(a);
		long absoluteB = Math.abs(b);
		long higher = Math.max(absoluteA, absoluteB);
		long lower = Math.min(absoluteA, absoluteB);
		long lcm = higher;
		
		while(lcm % lower != 0) {
			lcm += higher;
		}
	
		return lcm;
	}
	
	public static BigInteger bigLeastCommonMultiple(BigInteger a, BigInteger b) {
		if(a == BigInteger.ZERO || b == BigInteger.ZERO) {
			return BigInteger.ZERO;
		}
		
		BigInteger absoluteA = a.abs();
		BigInteger absoluteB = b.abs();
		BigInteger higher = absoluteA.max(absoluteB);
		BigInteger lower = absoluteA.min(absoluteB);
		BigInteger lcm = higher;
		
		while(lcm.mod(lower) != BigInteger.ZERO) {
			lcm = lcm.add(higher);
		}
		
		return lcm;
	}
}
