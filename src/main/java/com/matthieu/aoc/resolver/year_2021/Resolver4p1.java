package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.matrix.Row;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver4p1 implements Resolver {

	protected List<Integer> toMark;
	protected List<Matrix<Duo<Integer, Boolean>>> boards;
	protected Parser<Duo<Integer, Boolean>> matrixParser;
	protected int lastNumber;
	protected int winningBoardSum;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.toMark = Arrays.asList(values.remove(0).split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
		this.matrixParser = s -> new Duo<Integer, Boolean>(Integer.parseInt(s), false);
		this.boards = new ArrayList<>();
		
		// Remove double whitespace
		values = values.stream().map(s -> s.trim().replaceAll("\\s+", " ")).collect(Collectors.toList());
		
		
		List<String> dataForNewMatrix = null;
		
		for (String line : values) {
			
			if(ObjectUtils.isEmpty(line)) {
				if(dataForNewMatrix != null) {
					this.boards.add(new Matrix<>(dataForNewMatrix, this.matrixParser));
				}
				
				dataForNewMatrix = new ArrayList<>();
			} else {
				dataForNewMatrix.add(line);
			}
		}
	}


	@Override
	public boolean solve() throws SolveException {
		for (Integer n : this.toMark) {
			boards.stream().forEach(board -> this.markNumber(board, n));
			
			for (Matrix<Duo<Integer, Boolean>> board : boards) {
				if(this.isWinningBoard(board)) {
					this.lastNumber = n;
					this.winningBoardSum = this.sumUnmarks(board);
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public String get() {
		return String.valueOf(this.lastNumber * this.winningBoardSum);
	}
	
	
	protected void markNumber(Matrix<Duo<Integer, Boolean>> matrix, int n) {
		matrix.stream()
				.filter(cell -> cell.a() == n)
				.forEach(cell -> cell.b(true));
	}
	
	protected int sumUnmarks(Matrix<Duo<Integer, Boolean>> board) {
		return board.stream()
					.filter(Predicate.not(Duo::b))
					.mapToInt(Duo::a).sum();
	}
	
	protected boolean isWinningBoard(Matrix<Duo<Integer, Boolean>> matrix) {
		return matrix.getRows().stream().anyMatch(this::isWinningRow) || 
				matrix.getColumns().stream().anyMatch(this::isWinningRow);
	}
	
	protected boolean isWinningRow(Row<Duo<Integer, Boolean>> row) {
		return row.stream().allMatch(Duo::b);
	}
}
