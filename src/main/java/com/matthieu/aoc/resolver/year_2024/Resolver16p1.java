package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver16p1 implements Resolver {

    protected CharMatrix maze;
    protected Matrix<Integer> distances;
    protected Point start;
    protected Point end;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.maze = new CharMatrix(values);
        this.distances = new Matrix<>(maze.getWidth(), maze.getHeight(), () -> Integer.MAX_VALUE);
        this.start = new Point(1, maze.getMaxY() - 1);
        this.end = new Point(maze.getMaxX() - 1, 1);
        this.maze.set(start, '.');
        this.maze.set(end, '.');
        this.distances.set(end, 0);
    }

    @Override
    public boolean solve() throws Exception {

        // Find minimal distance
        this.maze.forEachNeigthboursCross(end.x(), end.y(), (xn, yn, cn) -> {
            if (cn == '.') {
                explore(end, new Point(xn, yn), 1, Direction.fromVector(xn - end.x(), yn - end.y()));
            }
        });

        return true;
    }

    @Override
    public String get() {
        return distances.get(start) + "";
    }

    protected void explore(Point from, Point current, int currentDistance, Direction currentDirection) {
        this.distances.set(current, currentDistance);

        if (current.equals(start)) {
            // Should end with east direction
            if (currentDirection != Direction.EAST) {
                this.distances.set(start, currentDistance + 1000);
            }
            return;
        }

        this.maze.forEachNeigthboursCross(current.x(), current.y(), (xn, yn, cn) -> {
            if (cn != '.' || (xn == from.x() && yn == from.y())) {
                return;
            }

            Direction nextDirection = Direction.fromVector(xn - current.x(), yn - current.y());
            int turn = currentDirection == nextDirection ? 0 : 1;
            int newNDistance = currentDistance + 1 + turn * 1000;
            int nDistance = this.distances.get(xn, yn);

            if (newNDistance < nDistance) {
                this.explore(current, new Point(xn, yn), currentDistance + 1 + turn * 1000, nextDirection);
            }
        });
    }

    protected String getPrintableMaze(Point current) {
        CharMatrix clone = new CharMatrix(maze.getWidth(), maze.getHeight(), maze::get);
        clone.set(current, 'X');
        return clone.toString();
    }

}

