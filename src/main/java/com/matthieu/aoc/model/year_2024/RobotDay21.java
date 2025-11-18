package com.matthieu.aoc.model.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;

public class RobotDay21 {

    private CharMatrix pad;
    private Matrix<Node> graph;
    private Point position;

    public RobotDay21(CharMatrix pad) {
        this.pad = pad;
        this.graph = new Matrix<>(pad.getWidth(), pad.getHeight(), (x, y) -> pad.get(x, y) != null ? new Node() : null);

        Cell<Character> startPosition = pad.cellStream()
                .filter(cell -> cell.value() != null && cell.value() == 'A')
                .findAny()
                .orElseThrow();

        this.position = new Point(startPosition.x(), startPosition.y());
        DijkstraSolver.linkNodes(graph);
    }

    public List<Character> move(Character destination) {
        Cell<Character> cellDest = pad.cellStream()
                .filter(cell -> cell.value() != null && cell.value() == destination)
                .findAny()
                .orElseThrow();

        Point nextPos = new Point(cellDest.x(), cellDest.y());
        return move(nextPos);
    }

    public List<Character> move(Point destination) {
        List<Character> moves = new ArrayList<>();

        int deltaX = destination.x() - position.x();
        int deltaY = destination.y() - position.y();

        if (deltaY > 0) {
            append(moves, 'v', deltaY);
        } else if (deltaY < 0) {
            append(moves, '^', deltaY);
        }

        if (deltaX > 0) {
            append(moves, '>', deltaX);
        } else if (deltaX < 0) {
            append(moves, '<', deltaX);
        }

        this.position = destination;

        return moves;
    }

    private void append(List<Character> chars, char c, int count) {
        for (int i = 0; i < Math.abs(count); i++) {
            chars.add(c);
        }
    }
    
    public String printPad() {
        CharMatrix clone = new CharMatrix(pad.getWidth(), pad.getHeight(),
                (x, y) -> pad.get(x, y) == null ? ' ' : pad.get(x, y));

        clone.set(position, 'X');

        return clone.toString();
    }

}
