package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.year_2024.RobotDay21;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver21p1 implements Resolver {

    protected List<RobotDay21> robots;
    protected Map<String, List<Character>> codes;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.codes = values.stream().collect(Collectors.toMap(k -> k, k -> Collections.emptyList()));
        this.robots = new ArrayList<>();
        this.robots.add(new RobotDay21(newNumericPad()));
        this.robots.add(new RobotDay21(newDirectionalPad()));
        this.robots.add(new RobotDay21(newDirectionalPad()));
    }

    @Override
    public boolean solve() throws Exception {

        for (String code : codes.keySet()) {
            List<Character> sequence = code.chars()
                    .mapToObj(c -> (char) c)
                    .map(this::typeDigit)
                    .flatMap(List::stream).toList();

            this.codes.put(code, sequence);
        }

        return true;
    }

    @Override
    public String get() {

        this.codes.entrySet().stream().forEach(entry -> {
            System.out.println(
                    entry.getKey() + ": " + entry.getValue().stream().map(Object::toString).collect(Collectors.joining()));
        });

        return this.codes.entrySet().stream()
                .mapToLong(entry -> Extractor.extractNumbers(entry.getKey()).get(0) * entry.getValue().size())
                .sum() + "";
    }

    protected List<Character> typeDigit(Character c) {
        List<Character> neededMoves = robots.get(0).move(c);
        neededMoves.add('A');

        for (int i = 1; i < robots.size(); i++) {
            // final int currentI = i;

            List<Character> nextMoves = new ArrayList<>();
            for (Character character : neededMoves) {
                nextMoves.addAll(robots.get(i).move(character));
                nextMoves.add('A');
            }

            neededMoves = nextMoves;
            // neededMoves = neededMoves.stream()
            // .map(moveToReverse -> robots.get(currentI).reverseMove(moveToReverse))
            // .flatMap(List::stream)
            // .toList();
        }

        return neededMoves;
    }

    protected CharMatrix newNumericPad() {
        CharMatrix pad = new CharMatrix(3, 4, () -> null);
        pad.set(0, 0, '7');
        pad.set(1, 0, '8');
        pad.set(2, 0, '9');

        pad.set(0, 1, '4');
        pad.set(1, 1, '5');
        pad.set(2, 1, '6');;

        pad.set(0, 2, '1');
        pad.set(1, 2, '2');
        pad.set(2, 2, '3');

        pad.set(1, 3, '0');
        pad.set(2, 3, 'A');

        return pad;
    }

    protected CharMatrix newDirectionalPad() {
        CharMatrix pad = new CharMatrix(3, 2, () -> null);
        pad.set(1, 0, '^');
        pad.set(2, 0, 'A');

        pad.set(0, 1, '<');
        pad.set(1, 1, 'v');
        pad.set(2, 1, '>');

        return pad;
    }
}
