package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver9p2 extends Resolver9p1 {

    private List<Duo<Integer, Integer>> stones;

    // 00...111...2...333.44.5555.6666.777.888899
    // 0099.111...2...333.44.5555.6666.777.8888..
    // 0099.1117772...333.44.5555.6666.....8888..
    // 0099.111777244.333....5555.6666.....8888..
    // 00992111777.44.333....5555.6666.....8888..

    // 2333133121414131402

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        String description = values.get(0);
        int memoryIndex = 0;

        for (int descriptionIndex = 0; descriptionIndex < description.length(); descriptionIndex++) {
            int blockSize = 48 - description.charAt(descriptionIndex);
            
            if (descriptionIndex % 2 == 0) {
                // Setup file
                for (int i = 0; i < blockSize; i++) {
                    stones.add(new Duo<>(memoryIndex, blockSize));
                }

                memoryIndex++;
            } else {
                // Setup free space
                for (int i = 0; i < blockSize; i++) {
                    stones.add(new Duo<>(-1, blockSize));
                }
            }
        }
    }

    @Override
    public boolean solve() throws Exception {

        return true;
    }
}
