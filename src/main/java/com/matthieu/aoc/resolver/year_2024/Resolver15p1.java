package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver15p1 implements Resolver {

    protected CharMatrix map;
    protected List<Character> moves;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        String line = null;
        List<String> mapDescription = new ArrayList<>();

        while ((line = values.remove(0)) != "") {
            mapDescription.add(line);
        }

        this.map = new CharMatrix(mapDescription);
        this.moves = new ArrayList<>();

        for (String moveLine : values) {
            char[] chars = moveLine.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                moves.add(chars[i]);
            }
        }
    }

    @Override
    public boolean solve() throws Exception {

        return true;
    }

    @Override
    public String get() {
        return null;
    }

    protected void move(Point robot, Point direction) {
        Point position = robot;
        Point nextCell = addOneMove(position, direction);

        while (map.get(nextCell) != '#' && map.get(nextCell) != '.') {
            Character cellValue = map.get(nextCell);
        }
    }

    protected Point addOneMove(Point from, Point direction) {
        return new Point(from.x() + direction.x(), from.y() + direction.y());
    }
}
