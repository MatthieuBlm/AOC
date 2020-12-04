package com.matthieu.aoc_2020.service.resolver;

import java.util.Arrays;
import java.util.List;


import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.service.Validator;


public class Resolver4p2 extends Resolver4p1 {

	@Override
	public boolean solve() throws SolveException {
		List<String> eclPossibility = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
		
		Validator byr = s -> s.length() == 4 && Integer.parseInt(s) >= 1920 && Integer.parseInt(s) <= 2002;
		Validator iyr = s -> s.length() == 4 && Integer.parseInt(s) >= 2010 && Integer.parseInt(s) <= 2020;
		Validator eyr = s -> s.length() == 4 && Integer.parseInt(s) >= 2020 && Integer.parseInt(s) <= 2030;
		Validator hgt = s -> {
			if(s.contains("cm")) {
				int i = Integer.parseInt(s.split("cm")[0]);
				
				return i >= 150 && i <= 193;
			} else if(s.contains("in")) {
				int i = Integer.parseInt(s.split("in")[0]);
				
				return i >= 59 && i <= 76;
			}
			return false;
		};
		Validator hcl = s -> s.matches("#[0-9a-f]{6}");
		Validator ecl = s -> eclPossibility.contains(s);
		Validator pid = s -> s.length() == 9;
		
		for (List<String[]> passport : data) {
			boolean ok = true;
			
			for (String field : this.requiredFileds) {
				ok &= passport.stream().anyMatch(entry -> entry[0].equalsIgnoreCase(field));
			}
			
			if(ok) {
				for (String[] field : passport) {
					
					if(ok) {
						Validator v = null;
						switch(field[0]) {
						case "byr":
							v= byr;
							break;
						case "iyr":
							v= iyr;
							break;
						case "eyr":
							v= eyr;
							break;
						case "hgt":
							v= hgt;
							break;
						case "hcl":
							v= hcl;
							break;
						case "ecl":
							v= ecl;
							break;
						case "pid":
							v= pid;
							break;
						case "cid":
							continue;
						default: 
							ok = false;
							continue;
						}
						
						ok &= v.validate(field[1]);
					}
				}
			}
			
			if(ok) {
				this.validPassport++;
			}
		}
		
		return true;
	}
	
	public static void main(String[]s) {
		System.out.println("#6f89ab".matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$"));
	}
}
