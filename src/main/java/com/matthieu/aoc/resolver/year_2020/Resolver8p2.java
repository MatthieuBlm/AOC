package com.matthieu.aoc.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2020.Instruction;

public class Resolver8p2 extends Resolver8p1 {

	protected List<Instruction> originals;
	
	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < instructions.size(); i++) {
			
			while(!instructions.get(i).getName().matches("jmp|nop")) {
				i++;
			}
			
			Instruction swaped = this.swap(instructions.get(i));
			instructions.set(i, swaped);

			if(super.solve()) {
				return true;
			}

			// Restore
			swaped = this.swap(instructions.get(i));
			instructions.set(i, swaped);
		}
		
		return false;
	}

	private Instruction swap(Instruction i) {
		if(i.getName().equals("jmp")) {
			i.setName("nop");
		} else if(i.getName().equals("nop")) {
			i.setName("jmp");
		}
		
		return i;
	}
	
}
