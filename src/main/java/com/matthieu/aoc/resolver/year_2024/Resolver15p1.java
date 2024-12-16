package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver15p1 implements Resolver {

    protected CharMatrix map;
    protected List<Character> moves;
    protected Point robot;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        String line = null;
        List<String> mapDescription = new ArrayList<>();

        while ((line = values.remove(0)) != "") {
            mapDescription.add(line);
        }

        this.map = new CharMatrix(mapDescription);
        this.moves = new ArrayList<>();

        for (String moveLine : values) {
            char[] chars = moveLine.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                moves.add(chars[i]);
            }
        }
        
        // Find robot
        Cell<Character> robotCell = map.cellStream().filter(cell -> cell.value() == '@').findAny().orElseThrow(); 
        this.robot = new Point(robotCell.x(), robotCell.y());
        this.map.set(robot, '.');
    }

    @Override
    public boolean solve() throws Exception {

    	moves.forEach(move -> move(robot, Direction.fromCharacter(move).getVector()));
    	
        return true;
    }

    @Override
    public String get() {
        return this.map.cellStream()
        		.filter(cell -> cell.value() == 'O')
        		.mapToLong(cell -> cell.x() + cell.y() * 100l)
        		.sum() + "";
    }

    protected void move(Point robot, Point direction) {
        Point position = robot;
        Point nextCell = addOneMove(position, direction);

        if(map.get(nextCell) == 'O') {
        	pushBox(nextCell, direction);
        }
        
        if(map.get(nextCell) == '.') {
        	robot.setX(nextCell.x());
        	robot.setY(nextCell.y());
        }
    }

    protected boolean pushBox(Point box, Point direction) {
    	Point nextCell = this.addOneMove(box, direction);
    	
		if(map.get(nextCell) == '.' || (map.get(nextCell) == 'O' && pushBox(nextCell, direction))) {
			map.set(box, '.');
			map.set(nextCell, 'O');
			return true;
		}
		
		return false;
	}

	protected Point addOneMove(Point from, Point direction) {
        return new Point(from.x() + direction.x(), from.y() + direction.y());
    }
	
	protected String printMap() {
		CharMatrix clone = new CharMatrix(map.getWidth(), map.getHeight(), map::get);
		clone.set(robot, '@');
		return clone.toString();
	}
}
