package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point3D;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.year_2023.Brick;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver22p1 implements Resolver {

    private static int MAX_HEIGHT = 400;

    private List<Brick> bricks;
    private Matrix<List<Brick>> world;
    
    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.bricks = new ArrayList<>();
        this.world = new Matrix<>(10, 10, () -> new ArrayList<>(MAX_HEIGHT));
        
        for (String value : values) {
            String[] parts = value.split("~");
            int[] originA = Stream.of(parts[0].split(",")).mapToInt(Integer::parseInt).toArray();
            int[] originB = Stream.of(parts[1].split(",")).mapToInt(Integer::parseInt).toArray();

            bricks.add(new Brick(new Point3D(originA[0], originA[1], originA[2]),
                    new Point3D(originB[0], originB[1], originB[2])));
        }

        for (Brick brick : bricks) {
            for (Point3D block : brick.getBlocks()) {
                world.get(block.x(), block.y()).add(block.z(), brick);
            }
        }
    }

    @Override
    public boolean solve() throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String get() {
        // TODO Auto-generated method stub
        return null;
    }

    private void fall() {
        for (int z = 2; z < MAX_HEIGHT; z++) {

        }
    }

    private boolean haveSupport(Brick b) {
        for (Point3D block : b.getBlocks()) {
            if (block.z() == 1 || world.get(block.x(), block.y()).get(block.z() - 1) != null) {
                return true;
            }
        }

        return false;
    }
}
