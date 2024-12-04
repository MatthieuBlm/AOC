package com.matthieu.aoc.resolver.year_2024;

import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver4p1 implements Resolver {

    protected CharMatrix data;
    protected long count;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.data = new CharMatrix(values);
    }

    @Override
    public boolean solve() throws Exception {

        this.data.forEach((x, y, c) -> {
            if (c == 'X') {
                this.explore(x, y);
            }
        });

        return true;
    }

    @Override
    public String get() {
        return count + "";
    }

    protected void explore(int x, int y) {
        this.data.forEachNeigthbours(x, y, (xn, yn, cn) -> {
            Point vector = new Point(xn - x, yn - y);

            count += isXmas(x, y, vector, new char[] {data.get(x, y)}) ? 1 : 0;
        });
    }

    protected boolean isXmas(int x, int y, Point vector, char[] currentWord) {
        if (this.isXmas(currentWord)) {
            return true;
        } else if (currentWord.length > 4) {
            return false;
        }

        int nextX = x + vector.x();
        int nextY = y + vector.y();

        try {
            char nextChar = this.data.get(nextX, nextY);

            char[] nextWord = Arrays.copyOf(currentWord, currentWord.length + 1);
            nextWord[nextWord.length - 1] = nextChar;

            return this.isXmas(nextX, nextY, vector, nextWord);

        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isXmas(char[] word) {
        return word.length == 4 && word[0] == 'X' && word[1] == 'M' && word[2] == 'A' && word[3] == 'S';
    }
}
