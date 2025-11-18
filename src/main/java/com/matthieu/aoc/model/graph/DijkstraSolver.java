package com.matthieu.aoc.model.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.matthieu.aoc.model.matrix.Matrix;

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
	
    public static void resetDistances(List<Node> nodes) {
        nodes.forEach(node -> node.setDistance(Integer.MAX_VALUE));
    }
    
    public static void removeNodeFromGraph(Node node) {
        node.getAdjacentNodes().keySet().forEach(adjacent -> {
            adjacent.getAdjacentNodes().remove(node);
        });
    }

    public static void linkNodes(Matrix<Node> graph) {
        graph.cellStream().forEach(cell -> {
            if (cell.value() != null) {
                graph.forEachNeigthboursCross(cell.x(), cell.y(), (xn, yn, cn) -> {
                    if (cn != null) {
                        cell.value().addDestination(cn, 1);
                    }
                });
            }
        });
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
