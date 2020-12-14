package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.List;
import java.util.TreeMap;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver14p1 implements Resolver {

	protected List<String> values;
	protected TreeMap<Long, Long> memory;
	protected String mask;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.values = values;
		this.memory = new TreeMap<>();
	}

	@Override
	public boolean solve() throws SolveException {

		for (String value : values) {
			String[] line = value.split(" = ");
			
			if(line[0].equals("mask")) {
				this.mask = line[1];
			} else {
				long address = Long.parseLong(line[0].replace("mem[", "").replace("]", ""));
				long val = Long.parseLong(line[1]);
				
				String bitRepresentation = this.get36BitString(Long.toBinaryString(val));
				String maskedValue = this.applyMask(bitRepresentation);
				
				Long result = this.longFromBinaryString(maskedValue);
				
				this.memory.put(address, result);
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.memory.values().stream().mapToLong(i -> i).sum());
	}
	
	protected String get36BitString(String s) {
		return this.getNBitString(s, 36);
	}
	
	protected String getNBitString(String s, int n) {
		StringBuilder sb = new StringBuilder(s);
		
		while(sb.length() <= n - 1) {
			sb.insert(0, '0');
		}
		
		return sb.toString();
	}
	
	protected String applyMask(String s) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= s.length() ; i++) {
			char maskChar = mask.charAt(mask.length() - i);
			
			if(maskChar == 'X') {
				sb.insert(0, s.charAt(s.length() - i));
			} else {
				sb.insert(0, maskChar);
			}
		}
		
		return sb.toString();
	}
	
	protected Long longFromBinaryString(String s) {
		return Long.parseLong(s, 2);
	}
}
