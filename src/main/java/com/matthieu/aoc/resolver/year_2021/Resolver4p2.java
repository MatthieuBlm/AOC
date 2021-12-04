package com.matthieu.aoc.resolver.year_2021;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver4p2 extends Resolver4p1 {

	@Override
	public boolean solve() throws SolveException {
		
		for (Integer n : this.toMark) {
			// Mark current number
			for (Matrix<Duo<Integer, Boolean>> board : boards) {
				this.markNumber(board, n);
			}
			
			// Remove winning boards (until last)
			for (int i = 0; i < boards.size(); i++) {
				if(this.isWinningBoard(boards.get(i)) && this.boards.size() > 1) {
					boards.remove(i);
				}
			}

			// If last, calculate score
			if(boards.size() == 1 && this.isWinningBoard(boards.get(0))) {
				this.lastNumber = n;
				this.winningBoardSum = this.sumUnmarks(this.boards.get(0));
				return true;
			}
		}
		
		return false;
	}

}
