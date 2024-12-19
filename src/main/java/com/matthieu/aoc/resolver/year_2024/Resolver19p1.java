package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver19p1 implements Resolver {

    protected List<String> towels;
    protected List<String> designs;
    protected List<String> possibleDesigns;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.towels = Stream.of(values.remove(0).split(", ")).collect(Collectors.toList());
        values.remove(0);
        this.designs = values;
        this.possibleDesigns = new ArrayList<>();

        this.towels.addAll(this.createTowelCombinaisons());

        Collections.sort(towels, (ta, tb) -> tb.length() - ta.length());
    }

    @Override
    public boolean solve() throws Exception {

        for (String design : designs) {
            if (hasTowelMatchDesign(towels, design)) {
                this.possibleDesigns.add(design);
            }
        }

        return true;
    }

    @Override
    public String get() {
        return this.possibleDesigns.size() + "";
    }


    protected boolean hasTowelMatchDesign(List<String> towels, String design) {
        int position = 0;

        while (position != -1 && position < design.length()) {
            position = hasTowelMatchDesign(towels, design, position);
        }

        if (position == design.length()) {
            return true;
        }

        return false;
    }

    /**
     * @param design the design to test
     * @param position the position to match
     * @return the last index of the match
     */
    protected int hasTowelMatchDesign(List<String> towels, String design, int position) {
        for (String towel : towels) {
            if (design.indexOf(towel, position) == position) {
                return position + towel.length();
            }
        }

        return -1;
    }
    
    protected List<String> createTowelCombinaisons() {
        StringBuilder builder = new StringBuilder();
        List<String> towelsCombinaisons = new ArrayList<>();

        for (int i = 0; i < towels.size(); i++) {
            String currentTowel = towels.get(i);

            for (int j = 0; j < towels.size(); j++) {
                builder.delete(0, builder.length());

                builder.append(currentTowel);
                builder.append(towels.get(j));

                towelsCombinaisons.add(builder.toString());
            }
        }

        return towelsCombinaisons;
    }

}
