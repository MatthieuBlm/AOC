package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver23p1 implements Resolver {

    protected Matrix<Node> map;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.map = new Matrix<>(values, Node::new);
    }

    @Override
    public boolean solve() throws Exception {
        this.map.forEach((x, y, node) -> {
            if (x == 0 || y == 0 || x == map.getMaxX() || y == map.getMaxY()) {
                return;
            }

            Node upperNode = this.map.get(x, y - 1);
            Node lowerNode = this.map.get(x, y + 1);
            Node leftNode = this.map.get(x - 1, y);
            Node rightNode = this.map.get(x + 1, y);

            if (upperNode.getName().equals(".") || upperNode.getName().equals("^")) {
                node.addDestination(upperNode, -1);
            }
            if (lowerNode.getName().equals(".") || lowerNode.getName().equals("v")) {
                node.addDestination(lowerNode, -1);
            }
            if (leftNode.getName().equals(".") || leftNode.getName().equals("<")) {
                node.addDestination(leftNode, -1);
            }
            if (rightNode.getName().equals(".") || rightNode.getName().equals(">")) {
                node.addDestination(rightNode, -1);
            }
        });

        DijkstraSolver.calculateShortestPathFromSource(map.get(0, 1));

        return true;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE - 1);
    }

    @Override
    public String get() {
        return map.get(map.getMaxX() - 1, map.getMaxY()).getDistance() + "";
    }

}
