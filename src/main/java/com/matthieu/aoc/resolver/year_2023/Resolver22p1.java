package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
    
    private Set<Brick> canBeDesintegrated;
    
    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.bricks = new ArrayList<>();
        this.world = new Matrix<>(10, 10, () -> emptyList());
        
        for (int i = 0; i < values.size(); i++) {
        	String value = values.get(i);
        	
            String[] parts = value.split("~");
            int[] originA = Stream.of(parts[0].split(",")).mapToInt(Integer::parseInt).toArray();
            int[] originB = Stream.of(parts[1].split(",")).mapToInt(Integer::parseInt).toArray();

            bricks.add(new Brick(new Point3D(originA[0], originA[1], originA[2]),
                    new Point3D(originB[0], originB[1], originB[2]), i + ""));
        }

        for (Brick brick : bricks) {
            for (Point3D block : brick.getBlocks()) {
                world.get(block.x(), block.y()).set(block.z(), brick);
            }
        }
    }

    @Override
    public boolean solve() throws Exception {
    	while(fallAll(world)) {}
    	
        this.canBeDesintegrated = new HashSet<>();
    	
        bricks.forEach(brickToTest -> {
            Matrix<List<Brick>> copy = new Matrix<>(world.getWidth(), world.getHeight(),
                    (xc, yc) -> world.get(xc, yc).stream().map(b -> b != null ? b.clone() : null)
                            .collect(Collectors.toCollection(ArrayList::new)));

            desintegrate(copy, brickToTest);

            if (!fallAll(copy)) {
                this.canBeDesintegrated.add(brickToTest);
            }
        });
    	
        return true;
    }

    @Override
    public String get() {
        return this.canBeDesintegrated.size() + "";
    }

    private boolean fallAll(Matrix<List<Brick>> world) {
    	boolean someBrickHasFallen = false;
    	
        for (int z = 2; z < MAX_HEIGHT; z++) {
        	
        	for (int x = 0; x <= world.getMaxX(); x++) {
        		for (int y = 0; y <= world.getMaxY(); y++) {
        			Brick b = world.get(x, y).get(z);
        			
        			if(b != null && !haveSupport(world, b)) {
        				fall(world, b);
        				someBrickHasFallen = true;
        			}
        		}
        	}
        }
        
        return someBrickHasFallen;
    }
    
    private static void fall(Matrix<List<Brick>> world, Brick b) {
    	desintegrate(world, b);
    	
    	for(Point3D block : b.getBlocks()) {
            block.setZ(block.getZ() - 1);
            world.get(block.x(), block.y()).set(block.z(), b);
    	}
    }

    private static boolean haveSupport(Matrix<List<Brick>> world,  Brick b) {
    	for (Point3D block : b.getBlocks()) {
    		Brick underneathBrick = world.get(block.x(), block.y()).get(block.z() - 1);
    		
            if (block.z() == 1 || (underneathBrick != null && underneathBrick != b)) {
                return true;
            }
        }

        return false;
    }
    
    private static void desintegrate(Matrix<List<Brick>> world, Brick b) {
    	for (Point3D block : b.getBlocks()) {
    		world.get(block.x(), block.y()).set(block.z(), null);
    	}
    }
    
    private static List<Brick> emptyList() {
    	ArrayList<Brick> l = new ArrayList<>();
    	
    	for(int i = 0; i < MAX_HEIGHT; i++) {
    		l.add(null);
    	}
    	
    	return l;
    }
    
    private static void printXZ(Matrix<List<Brick>> world) {
    	for(int z = MAX_HEIGHT - 1; z >= 0; z--) {
    		String line = "";
    		
    		for(int x = 0; x < world.getWidth(); x++) {
                String c = ".";
    			
    			for(int y = 0; y < world.getHeight(); y++) {
    				Brick b = world.get(x, y).get(z);
    				
    				if(b != null) {
    					c = b.getName();
    					break;
    				}
    			}
    			
    			line += c;
    		}
    		
    		System.out.println(line);
    	}
    	
    	System.out.println();
    }
    
    private static void printYZ(Matrix<List<Brick>> world) {
    	for(int z = MAX_HEIGHT - 1; z >= 0; z--) {
    		String line = "";
    		
			for(int y = 0; y < world.getHeight(); y++) {
                String c = ".";
    			
    			for(int x = 0; x < world.getWidth(); x++) {
    				Brick b = world.get(x, y).get(z);
    				
    				if(b != null) {
    					c = b.getName();
    					break;
    				}
    			}
    			
    			line += c;
    		}
    		
    		System.out.println(line);
    	}
    	
    	System.out.println();
    }
}
