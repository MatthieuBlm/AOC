package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.model.year_2021.AmphipodsMap;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver23p2 implements Resolver {

	protected Map<AmphipodsMap, Node> nodes;
	protected AmphipodsMap initialState;
	protected AmphipodsMap finalState;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.nodes = new HashMap<>();
		this.initialState = new AmphipodsMap(new char[] {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, Arrays.asList(List.of('C', 'D', 'D', 'D'), List.of('C', 'C', 'B', 'D'), List.of('A', 'B', 'A', 'B'), List.of('B', 'A', 'C', 'A')));
		this.finalState	  = new AmphipodsMap(new char[] {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, Arrays.asList(List.of('A', 'A', 'A', 'A'), List.of('B', 'B', 'B', 'B'), List.of('C', 'C', 'C', 'C'), List.of('D', 'D', 'D', 'D')));
	}

	@Override
	public boolean solve() throws SolveException {
		List<AmphipodsMap> toInitialize = new ArrayList<>();
		
		Node initialNode = new Node();
		
		this.nodes.put(initialState, initialNode);
		
		toInitialize.add(initialState);
		
		
		while(!toInitialize.isEmpty()) {
			AmphipodsMap mapToProcess = toInitialize.remove(0);
			Node currentNode = this.nodes.get(mapToProcess);
			
			List<Duo<AmphipodsMap, Integer>> nextPossibleMaps = this.calcNextPossibleState(mapToProcess);
			
			for (Duo<AmphipodsMap, Integer> mapAndDistance : nextPossibleMaps) {
				AmphipodsMap nextMap = mapAndDistance.a();
				
				// If unknown next map, create node mark map to be initialized
				Node node = this.nodes.computeIfAbsent(nextMap, k -> {
					toInitialize.add(nextMap);
					return new Node();
				});

				// Setup nodes connection
				currentNode.addDestination(node, mapAndDistance.b());
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		DijkstraSolver.calculateShortestPathFromSource(nodes.get(initialState));
		
		return String.valueOf(nodes.get(finalState).getDistance());
	}

	/*			#############
	 * index : 	#01 3 5 7 9A#
	 * 			### # # # ###
	 *            #2#4#6#8#
	 */
	private List<Duo<AmphipodsMap, Integer>> calcNextPossibleState(AmphipodsMap map) {
		return IntStream.range(0, 11)
							.mapToObj(i -> new Duo<>(i, this.getPossibleIndexesFrom(map, i)))
							.filter(duo -> !duo.b().isEmpty())
							.flatMap(duo -> duo.b().stream().map(indexToMove -> new Duo<>(this.moveAmphipod(map, duo.a(), indexToMove.a()), indexToMove.b())))
							.collect(Collectors.toList());
	}
	
	private AmphipodsMap moveAmphipod(AmphipodsMap map, int from, int to) {
		AmphipodsMap newMap = this.clone(map);
		char toMove;
		
		if(from < 2 || from == 3 || from == 5 || from == 7 || from >= 9) {
			toMove = newMap.getOpenspace()[from];
			newMap.getOpenspace()[from] = '.';
		} else if(from == 2) {
			toMove = newMap.getRooms().get(0).remove(0);
		} else if(from == 4) {
			toMove = newMap.getRooms().get(1).remove(0);
		} else if(from == 6) {
			toMove = newMap.getRooms().get(2).remove(0);
		} else if(from == 8) {
			toMove = newMap.getRooms().get(3).remove(0);
		} else {
			throw new IllegalArgumentException("Unvailable index from "+ from);
		}
		
		
		if(to < 2 || to == 3 || to == 5 || to == 7 || to >= 9) {
			newMap.getOpenspace()[to] = toMove;
		} else if(to == 2) {
			newMap.getRooms().get(0).add(0, toMove);
		} else if(to == 4) {
			newMap.getRooms().get(1).add(0, toMove);
		} else if(to == 6) {
			newMap.getRooms().get(2).add(0, toMove);
		} else if(to == 8) {
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
		if(i == 0 || i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 10) {
			return map.getOpenspace()[i] == '.';
		} else if(i == 2){
			return map.getRooms().get(0).size() < 4;
		} else if(i == 4){
			return map.getRooms().get(1).size() < 4;
		} else if(i == 6){
			return map.getRooms().get(2).size() < 4;
		} else if(i == 8){
			return map.getRooms().get(3).size() < 4;
		}
		
		return false;
	}
	
	// Duo<Index, Distance>
	private List<Duo<Integer, Integer>> getPossibleIndexesFrom(AmphipodsMap map, int i) {
		// If i is not an amphipod
		if((isInOpenspace(i) && map.getOpenspace()[i] == '.') || (isInRoom(i) && getRoom(map, i).isEmpty()))
			return Collections.emptyList();
		
		
		List<Integer> possibleIndexes = new ArrayList<>();
		int indexToTest = i;
		boolean openspaceBlocked = false;
		
		// Forward
		while(++indexToTest < 11 && !openspaceBlocked) {
			
			if(this.isFreeSpace(map, indexToTest))
				possibleIndexes.add(indexToTest);
			
			else if(indexToTest == 0 || indexToTest == 1 || indexToTest == 3 || indexToTest == 5 || indexToTest == 7 || indexToTest == 9 || indexToTest == 10)
				openspaceBlocked = true;
		}

		indexToTest = i;
		openspaceBlocked = false;
		
		// Backward
		while(--indexToTest >= 0 && !openspaceBlocked) {
			
			if(this.isFreeSpace(map, indexToTest))
				possibleIndexes.add(indexToTest);
			
			else if(indexToTest == 0 || indexToTest == 1 || indexToTest == 3 || indexToTest == 5 || indexToTest == 7 || indexToTest == 9 || indexToTest == 10)
				openspaceBlocked = true;
		}

		// Calculate distances
		return possibleIndexes.stream().map(to -> {
			int distance = Math.abs(to - i);
			
			if(isInRoom(i))
				distance += 4 - getRoom(map, i).size() + 1;
			if(isInRoom(to))
				distance += 4 - getRoom(map, to).size();
			
			distance *= this.getAmphipodMoveCost(this.getAmphipod(map, i));
			
			return new Duo<>(to, distance);
		}).collect(Collectors.toList());
	}
	
	private boolean isInOpenspace(int index) {
		return index == 0 || index == 1 || index == 3 || index == 5 || index == 7 || index == 9 || index == 10;
	}
	
	private boolean isInRoom(int index) {
		return !this.isInOpenspace(index);
	}
	
	private List<Character> getRoom(AmphipodsMap map, int index) {
		return map.getRooms().get(index / 2 - 1);
	}
	
	private char getAmphipod(AmphipodsMap map, int index) {
		if(isInOpenspace(index))
			return map.getOpenspace()[index];

		return this.getRoom(map, index).get(0);
	}
	
	private int getAmphipodMoveCost(char a) {
		if(a == 'A')
			return 1;
		if(a == 'B')
			return 10;
		if(a == 'C')
			return 100;
		if(a == 'D')
			return 1000;
		
		throw new IllegalArgumentException("Unknown amphipod '"+ a +"'");
	}
}
