package com.matthieu.aoc.resolver.year_2021;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver13p2 extends Resolver13p1 {

	@Override
	public boolean solve() throws SolveException {
		this.drawDots();

		for (Duo<Character, Integer> instruction : foldInstructions) {
			if(instruction.a() == 'x') {
				this.foldX(instruction.b());
			} else {
				this.foldY(instruction.b());
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		Matrix<Character> folded = new Matrix<>(39, 6, () -> ' ');
		
		this.paper.forEach((int x, int y, Character c) -> {
			if(x < folded.getXSize() && y < folded.getYSize() && c == '#') {
				folded.set(x, y, c);
			}
		});
		
		return folded.toString();
	}

	protected void foldY(int i) {
		
		this.paper.forEach((int x, int y, Character c) -> {
			if(y > i && c == '#') {
				this.paper.set(x, y, '.');
				this.paper.set(x, i - (y - i), c);
			}
		});
	}
}
