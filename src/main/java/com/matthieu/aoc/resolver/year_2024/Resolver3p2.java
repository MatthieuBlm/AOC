package com.matthieu.aoc.resolver.year_2024;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver3p2 extends Resolver3p1 {

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);
        this.pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)|don't\\(\\)|do\\(\\)");
    }

    @Override
    public boolean solve() throws Exception {
        List<MatchResult> results = this.pattern.matcher(memory).results().toList();

        boolean ignore = false;

        for (MatchResult matchResult : results) {
            String match = matchResult.group(0);

            if (match.equals("do()")) {
                ignore = false;
            } else if (match.equals("don't()")) {
                ignore = true;
            } else if (!ignore) {
                sum += super.mul(matchResult.group(0));
            }
        }

        return true;
    }
}
