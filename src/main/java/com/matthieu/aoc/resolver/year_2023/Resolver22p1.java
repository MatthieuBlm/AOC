package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point3D;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.year_2023.Brick;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver22p1 implements Resolver {

    private static int MAX_HEIGHT = 400;

    protected List<Brick> bricks;
    protected Matrix<List<Brick>> world;
    
    private List<Brick> canBeDesintegrated;
    
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
                    new Point3D(originB[0], originB[1], originB[2]), ((char) ((i % 78) + 48)) + ""));
        }

        for (Brick brick : bricks) {
            for (Point3D block : brick.getBlocks()) {
                world.get(block.x(), block.y()).set(block.z(), brick);
            }
        }
    }

    @Override
    public boolean solve() throws Exception {
        while (!fallAll(world).isEmpty()) {}

        this.canBeDesintegrated = new ArrayList<>();
    	
        bricks.forEach(brickToTest -> {
            Matrix<List<Brick>> copy = new Matrix<>(world.getWidth(), world.getHeight(),
                    (xc, yc) -> world.get(xc, yc).stream().map(b -> b != null ? b.clone() : null)
                            .collect(Collectors.toCollection(ArrayList::new)));

            desintegrate(copy, brickToTest);


            if (fallAll(copy).isEmpty()) {
                this.canBeDesintegrated.add(brickToTest);
            }
        });
    	
        return true;
    }

    @Override
    public String get() {
        return this.canBeDesintegrated.size() + "";
    }

    protected Set<Brick> fallAll(Matrix<List<Brick>> world) {
    	boolean someBrickHasFallen = false;

        Set<Brick> fallenBricks = new HashSet<>();

        for (Brick b : getDistinctBricks(world)) {
            if (!haveSupport(world, b)) {
                fall(world, b);
                fallenBricks.add(b);
            }
        }
        
        return fallenBricks;
    }
    
    protected static void fall(Matrix<List<Brick>> world, Brick b) {
    	desintegrate(world, b);
    	
    	for(Point3D block : b.getBlocks()) {
            block.setZ(block.getZ() - 1);
            world.get(block.x(), block.y()).set(block.z(), b);
    	}
    }

    protected static boolean haveSupport(Matrix<List<Brick>> world, Brick b) {
    	for (Point3D block : b.getBlocks()) {
    		Brick underneathBrick = world.get(block.x(), block.y()).get(block.z() - 1);
    		
            if (block.z() == 1 || underneathBrick != null && !underneathBrick.equals(b)) {
                return true;
            }
        }

        return false;
    }
    
    protected static void desintegrate(Matrix<List<Brick>> world, Brick b) {
    	for (Point3D block : b.getBlocks()) {
    		world.get(block.x(), block.y()).set(block.z(), null);
    	}
    }
    
    protected static List<Brick> emptyList() {
    	ArrayList<Brick> l = new ArrayList<>();
    	
    	for(int i = 0; i < MAX_HEIGHT; i++) {
    		l.add(null);
    	}
    	
    	return l;
    }
    
    protected static void printXZ(Matrix<List<Brick>> world) {
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
    
    protected static void printYZ(Matrix<List<Brick>> world) {
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

    protected static List<Brick> getDistinctBricks(Matrix<List<Brick>> world) {
        return world.getCells().stream()
                .map(Cell::value)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .distinct()
                .sorted((b1, b2) -> b1.getLowerZ() - b2.getLowerZ())
                .toList();
    }
}
