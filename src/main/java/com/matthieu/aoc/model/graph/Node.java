package com.matthieu.aoc.model.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Node {

	private String name;
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    
    private Map<Node, Integer> adjacentNodes = new HashMap<>();


    public Node() { }

    public Node(String name) {
        this.name = name;
    }


    public void addDestination(Node destination, Integer distance) {
    	adjacentNodes.put(destination, distance);
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());

        if (adjacentNodes != null) {
            for (Map.Entry<Node, Integer> entry : adjacentNodes.entrySet()) {
                result = prime * result + entry.getKey().getName().hashCode();
                result = prime * result + entry.getValue();
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (adjacentNodes == null) {
            if (other.adjacentNodes != null)
                return false;
        } else if (!adjacentNodes.entrySet().stream().allMatch(entry -> other.adjacentNodes.containsKey(entry.getKey())
                && other.adjacentNodes.get(entry.getKey()).equals(entry.getValue()))) {
            return false;
        }
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Node [name=" + name + ", adjacentNodes=[" + adjacentNodes.entrySet().stream()
                .map(entry -> "(" + entry.getKey().getName() + "," + entry.getValue() + ")").collect(Collectors.joining(", "))
                + "]]";
    }
    
    
}
