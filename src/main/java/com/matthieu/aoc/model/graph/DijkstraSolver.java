package com.matthieu.aoc.model.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

public class DijkstraSolver {
	
	private DijkstraSolver() {}

	public static void calculateShortestPathFromSource(Node source) {
	    source.setDistance(0);

	    Set<Node> settledNodes = new HashSet<>();
	    Set<Node> unsettledNodes = new HashSet<>();

	    unsettledNodes.add(source);

	    while (!unsettledNodes.isEmpty()) {
	        Node currentNode = getLowestDistanceNode(unsettledNodes);
	        
	        unsettledNodes.remove(currentNode);
	        
	        for (Entry<Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
	        	
	            Node adjacentNode = adjacencyPair.getKey();
	            Integer edgeWeight = adjacencyPair.getValue();
	            
	            if (!settledNodes.contains(adjacentNode)) {
	                calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
	                unsettledNodes.add(adjacentNode);
	            }
	        }
	        
	        settledNodes.add(currentNode);
	    }
	}
	
	private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
	    Node lowestDistanceNode = null;
	    
	    Integer lowestDistance = Integer.MAX_VALUE;
	    
	    for (Node node: unsettledNodes) {
	    	Integer nodeDistance = node.getDistance();
	        
	        if (nodeDistance < lowestDistance) {
	            lowestDistance = nodeDistance;
	            lowestDistanceNode = node;
	        }
	    }
	    
	    return lowestDistanceNode;
	}
	
	private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
	    
	    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
	        evaluationNode.setDistance(sourceDistance + edgeWeigh);
	        LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
	        
	        shortestPath.add(sourceNode);
	        evaluationNode.setShortestPath(shortestPath);
	    }
	}
}