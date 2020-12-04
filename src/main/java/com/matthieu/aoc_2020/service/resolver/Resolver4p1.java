package com.matthieu.aoc_2020.service.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.service.parser.Parser;

public class Resolver4p1 implements Resolver {

	protected List<List<String[]>> data;
	protected List<String> requiredFileds;
	protected int validPassport;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<String[]> entryParser = s -> s.split(":");
		this.requiredFileds = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
		this.validPassport = 0;
		
		this.data = new ArrayList<>();
		
		List<String[]> passport = new ArrayList<>();
		this.data.add(passport);
		for (String line : values) {

			if(Strings.isBlank(line)) {
				passport = new ArrayList<>();
				data.add(passport);
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
		for (List<String[]> passport : data) {
			boolean ok = true;
			
			for (String field : this.requiredFileds) {
				ok &= passport.stream().anyMatch(entry -> entry[0].equalsIgnoreCase(field));
			}
			
			if(ok) {
				this.validPassport++;
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.validPassport);
	}

}
