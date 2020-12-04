package com.matthieu.aoc_2020.service.resolver;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.StringTuple;
import com.matthieu.aoc_2020.service.validator.ValidatorSet;


public class Resolver4p2 extends Resolver4p1 {

	@Override
	public boolean solve() throws SolveException {
		ValidatorSet validators = new ValidatorSet();
		
		validators.put("cid", s -> true);
		validators.put("byr", s -> s.length() == 4 && Integer.parseInt(s) >= 1920 && Integer.parseInt(s) <= 2002);
		validators.put("iyr", s -> s.length() == 4 && Integer.parseInt(s) >= 2010 && Integer.parseInt(s) <= 2020);
		validators.put("eyr", s -> s.length() == 4 && Integer.parseInt(s) >= 2020 && Integer.parseInt(s) <= 2030);
		validators.put("hcl", s -> s.matches("#[0-9a-f]{6}"));
		validators.put("ecl", s -> s.matches("amb|blu|brn|gry|grn|hzl|oth"));
		validators.put("pid", s -> s.length() == 9);
		validators.put("hgt", s -> {
			if(s.contains("cm")) {
				int i = Integer.parseInt(s.split("cm")[0]);
				
				return i >= 150 && i <= 193;
			} else if(s.contains("in")) {
				int i = Integer.parseInt(s.split("in")[0]);
				
				return i >= 59 && i <= 76;
			}
			return false;
		});
		

		super.solve();
		
		
		List<List<String[]>> passportWithAllFields = super.validPassport;
		super.validPassport = new ArrayList<>();
		
		for (List<String[]> passport : passportWithAllFields) {
			
			if(passport.stream().map(StringTuple::new).allMatch(validators::validate)) {
				super.validPassport.add(passport);
			}
		}
		
		return true;
	}
}
