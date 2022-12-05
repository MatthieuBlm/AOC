package com.matthieu.aoc.resolver.year_2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver5p1 implements Resolver {
	
	List<List<Character>> crates;
	List<List<Integer>> moves;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		List<String> cratesInitialization = new ArrayList<>();
		List<String> movesInitialization= new ArrayList<>();

		boolean whiteLineSeen = false;
		
		for (String line : values) {
			if(org.apache.logging.log4j.util.Strings.isBlank(line)) {
				whiteLineSeen = true;
			} else {
				if(whiteLineSeen) {
					movesInitialization.add(line);
				} else {
					cratesInitialization.add(line);
				}
			}
		}
		
		this.initCrates(cratesInitialization);
		this.initMoves(movesInitialization);
	}

	@Override
	public boolean solve() throws SolveException {

		for (List<Integer> move : moves) {
			this.moveCrates(move.get(0), move.get(1), move.get(2));
		}
		
		return true;
	}

	@Override
	public String get() {
		String result = "";
		
		for (List<Character> stack : crates) {
			result += stack.get(stack.size() - 1);
		}
		
		return result;
	}

	protected void moveCrate(int idSource, int idDest) {
		List<Character> relatedStack = this.crates.get(idSource - 1);
		
		Character toMove = relatedStack.remove(relatedStack.size() - 1);
		
		this.crates.get(idDest - 1).add(toMove);
	}
	
	protected void moveCrates(int nbToMove, int idSource, int idDest) {
		for (int i = 0; i < nbToMove; i++) {
			this.moveCrate(idSource, idDest);
		}
	}
	
	protected void initCrates(List<String> initial) {
		int stackNumber = initial.get(initial.size() - 1).replaceAll(" ", "").length();
		this.crates = new ArrayList<>();
		
		System.out.println("Found "+ stackNumber +" crates stacks");
		
		for (int i = 0; i < stackNumber; i++) {
			crates.add(new ArrayList<>());
		}
		
		for (int i = initial.size() - 2; i >= 0; i--) {
			for (int j = 0; j < crates.size(); j++) {
				char crate = initial.get(i).charAt(j + 1 + j * 3);
				
				System.out.print(crate);
			
				if(crate != ' ') {
					crates.get(j).add(crate);
				}
			}
			System.out.println();
		}
	}
	
	protected void initMoves(List<String> initial) {
		this.moves = new ArrayList<>();
		
		for (String move : initial) {
			String[] splitA = move.split(" from ");
			String[] splitB = splitA[1].split(" to ");
			
			int nbCrate = Integer.parseInt(splitA[0].replaceAll("move ", ""));
			int idSource = Integer.parseInt(splitB[0]);
			int idDest = Integer.parseInt(splitB[1]);
			
			moves.add(Arrays.asList(nbCrate, idSource, idDest));
		}
	}
}
