package com.matthieu.aoc.resolver.year_2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.I;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver24p1 implements Resolver {

	protected Map<Character, Integer> memory;
	protected List<String> program;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.memory = new HashMap<>();
		this.program = values;
	}

	@Override
	public boolean solve() throws SolveException {
		char[] number = "12345678912345".toCharArray();
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected void inp(char var, int value) {
		this.memory.put(var, value);
	}
	
	protected void add(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA + valB;
		
		this.memory.put(a, res);
		
	}
	
	protected void mod(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA % valB;
		
		this.memory.put(a, res);
	}
	
	protected void mul(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);

		int res = valA * valB;
		
		this.memory.put(a, res);
	}
	
	protected void div(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA / valB;

		this.memory.put(a, res);
	}
	
	protected void eql(char a, String b) {
		int valA = this.memory.get(a);
		int valB = this.getValue(b);
		
		int res = valA == valB ? 1 : 0;

		this.memory.put(a, res);
	}
	
	protected int getValue(String param) {
		return this.isDigit(param) ? Integer.valueOf(param) : this.memory.get(param.charAt(0));
	}
	
	protected boolean isDigit(String s) {
		for(char c : s.toCharArray()) {
			if(c < '0' || c > '9')
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		int[] var = new int[] {0, 0, 0, 0};
		
		
		var[0] = 2;
		
		var = doCycle(var[0], var[1], var[2], var[3]);
		
		var[0] = 6;
		
		var = doCycle(var[0], var[1], var[2], var[3]);
		
		System.out.println(Arrays.toString(var));
		
		int x = 19;
		int w = 19;
		
		x = x == w ? 1 : 0;
		x = x == 0 ? 1 : 0;
		
		System.out.println(x);
		
		x = 19;
		w = 19;
		
		x = x != w ? 1 : 0;

		System.out.println(x);
	}
	
	public static int[] doCycle(int w, int x, int y, int z) {
		x = z;
		x %= 26;
		z /= 1;		// 1 or 26
		x += 10;	// variable
		
		x = x != w ? 1 : 0;
		
		y = 25;
		y *= x;
		y++;
		
		z *= y;
		
		y = w;
		y += 2;	// variable
		y *= x;
		z += y;
		
		return new int[] {w, x, y, z};
	}
	
	public static int doCycleVerbose(int input, int z) {
		
		int remainder = z % 26;
		int q = z / 26; // Or 1
		
		remainder += -7; // Or whatever
		
		int x = remainder == input ? 1 : 0;
		x = x == 0 ? 1 : 0;
		
		int y = 25;
		y *= x;
		y++;
		
		int res = q * y;
		
		y = input;
		y += 3; // or Whatever
		
		y *= x;
		
		res += y;
			
		return res;
	}
}
