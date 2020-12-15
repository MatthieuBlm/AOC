package com.matthieu.aoc.resolver.year_2020;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2020.Instruction;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver8p1 implements Resolver {

	protected List<Instruction> instructions;
	protected int accumulator;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<Instruction> parser = s -> new Instruction(s.split(" ")[0], Integer.parseInt(s.split(" ")[1]));

		this.instructions = values.stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		int pointer = 0;
		this.accumulator = 0;
		
		List<Boolean> stepExecuted = this.instructions.stream().map(i -> Boolean.FALSE).collect(Collectors.toList());
		
		while(pointer < this.instructions.size() && Boolean.FALSE.equals(stepExecuted.get(pointer))) {
			Instruction i = this.instructions.get(pointer);

			stepExecuted.set(pointer, Boolean.TRUE);
			
			if(i.getName().equals("acc")) {
				accumulator += i.getArg();
				pointer++;
			} else if(i.getName().equals("jmp")) {
				pointer += i.getArg();
			} else {
				pointer++;
			}

		}
		
		
		return pointer >= this.instructions.size();
	}

	@Override
	public String get() {
		return String.valueOf(this.accumulator);
	}

}
