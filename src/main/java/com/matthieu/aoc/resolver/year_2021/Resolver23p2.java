package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.year_2021.AmphipodsMap;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver23p2 implements Resolver {

	protected Map<AmphipodsMap, Node> nodes;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.nodes = new HashMap<>();
		
	}

	@Override
	public boolean solve() throws SolveException {
		List<AmphipodsMap> toInitialize = new ArrayList<>();
		
		toInitialize.add(new AmphipodsMap(new char[] {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, Arrays.asList(List.of('C', 'D', 'D', 'D'), List.of('C', 'C', 'B', 'D'), List.of('A', 'B', 'A', 'B'), List.of('B', 'A', 'C', 'A'))));
		
		while(!toInitialize.isEmpty()) {
			AmphipodsMap mapToProcess = toInitialize.remove(0);
			
			
		}
		
		return true;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

	/*			#############
	 * index : 	#12 4 6 8 AB#
	 * 			### # # # ###
	 *            #3#5#7#9#
	 */
	private List<AmphipodsMap> calcNextPossibleStateFrom(AmphipodsMap map, int fromIndex) {
		List<AmphipodsMap> results = new ArrayList<>();
		
		List<Integer> possibleIndexes = this.getPossibleIndexesFrom(map, fromIndex);
		
		return results;
	}
	
	private AmphipodsMap moveAmphipod(AmphipodsMap map, int from, int to) {
		AmphipodsMap newMap = this.clone(map);
		char toMove;
		
		if(from <= 2 || from == 4 || from == 6 || from == 8 || from >= 10) {
			toMove = newMap.getOpenspace()[from];
			newMap.getOpenspace()[from] = '.';
		} else if(from == 3) {
			toMove = newMap.getRooms().get(0).remove(0);
		} else if(from == 5) {
			toMove = newMap.getRooms().get(1).remove(0);
		} else if(from == 7) {
			toMove = newMap.getRooms().get(2).remove(0);
		} else if(from == 9) {
			toMove = newMap.getRooms().get(3).remove(0);
		} else {
			throw new IllegalArgumentException("Unvailable index from "+ from);
		}
		
		
		if(to <= 2 || to == 4 || to == 6 || to == 8 || to >= 10) {
			newMap.getOpenspace()[to] = toMove;
		} else if(to == 3) {
			newMap.getRooms().get(0).add(0, toMove);
		} else if(to == 5) {
			newMap.getRooms().get(1).add(0, toMove);
		} else if(to == 7) {
			newMap.getRooms().get(2).add(0, toMove);
		} else if(to == 9) {
			newMap.getRooms().get(3).add(0, toMove);
		} else {
			throw new IllegalArgumentException("Unvailable index to "+ to);
		}
		
		return newMap;
	}
	
	private AmphipodsMap clone(AmphipodsMap mapToClone) {
		char[] newOpenspace = Arrays.copyOf(mapToClone.getOpenspace(), mapToClone.getOpenspace().length);
		List<List<Character>> newRooms = new ArrayList<>();
		
		newRooms.add(new ArrayList<>());
		newRooms.add(new ArrayList<>());
		newRooms.add(new ArrayList<>());
		newRooms.add(new ArrayList<>());
		
		newRooms.get(0).addAll(mapToClone.getRooms().get(0));
		newRooms.get(1).addAll(mapToClone.getRooms().get(1));
		newRooms.get(2).addAll(mapToClone.getRooms().get(2));
		newRooms.get(3).addAll(mapToClone.getRooms().get(3));
		
		return new AmphipodsMap(newOpenspace, newRooms);
	}
	
	private boolean isFreeSpace(AmphipodsMap map, int i) {
		if(i == 1 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 11) {
			return map.getOpenspace()[i] == '.';
		} else if(i == 3){
			return map.getRooms().get(0).size() < 4;
		} else if(i == 5){
			return map.getRooms().get(1).size() < 4;
		} else if(i == 7){
			return map.getRooms().get(2).size() < 4;
		} else if(i == 9){
			return map.getRooms().get(3).size() < 4;
		}
		
		return false;
	}
	
	private List<Integer> getPossibleIndexesFrom(AmphipodsMap map, int index) {
		List<Integer> possibleIndexes = new ArrayList<>();
		boolean isFree;
		int indexToTest = index;

		// Forward
		while(isFree = this.isFreeSpace(map, indexToTest++) && indexToTest <= 11) {
			possibleIndexes.add(indexToTest);
		}

		indexToTest = index;
		
		// Backward
		while(isFree = this.isFreeSpace(map, indexToTest--) && indexToTest >= 0) {
			possibleIndexes.add(indexToTest);
		}
		
		return possibleIndexes;
	}
}
