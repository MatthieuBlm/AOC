package com.matthieu.aoc.resolver.year_2024;

public class Resolver9p2 extends Resolver9p1 {

    // 00...111...2...333.44.5555.6666.777.888899
    // 0099.111...2...333.44.5555.6666.777.8888..
    // 0099.1117772...333.44.5555.6666.....8888..
    // 0099.111777244.333....5555.6666.....8888..
    // 00992111777.44.333....5555.6666.....8888..

    // 2333133121414131402

    @Override
    public boolean solve() throws Exception {
        int blockIndex = lastBlockIndex();
        int descriptionPointer = this.getBlockDescriptionIndex(blockIndex);

        while (blockIndex > 0) {
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
}
