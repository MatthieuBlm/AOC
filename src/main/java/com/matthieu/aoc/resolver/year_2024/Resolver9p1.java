package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver9p1 implements Resolver {

    protected String diskMap;
    protected List<Integer> blockSizes;
    protected long checksum;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.diskMap = values.get(0);
        this.blockSizes = new ArrayList<>();

        for (char c : diskMap.toCharArray()) {
            blockSizes.add(c - 48);
        }
    }
    // Ex 1
    // 033111322222
    // 012345678911
    // ..........01

    // 1234513 // Block sizes (129)
    // 0..111....22222.333 // Block description
    // 0123456789111111111
    // ..........012345678
    
    // Ex 2
    // 03311122233
    // 01234567891
    // ..........0
    
    // 1230314 (120)
    // 0..111222.3333
    // 01234567891111
    // ..........0123
    
    // Ex 3
    // 043132
    // 012345
    
    // 121314251 (35)
    // 0..1...2....33.....4

    @Override
    public boolean solve() throws Exception {
        int blockIndex = 0;
        int descriptionPointer = 0;

        while (blockIndex < blockSizes.size()) {
            int fileSize = blockSizes.get(blockIndex);
            int fileIndex = this.getBlockDescriptionIndex(blockIndex);

            // Manage file description
            while (fileSize > 0) {
                addToChecksum(descriptionPointer * fileIndex);

                descriptionPointer++;
                fileSize--;
            }

            blockIndex++;

            if (blockIndex > lastBlockIndex()) {
                break;
            }

            // Manage next free space
            int freeSpaceAvailable = blockSizes.get(blockIndex);
            int lastFileDescriptionIndex = getBlockDescriptionIndex(lastBlockIndex());
            
            while (freeSpaceAvailable > 0 && blockIndex < lastBlockIndex()) {
                addToChecksum(descriptionPointer * lastFileDescriptionIndex);

                descriptionPointer++;
                freeSpaceAvailable--;
                reduceLastBlockSize();

                if (blockSizes.get(lastBlockIndex()) <= 0) {
                    removeLastBlock();
                    removeLastBlock();
                    lastFileDescriptionIndex = getBlockDescriptionIndex(lastBlockIndex());
                }
            }

            blockIndex++;
        }

        return true;
    }

    @Override
    public String get() {
        return checksum + "";
    }

    protected void addToChecksum(int value) {
        this.checksum += value;
        System.out.println("Add to checksum " + value + ". Total -> " + checksum);
    }

    protected int getBlockDescriptionIndex(int n) {
        return (int) Math.floor(n / 2d);
    }

    protected void reduceLastBlockSize() {
        int last = blockSizes.size() - 1;
        this.blockSizes.set(last, blockSizes.get(last) - 1);
    }

    protected void removeLastBlock() {
        blockSizes.remove(blockSizes.size() - 1);
    }

    protected String blockSizeToString(List<Integer> n) {
        return n.stream().map(i -> i.toString()).collect(Collectors.joining(","));
    }

    protected int lastBlockIndex() {
        return blockSizes.size() - 1;
    }

    protected enum BlockType {
        FILE,
        FREE_SPACE;
    }

}
