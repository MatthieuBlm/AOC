package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.Set;

import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver11p2 extends Resolver11p1 {

	private int enlaredFactor = 1_000_000;
	private List<Boolean> emptyRows;
	private List<Boolean> emptyColumns;
	
	@Override
	public boolean solve() throws Exception {
		emptyRows = this.space.getRows().stream().map(row -> row.stream().allMatch(c -> c == '.')).toList();
		emptyColumns = this.space.getColumns().stream().map(col -> col.stream().allMatch(c -> c == '.')).toList();
		
		this.galaxies = space.cellStream().filter(cell -> cell.value() == '#').map(this::enlargeCellCoords).toList();
		
		Set<Duo<Cell<Character>, Cell<Character>>> paires = this.makePaires(this.galaxies);
		
		this.distances = paires.stream().map(pair -> this.shortestDistance(pair.a(), pair.b())).toList();
		
		return false;
	}
	
	protected Cell<Character> enlargeCellCoords(Cell<Character> cell) {
		int enlargedX = 0;
		int enlargedY = 0;
		
		for (int x = 0; x < cell.x(); x++) {
			if(emptyColumns.get(x).booleanValue()) {
				enlargedX += enlaredFactor;
			} else {
				enlargedX++;
			}
		}

		for (int y = 0; y < cell.y(); y++) {
			if(emptyRows.get(y).booleanValue()) {
				enlargedY += enlaredFactor;
			} else {
				enlargedY++;
			}
		}
		
		return new Cell<>(enlargedX, enlargedY, cell.value());
	}


}
