package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver17p1 implements Resolver {

	protected Matrix<Node> verticalMap;
	protected Matrix<Node> horizontalMap;
	protected int minStepLenght;
	protected int maxStepLength;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.verticalMap = new Matrix<>(values, Node::new);
		this.horizontalMap = new Matrix<>(values, Node::new);
		this.minStepLenght = 1;
		this.maxStepLength = 3;
	}

	@Override
	public boolean solve() throws Exception {
		buildGraph();
		return true;
	}

	@Override
	public String get() {
		DijkstraSolver.calculateShortestPathFromSource(this.verticalMap.get(0, 0));
		DijkstraSolver.calculateShortestPathFromSource(this.horizontalMap.get(0, 0));
		
		return Math.min(
				verticalMap.get(verticalMap.getMaxX(), verticalMap.getMaxY()).getDistance(), 
				horizontalMap.get(horizontalMap.getMaxX(), horizontalMap.getMaxY()).getDistance()
			) + "";
	}
	
	protected void buildGraph() {
		this.verticalMap.forEach((x, y, node) -> {
			Node currentNode = verticalMap.get(x, y);
			
			for(Direction d : new Direction[] {Direction.EAST, Direction.WEST}) {
				int cost = d == Direction.EAST ? 
						IntStream.range(1, Math.min(minStepLenght, verticalMap.getMaxX() - x)).map(i -> Integer.parseInt(verticalMap.get(x + i, y).getName())).sum() :
						IntStream.range(1, Math.min(minStepLenght, x)).map(i -> Integer.parseInt(verticalMap.get(x - i, y).getName())).sum();
				
				for (int i = minStepLenght; i <= maxStepLength ; i++) {
					Node toLink = null;
					int nodeToLinkX = x;
					
					if(d == Direction.EAST && x + i <= verticalMap.getMaxX()) {
						nodeToLinkX = x + i;
					} else if(d == Direction.WEST && x - i >= 0) {
						nodeToLinkX = x - i;
					}
					
					if(nodeToLinkX != x) {
						toLink = horizontalMap.get(nodeToLinkX, y);
						cost += Integer.parseInt(toLink.getName());
						currentNode.addDestination(toLink, cost);
					}
				}
			}
		});

		this.horizontalMap.forEach((x, y, node) -> {
			Node currentNode = horizontalMap.get(x, y);
			
			for(Direction d : new Direction[] {Direction.SOUTH, Direction.NORTH}) {
				int cost = d == Direction.SOUTH ? 
						IntStream.range(1, Math.min(minStepLenght, verticalMap.getMaxY() - y)).map(i -> Integer.parseInt(verticalMap.get(x, y + i).getName())).sum() :
						IntStream.range(1, Math.min(minStepLenght, y)).map(i -> Integer.parseInt(verticalMap.get(x, y - i).getName())).sum();
							

				for (int i = minStepLenght; i <= maxStepLength ; i++) {
					Node toLink = null;
					int nodeToLinkY = y;
					
					if(d == Direction.SOUTH && y + i <= horizontalMap.getMaxY()) {
						nodeToLinkY = y + i;
					} else if(d == Direction.NORTH && y - i >= 0) {
						nodeToLinkY = y - i;
					}
					
					if(nodeToLinkY != y) {
						toLink = verticalMap.get(x, nodeToLinkY);
						cost += Integer.parseInt(toLink.getName());
						currentNode.addDestination(toLink, cost);
					}
				}
			}
		});
	}

}
