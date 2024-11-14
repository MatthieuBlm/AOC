package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.year_2023.Brick;

public class Resolver22p2 extends Resolver22p1 {

    private long fallenBricks = 0;

    @Override
    public boolean solve() throws Exception {
        while (!fallAll(world).isEmpty()) {}

        bricks.forEach(brickToTest -> {
            Matrix<List<Brick>> copy = new Matrix<>(world.getWidth(), world.getHeight(),
                    (xc, yc) -> world.get(xc, yc).stream().map(b -> b != null ? b.clone() : null)
                            .collect(Collectors.toCollection(ArrayList::new)));

            desintegrate(copy, brickToTest);

            fallenBricks += fallAll(copy).size();
        });

        return true;
    }

    @Override
    public String get() {
        return this.fallenBricks + "";
    }
}
