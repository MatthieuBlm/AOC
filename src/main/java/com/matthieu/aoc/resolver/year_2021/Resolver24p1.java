package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2021.Base9Digit;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver24p1 implements Resolver {

	protected static final int[] xIncrements = 	new int[] {10, 15, 14, 15, -8, 10, 16, -4, 11, -3, 12, -7, -15, -7};
	protected static final int[] yIncrements = 	new int[] {2,  16,  9, 0,   1, 12,  6,  6,  3,  5,  9,  3,   2,  3};
	protected static final int[] zDivider = 	new int[] {1,  1,   1, 1,  26,  1, 26, 26,  1, 26,  1, 26,  26, 26};
	
	protected List<String> program;
	protected boolean findMax;
	protected Base9Digit digits;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.program = values;
		this.findMax = true;
		this.digits = new Base9Digit("99999999999999");
	}

	@Override
	public boolean solve() throws SolveException {

		for(int v = 0; v < 100_000_000; v++) {
//		while(true) {
			int z = 0;
			
			for (int i = 0; i < 14; i++) {
				if(i >= 11 && z > 17576)
					break;
				
				z =  doCycleVerbose(digits.get(i), xIncrements[i], yIncrements[i], zDivider[i], z);
			}

			if(z == 0) {
				break;
			}
			
			if(this.findMax)
				digits.decr();
			else
				digits.incr();
		}
		
		return true;
	}

	@Override
	public String get() {
		return this.digits.toString();
	}
	
	public static void main(String[] args) {
//		Base9Digit digits = new Base9Digit("99399795999899");
//		Base9Digit digits = new Base9Digit("99991795999899");
//		Base9Digit digits = new Base9Digit("99999985999899");
		Base9Digit digits = new Base9Digit("99999995999999");
		
		int z = 0;
		for (int i = 0; i < 14; i++) {
			z =  doCycleVerbose(digits.get(i), xIncrements[i], yIncrements[i], zDivider[i], z);
		}
		
		System.out.println(z);
	}
	
	public static int[] doCycle(int w, int xIncr, int yIncr, int zDiv, int z) {
		int x = z;
		x %= 26;
		z /= zDiv;	// 1 or 26
		x += xIncr;	// variable
		
		x = x != w ? 1 : 0;
		
		int y = 25;
		y *= x;
		y++;
		
		z *= y;
		
		y = w;
		y += yIncr;	// variable
		y *= x;
		z += y;
		
		return new int[] {w, x, y, z};
	}
	
	public static int doCycleVerbose(int input, int z) {
		
		int remainder = (z % 26) + 10; // "+10" or whatever
		int q = z / 1; 		// Or 26
		
		if(remainder != input)
			return q * 26 + input + 2; 	// "+2" or Whatever
		else
			return q;
	}
	
	public static int doCycleVerbose(int input, int xIncrement, int yIncrement, int zDivider, int z) {
		if((z % 26) + xIncrement == input)
			return z / zDivider;
		else
			return z / zDivider * 26 + input + yIncrement;
	}
}
