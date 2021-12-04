package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
					this.boards.add(this.buildMatrix(dataForNewMatrix, this.matrixParser));
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
			for (Matrix<Duo<Integer, Boolean>> board : boards) {
				this.markNumber(board, n);
			}
			
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
		for (Row<Duo<Integer, Boolean>> row : matrix.getRows()) {
			for (Duo<Integer, Boolean> cell : row.get()) {
				if(cell.a().equals(n))
					cell.b(true);
			}
		}
	}
	
	protected int sumUnmarks(Matrix<Duo<Integer, Boolean>> board) {
		int sum = 0;

		for (Row<Duo<Integer, Boolean>> row : board.getRows()) {
			for (Duo<Integer, Boolean> cell : row.get()) {
				if(Boolean.FALSE.equals(cell.b()))
					sum += cell.a();
			}
		}
		
		return sum;
		
	}
	
	protected boolean isWinningBoard(Matrix<Duo<Integer, Boolean>> matrix) {
		for (Row<Duo<Integer, Boolean>> row : matrix.getRows()) {
			if(this.isWinningRow(row))
				return true;
		}
		
		for (Row<Duo<Integer, Boolean>> column : matrix.getColumns()) {
			if(this.isWinningColumn(column))
				return true;
		}
		
		return false;
	}
	
	protected boolean isWinningRow(Row<Duo<Integer, Boolean>> row) {
		for (Duo<Integer, Boolean> cell : row.get()) {
			if(Boolean.FALSE.equals(cell.b()))
				return false;
		}
		
		return true;
	}
	
	protected boolean isWinningColumn(Row<Duo<Integer, Boolean>> column) {
		for (Duo<Integer, Boolean> cell : column.get()) {
			if(Boolean.FALSE.equals(cell.b()))
				return false;
		}
		
		return true;
	}
	
	protected Matrix<Duo<Integer, Boolean>> buildMatrix(List<String> data, Parser<Duo<Integer, Boolean>> parser) {
		return new Matrix<>(data, parser);
	}
}
