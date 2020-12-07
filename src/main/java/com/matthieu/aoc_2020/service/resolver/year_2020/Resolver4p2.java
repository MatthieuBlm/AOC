package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.stream.Collectors;

import com.matthieu.aoc_2020.exception.SolveException;
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
		validators.put("cm", s -> Integer.parseInt(s) >= 150 && Integer.parseInt(s) <= 193);
		validators.put("in", s -> Integer.parseInt(s) >= 59 && Integer.parseInt(s) <= 76);
		validators.put("hgt", s -> s.matches("[0-9]*cm|[0-9]*in") && validators.validate(s.substring(s.length() - 2), s.substring(0, s.length() - 2)));

		super.solve();
		
		super.validPassport = super.validPassport.stream()
										.filter(pass -> pass.stream().allMatch(validators::validate))
										.collect(Collectors.toList());
		
		return true;
	}
}
