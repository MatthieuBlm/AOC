package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver9p2 extends Resolver9p1 {

    private List<Duo<Integer, Integer>> blocks;

    // 00...111...2...333.44.5555.6666.777.888899
    // 0099.111...2...333.44.5555.6666.777.8888..
    // 0099.1117772...333.44.5555.6666.....8888..
    // 0099.111777244.333....5555.6666.....8888..
    // 00992111777.44.333....5555.6666.....8888..

    // 2333133121414131402

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        String description = values.get(0);
        blocks = new ArrayList<>();
        int memoryIndex = 0;

        for (int descriptionIndex = 0; descriptionIndex < description.length(); descriptionIndex++) {
            int blockSize = description.charAt(descriptionIndex) - 48;
            
            if (descriptionIndex % 2 == 0) {
                // Setup file
                for (int i = 0; i < blockSize; i++) {
                	blocks.add(new Duo<>(memoryIndex, blockSize));
                }

                memoryIndex++;
            } else {
                // Setup free space
                for (int i = 0; i < blockSize; i++) {
                	blocks.add(new Duo<>(-1, blockSize));
                }
            }
        }
    }

    @Override
    public boolean solve() throws Exception {
    	
    	// Try to pad each blocks
    	for (int i = blocks.size() - 1; i >= 0; i--) {
    		// Found a block
    		if(blocks.get(i).a() >= 0) {
    			Duo<Integer, Integer> block = blocks.get(i);
    			int blockSize = block.b();
    			
    			// Search for free space
    			for (int j = 0; j < i; j++) {
    				// Is free space
    				if(blocks.get(j).a() == -1 && blocks.get(j).b() >= blockSize) {
    					// Replace free space with block
    					for (int k = 0; k < blockSize; k++) {
    						blocks.set(j + k, blocks.get(i - k));
    					}
    					
    					// Set previous block as free space
    					for (int k = 0; k < blockSize; k++) {
    						blocks.set(i - k, new Duo<>(-1, blockSize));
    					}
    					
    					// Decrease remaining free space
    					Duo<Integer, Integer> remainingFreeSpace;
    					for(int l = 0; (remainingFreeSpace = blocks.get(j + blockSize + l)).a() == -1; l++) {
    						remainingFreeSpace.b(remainingFreeSpace.b() - blockSize);
    					}
    					
    					break;
    				}
    			}
    		}
		}
    	
        return true;
    }
    
    @Override
    public String get() {
        return IntStream.range(0, blocks.size())
        		.filter(i -> blocks.get(i).a() > 0)
        		.mapToLong(i -> i * blocks.get(i).a())
        		.sum() + "";
    	
    }
    
    private String printMap() {
    	StringBuilder builder = new StringBuilder();
    	
    	for (Duo<Integer, Integer> block : blocks) {
    		if(block.a() == -1) {
    			builder.append('.');
    		} else {
    			builder.append(block.a());
    		}
		}
    	
    	return builder.toString();
    }
}
