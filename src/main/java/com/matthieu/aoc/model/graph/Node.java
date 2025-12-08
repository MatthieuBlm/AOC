package com.matthieu.aoc.model.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Node {

	private String name;
    private List<Node> shortestPath;
    private Integer distance;
    
    private Map<Node, Integer> adjacentNodes;


    public Node() {
        this(UUID.randomUUID().toString());
    }

    public Node(String name) {
        this.name = name;
        this.adjacentNodes = new HashMap<>();
        this.initialize();
    }


    public void addDestination(Node destination, Integer distance) {
    	adjacentNodes.put(destination, distance);
    }
    
    public void initialize() {
    	this.shortestPath = new LinkedList<>();
    	this.distance = Integer.MAX_VALUE;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

    @Override
    public String toString() {
        return "Node [name=" + name + ", adjacentNodes=[" + adjacentNodes.entrySet().stream()
                .map(entry -> "(" + entry.getKey().getName() + "," + entry.getValue() + ")").collect(Collectors.joining(", "))
                + "]]";
    }
    
}
