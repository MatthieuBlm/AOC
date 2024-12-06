package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver6p1 implements Resolver {

    protected CharMatrix lab;
    protected CharMatrix visited;
    protected Cell<Character> guard;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.lab = new CharMatrix(values);
        this.visited = new CharMatrix(this.lab.getWidth(), this.lab.getHeight(), () -> '.');
        this.guard = this.lab.cellStream().filter(cell -> isGuard(cell.value())).findAny().orElseThrow();
        this.visited.set(guard.x(), guard.y(), 'X');
    }

    @Override
    public boolean solve() throws Exception {

        try {
            while (true) {
                this.moveOneStep();
            }
        } catch (Exception e) {}

        return true;
    }

    @Override
    public String get() {
        return this.visited.stream().filter(c -> c == 'X').count() + "";
    }

    protected boolean isGuard(Character c) {
        return c == '<' || c == '>' || c == 'v' || c == '^';
    }

    protected void moveOneStep() {
        Point nextPos = this.nextPos(guard.value(), guard.x(), guard.y());

        if (this.lab.get(nextPos) == '#') {
            turnGuard();
            return;
        }

        this.lab.set(guard.x(), guard.y(), '.');
        guard.x(nextPos.x());
        guard.y(nextPos.y());
        this.lab.set(guard.x(), guard.y(), guard.value());
        this.visited.set(guard.x(), guard.y(), 'X');
    }

    protected void turnGuard() {
        switch (guard.value()) {
            case '<':
                guard.value('^');
                break;
            case '^':
                guard.value('>');
                break;
            case '>':
                guard.value('v');
                break;
            case 'v':
                guard.value('<');
                break;
            default:
                throw new IllegalStateException();
        }
    }

    protected Point nextPos(char direction, int fromX, int fromY) {
        switch(direction) {
            case '<':
                return new Point(fromX - 1, fromY);
            case '^':
                return new Point(fromX, fromY - 1);
            case '>':
                return new Point(fromX + 1, fromY);
            case 'v':
                return new Point(fromX, fromY + 1);
            default:
                throw new IllegalStateException();
        }
    }
}
