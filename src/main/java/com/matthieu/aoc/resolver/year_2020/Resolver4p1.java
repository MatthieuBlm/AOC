package com.matthieu.aoc.resolver.year_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.StringTuple;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver4p1 implements Resolver {

	protected List<String> requiredFileds;
	protected List<List<StringTuple>> allPassports;
	protected List<List<StringTuple>> validPassport;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<StringTuple> entryParser = s -> new StringTuple(s.split(":"));
		this.requiredFileds = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
		
		this.validPassport = new ArrayList<>();
		this.allPassports = new ArrayList<>();
		
		List<StringTuple> passport = new ArrayList<>();
		this.allPassports.add(passport);
		for (String line : values) {

			if(Strings.isBlank(line)) {
				passport = new ArrayList<>();
				allPassports.add(passport);
			} else {
				passport.addAll(
						Arrays.asList(line.split(" ")).stream()
							.map(entryParser::parse)
							.collect(Collectors.toList()));
			}
		}
	}

	@Override
	public boolean solve() throws SolveException {
		for (List<StringTuple> passport : allPassports) {
			
			List<String> fieldsPresent = passport.stream().map(StringTuple::a).collect(Collectors.toList());
			
			if(requiredFileds.stream().allMatch(fieldsPresent::contains)) {
				validPassport.add(passport);
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.validPassport.size());
	}

}
