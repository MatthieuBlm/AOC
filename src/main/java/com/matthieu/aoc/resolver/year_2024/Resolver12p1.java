package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver12p1 implements Resolver {

    protected CharMatrix map;
    protected Matrix<List<Point>> regions;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.map = new CharMatrix(values);
        this.regions = new Matrix<>(map.getWidth(), map.getHeight(), ArrayList::new);
    }

    @Override
    public boolean solve() throws Exception {
        findRegions();
        return true;
    }

    @Override
    public String get() {
        return this.regions.cellStream()
                .map(Cell::value)
                .distinct()
                .mapToLong(this::getRegionPrice)
                .sum() + "";
    }

    protected void findRegions() {
        map.forEach((x, y, c) -> this.regions.get(x, y).add(new Point(x, y)));
        map.forEach((x, y, c) -> {
            map.forEachNeigthboursCross(x, y, (nx, ny, nc) -> {
                List<Point> region = this.regions.get(x, y);
                List<Point> nRegion = this.regions.get(nx, ny);

                if (nc == c && region != nRegion) {
                    region.addAll(nRegion);
                    nRegion.forEach(p -> regions.set(p.x(), p.y(), region));
                }
            });
        });
    }

    protected int getRegionPerimeter(List<Point> region) {
        return region.parallelStream()
                .mapToInt(p -> Matrix.getNeigthboursCrossCoords(p.x(), p.y()).parallelStream()
                        .mapToInt(neigthbour -> isNotSameRegion(p.x(), p.y(), neigthbour.x(), neigthbour.y()) ? 1 : 0).sum())
                .sum();
    }

    protected boolean isNotSameRegion(int x1, int y1, int x2, int y2) {
        return map.isOut(x2, y2) || map.get(x2, y2) != map.get(x1, y1);
    }

    protected long getRegionPrice(List<Point> region) {
        return region.size() * getRegionPerimeter(region);
    }

    protected String getRegionPrint(List<Point> region) {
        CharMatrix print = new CharMatrix(map.getWidth(), map.getHeight(), () -> '.');

        region.forEach(p -> print.set(p.x(), p.y(), '#'));

        return print.toString();
    }
}
