package com.matthieu.aoc.resolver.year_2024;

import java.util.Objects;

import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.Cell;

public class Resolver18p2 extends Resolver18p1 {

    private int fatalByteIndex;

    @Override
    public boolean solve() throws Exception {
        super.solve();
        
        Node start = memory.get(0, 0);
        Node end = memory.get(width - 1, height - 1);

        for (int i = toTake; i < bytesFalling.size(); i++) {
            Point fallenByte = bytesFalling.get(i);
            Node corruptNode = memory.get(fallenByte);

            makeByteFall(fallenByte);

            DijkstraSolver.removeNodeFromGraph(corruptNode);
            DijkstraSolver.resetDistances(memory.cellStream().map(Cell::value).filter(Objects::nonNull).toList());
            DijkstraSolver.calculateShortestPathFromSource(start);

            if (end.getDistance() == Integer.MAX_VALUE) {
                fatalByteIndex = i;
                break;
            }
        }

        return true;
    }

    @Override
    public String get() {
        return this.bytesFalling.get(fatalByteIndex) + "";
    }

}
