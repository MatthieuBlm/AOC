package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver6p2 extends Resolver6p1 {

    private CharMatrix currentLab;
    private Matrix<List<Character>> visitedMatrix;
    private long nbCycle;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);
    }

    @Override
    public boolean solve() throws Exception {
        Cell<Character> originalGuard = this.lab.cellStream()
                .filter(cell -> isGuard(cell.value()))
                .findAny()
                .orElseThrow();

        this.nbCycle = this.lab.cellStream().mapToInt(cellToTest -> {
            this.visitedMatrix = newVisitedMatrix();
            this.currentLab = cloneLab();
            this.guard = new Cell<>(originalGuard.x(), originalGuard.y(), originalGuard.value());
            this.visitedMatrix.get(guard.x(), guard.y()).add(guard.value());

            if (!(cellToTest.x() == guard.x() && cellToTest.y() == guard.y())) {
                this.currentLab.set(cellToTest.x(), cellToTest.y(), '#');

                try {
                    while (true) {
                        this.moveOneStep();
                        
                        if (isInCycle()) {
                            return 1;
                        }
                        
                        this.visitedMatrix.get(guard.x(), guard.y()).add(guard.value());
                    }
                } catch (Exception e) {}
            }

            return 0;
        }).sum();
        
        return true;
    }

    @Override
    public String get() {
        return this.nbCycle + "";
    }

    @Override
    protected void moveOneStep() {
        Point nextPos = this.nextPos(guard.value(), guard.x(), guard.y());

        if (this.currentLab.get(nextPos) == '#') {
            turnGuard();
            return;
        }

        this.currentLab.set(guard.x(), guard.y(), '.');
        guard.x(nextPos.x());
        guard.y(nextPos.y());
        this.currentLab.set(nextPos.x(), nextPos.y(), guard.value());
    }

    private boolean isInCycle() {
        return this.visitedMatrix.get(guard.x(), guard.y()).contains(guard.value());
    }

    private CharMatrix cloneLab() {
        return new CharMatrix(this.lab.getWidth(), this.lab.getHeight(), this.lab::get);
    }
    private Matrix<List<Character>> newVisitedMatrix() {
        return new Matrix<>(this.lab.getWidth(), this.lab.getHeight(), () -> new ArrayList<>());
    }
}
