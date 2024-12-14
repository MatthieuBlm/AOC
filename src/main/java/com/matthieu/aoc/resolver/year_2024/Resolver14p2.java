package com.matthieu.aoc.resolver.year_2024;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver14p2 extends Resolver14p1 {

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);
        this.seconds = 1;
    }

    @Override
    public boolean solve() throws Exception {

        for (int i = 0; i < 100000; i++) {
            moveRobots();
            printImage(i);
        }

        return true;
    }

    private void printImage(int iteration) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);

        for (Duo<Point, Point> robot : robots) {
            g.fillRect(robot.a().x(), robot.a().y(), 1, 1);
        }

        g.dispose();

        File directory = new File("2024-d14-p2");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            File outputFile = Paths.get("2024-d14-p2", iteration + ".jpg").toFile();
            ImageIO.write(image, "jpg", outputFile);
            System.out.println("Image sauvegardée : " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
