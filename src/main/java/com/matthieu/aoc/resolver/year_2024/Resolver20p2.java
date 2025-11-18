package com.matthieu.aoc.resolver.year_2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver20p2 extends Resolver20p1 {

    private Set<Duo<Point, Point>> relevantCheats;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);
        this.relevantCheats = new HashSet<>();
    }

    @Override
    public boolean solve() throws Exception {

        this.map.forEach((x, y, c) -> {
            if (c == '#') {
                return;
            }
            // Is on path

            Point currentPosInPath = new Point(x, y);

            this.relevantCheats
                    .addAll(findCheatAround(currentPosInPath).stream().map(a -> new Duo<>(currentPosInPath, a)).toList());
        });

        return true;
    }

    @Override
    public String get() {
        return relevantCheats.size() + "";
    }

    protected Set<Point> findCheatAround(Point position) {
        Set<Point> cheatArrivals = new HashSet<>();
        int currentDistance = this.graph.get(position).getDistance();

        for (int r = 2; r <= 20; r++) {
            for (int x = 0; x <= r; x++) {
                int y = r - x;

                List<Point> arrivals = Arrays.asList(
                        new Point(position.x() + x, position.y() + y),
                        new Point(position.x() - x, position.y() + y),
                        new Point(position.x() + x, position.y() - y),
                        new Point(position.x() - x, position.y() - y));

                for (Point arrival : arrivals) {

                    try {
                        if (this.map.get(arrival) == '#') {
                            continue;
                        }
                    } catch (Exception e) {
                        // Out of map
                        continue;
                    }

                    int timeSaved = currentDistance - this.graph.get(arrival).getDistance();

                    if (timeSaved - r >= this.minTimeSave) {
                        cheatArrivals.add(arrival);
                    }
                }
            }
        }

        return cheatArrivals;
    }

}
