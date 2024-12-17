package com.matthieu.aoc.resolver.year_2024;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.CharMatrix;

public class Resolver15p2 extends Resolver15p1 {

	protected CharMatrix map;
	
	private record Box(Point left, Point right) {}
	
	@Override
    public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.map = new CharMatrix(super.map.getWidth() * 2, super.map.getHeight(), () -> '.');
		this.robot.setX(this.robot.getX() * 2);
		
		super.map.cellStream().forEach(cell -> {
			if(cell.value() == '#') {
				this.map.set(cell.x() * 2, cell.y(), '#');
				this.map.set(cell.x() * 2 + 1, cell.y(), '#');
			} else if(cell.value() == 'O') {
				this.map.set(cell.x() * 2, cell.y(), '[');
				this.map.set(cell.x() * 2 + 1, cell.y(), ']');
			}
		});
	}
	
    @Override
    public String get() {
        return this.map.cellStream()
        		.filter(cell -> cell.value() == '[')
        		.mapToLong(cell -> cell.x() + cell.y() * 100l)
        		.sum() + "";
    }
    
	@Override
    protected void move(Point robot, Point direction) {
        Point position = robot;
        Point nextCell = addOneMove(position, direction);

        if(map.get(nextCell) != '.' && map.get(nextCell) != '#') {
        	pushBox(nextCell, direction);
        }
        
        if(map.get(nextCell) == '.') {
        	robot.setX(nextCell.x());
        	robot.setY(nextCell.y());
        }
    }
	
	@Override
    protected boolean pushBox(Point edge, Point direction) {
		Box box = this.isBox(edge);
    	
		if(canMove(box, direction)) {
			pushBox(box, direction);
			return true;
		}
		
		return false;
	}
	
	private boolean pushBox(Box box, Point direction) {
		
		if(canImmediatelyMove(box, direction) || getAdjacentBoxes(box, direction).stream().allMatch(nBox -> pushBox(nBox, direction))) {
			moveBox(box, direction);
			return true;
		}
		
		return false;
	}
	
	private void moveBox(Box box, Point direction) {
		this.map.set(box.left(), '.');
		this.map.set(box.right(), '.');
		this.map.set(box.left().x() + direction.x(), box.left().y() + direction.y(), '[');
		this.map.set(box.right().x() + direction.x(), box.right().y() + direction.y(), ']');
	}
	
	private Box getBox(Point edge) {
		Point left = null;
		Point right = null;
		
		if(map.get(edge) == '[') {
			left = edge;
			right = new Point(edge.x() + 1, edge.y());
		} else {
			right = edge;
			left = new Point(edge.x() - 1, edge.y());
		}
		
		return new Box(left, right);
	}
	
	private boolean canImmediatelyMove(Box box, Point direction) {
		Point nextLeft = new Point(box.left().x() + direction.x(), box.left().y() + direction.y());
		Point nextRight = new Point(box.right().x() + direction.x(), box.right().y() + direction.y());
		
		Direction moveDirection = Direction.fromVector(direction);
		
		return (map.get(nextRight) == '.' && map.get(nextLeft) == '.') 
				|| (moveDirection == Direction.EAST && map.get(nextRight) == '.')
				|| (moveDirection == Direction.WEST && map.get(nextLeft) == '.');
	}
	
	private boolean canMove(Box box, Point direction) {
		Point nextLeft = new Point(box.left().x() + direction.x(), box.left().y() + direction.y());
		Point nextRight = new Point(box.right().x() + direction.x(), box.right().y() + direction.y());
		
		Direction moveDirection = Direction.fromVector(direction);
		
		if(canImmediatelyMove(box, direction)) {
			return true;
		} else if(map.get(nextLeft) == '#' || map.get(nextRight) == '#') {
			return false;
		}
		
		if(moveDirection == Direction.EAST) {
			return canMove(isBox(nextRight), direction);
		} else if(moveDirection == Direction.WEST) {
			return canMove(isBox(nextLeft), direction);
		}
		
		return Stream.of(isBox(nextLeft), isBox(nextRight))
				.filter(Objects::nonNull)
				.allMatch(b -> canMove(b, direction));
	}
	
	private List<Box> getAdjacentBoxes(Box box, Point direction) {
		Point nextLeft = new Point(box.left().x() + direction.x(), box.left().y() + direction.y());
		Point nextRight = new Point(box.right().x() + direction.x(), box.right().y() + direction.y());
		
		Direction moveDirection = Direction.fromVector(direction);
		
		if(moveDirection == Direction.EAST && map.get(box.right().x() + 1, box.right().y()) == '[') {
			return List.of(getBox(new Point(box.right().x() + 1, box.right().y())));
		} else if(moveDirection == Direction.WEST && map.get(box.left().x() - 1, box.left().y()) == ']') {
			return List.of(getBox(new Point(box.left().x() - 1, box.left().y())));
		}
		
		return Stream.of(isBox(nextLeft), isBox(nextRight)).filter(Objects::nonNull).distinct().toList();
	}
	
	private Box isBox(Point pos) {
		if(this.map.get(pos) == '[' || this.map.get(pos) == ']') {
			return getBox(pos);
		}
		
		return null;
	}
	
	@Override
	protected String printMap() {
		CharMatrix clone = new CharMatrix(map.getWidth(), map.getHeight(), map::get);
		clone.set(robot, '@');
		return clone.toString();
	}
}
