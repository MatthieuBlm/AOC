package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.model.Point;

public class Resolver12p2 extends Resolver12p1 {

    @Override
    protected int getRegionPerimeter(List<Point> region) {
        return region.parallelStream()
                .mapToInt(p -> {
                    int corners = 0;

                    // Convex corners
                    if (isNotSameRegion(p.x(), p.y(), p.x(), p.y() - 1) && isNotSameRegion(p.x(), p.y(), p.x() + 1, p.y())) {
                        corners++;
                    }
                    if (isNotSameRegion(p.x(), p.y(), p.x(), p.y() + 1) && isNotSameRegion(p.x(), p.y(), p.x() + 1, p.y())) {
                        corners++;
                    }
                    if (isNotSameRegion(p.x(), p.y(), p.x(), p.y() + 1) && isNotSameRegion(p.x(), p.y(), p.x() - 1, p.y())) {
                        corners++;
                    }
                    if (isNotSameRegion(p.x(), p.y(), p.x(), p.y() - 1) && isNotSameRegion(p.x(), p.y(), p.x() - 1, p.y())) {
                        corners++;
                    }
                    // Concave corners
                    if (!isNotSameRegion(p.x(), p.y(), p.x(), p.y() - 1) && !isNotSameRegion(p.x(), p.y(), p.x() + 1, p.y())
                            && isNotSameRegion(p.x(), p.y(), p.x() + 1, p.y() - 1)) {
                        corners++;
                    }
                    if (!isNotSameRegion(p.x(), p.y(), p.x(), p.y() + 1) && !isNotSameRegion(p.x(), p.y(), p.x() + 1, p.y())
                            && isNotSameRegion(p.x(), p.y(), p.x() + 1, p.y() + 1)) {
                        corners++;
                    }
                    if (!isNotSameRegion(p.x(), p.y(), p.x(), p.y() + 1) && !isNotSameRegion(p.x(), p.y(), p.x() - 1, p.y())
                            && isNotSameRegion(p.x(), p.y(), p.x() - 1, p.y() + 1)) {
                        corners++;
                    }
                    if (!isNotSameRegion(p.x(), p.y(), p.x(), p.y() - 1) && !isNotSameRegion(p.x(), p.y(), p.x() - 1, p.y())
                            && isNotSameRegion(p.x(), p.y(), p.x() - 1, p.y() - 1)) {
                        corners++;
                    }

                    return corners;
                }).sum();
    }
}
