package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver14p1 implements Resolver {

    protected List<Duo<Point, Point>> robots; // <Position, Velocity>
    protected int width;
    protected int height;
    protected int middleX = (int) (width / 2d);
    protected int middleY = (int) (height / 2d);
    protected int seconds;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.width = 101;
        this.height = 103;
        this.middleX = (int) (width / 2d);
        this.middleY = (int) (height / 2d);
        this.seconds = 100;
        this.robots = values.stream().map(value -> {
            List<Long> n = Extractor.extractNumbers(value);
            Point position = new Point(n.get(0).intValue(), n.get(1).intValue());
            Point velocity = new Point(n.get(2).intValue(), n.get(3).intValue());
            return new Duo<>(position, velocity);
        }).toList();
    }

    @Override
    public boolean solve() throws Exception {

        moveRobots();

        return true;
    }

    @Override
    public String get() {
        return this.getRobotCountOnCorners().stream().reduce((acc, v) -> acc * v).get().toString();
    }

    protected List<Long> getRobotCountOnCorners() {
        long topLeft = 0, topRight = 0, bottomLeft = 0, bottomRight = 0;

        for(Duo<Point, Point> robot: robots) {
            int x = robot.a().x();
            int y = robot.a().y();
            
            if(x < middleX && y < middleY) {
                topLeft++;
            } else if(x > middleX && y < middleY) {
                topRight++;
            } else if(x < middleX && y > middleY) {
                bottomLeft++;
            } else if(x > middleX && y > middleY) {
                bottomRight++;
            }
        };

        return List.of(topLeft, topRight, bottomLeft, bottomRight);
    }

    protected void moveRobots() {
        for (Duo<Point, Point> robot : robots) {
            Point pos = robot.a();
            Point vel = robot.b();
            int newX = positiveModulo((pos.x() + vel.x() * seconds), width);
            int newY = positiveModulo((pos.y() + vel.y() * seconds), height);
            pos.set(newX, newY);
        }
    }

    protected int positiveModulo(int value, int modulo) {
        return ((value % modulo) + modulo) % modulo;
    }

}
