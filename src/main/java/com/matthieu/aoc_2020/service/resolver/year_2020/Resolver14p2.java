package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc_2020.exception.SolveException;

public class Resolver14p2 extends Resolver14p1 {

	@Override
	public boolean solve() throws SolveException {
		for (String value : values) {
			String[] line = value.split(" = ");
			
			if(line[0].equals("mask")) {
				this.mask = line[1];
			} else {
				long address = Long.parseLong(line[0].replace("mem[", "").replace("]", ""));
				long val = Long.parseLong(line[1]);
				
				String bitAddress = this.get36BitString(Long.toBinaryString(address));
				bitAddress = this.applyMask(bitAddress);
				
				List<Long> adresses = this.getAddresses(bitAddress);
				
				for (Long addr : adresses) {
					this.memory.put(addr, val);
				}
			}
		}
		
		return true;
	}

	@Override
	protected String applyMask(String s) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= s.length() ; i++) {
			char maskChar = mask.charAt(mask.length() - i);
			
			if(maskChar == 'X' || maskChar == '1') {
				sb.insert(0, maskChar);
			} else {
				sb.insert(0, s.charAt(s.length() - i));
			}
		}
		
		return sb.toString();
	}

	private List<Long> getAddresses(String bitAddress) {
		List<Long> result = new ArrayList<>();
		
		int getNumberOfFloatingBit = (int) bitAddress.chars().filter(c -> c == 'X').count();
		int possibilities = (int) Math.pow(2, getNumberOfFloatingBit);
		
		for (int i = 0; i < possibilities; i++) {
			String currentBitReplacement = this.getNBitString(Integer.toBinaryString(i), getNumberOfFloatingBit);
			String parsedBitAddress = this.replaceAllXOccurence(bitAddress, currentBitReplacement);
			
			result.add(this.longFromBinaryString(parsedBitAddress));
		}
		
		return result;
	}
	
	private String replaceAllXOccurence(String s, String bitReplacement) {
		for (int j = 0; j < bitReplacement.length(); j++) {
			s = s.replaceFirst("X", String.valueOf(bitReplacement.charAt(j)));
		}
		
		return s;
	}
}
