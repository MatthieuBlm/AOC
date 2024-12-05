package com.matthieu.aoc.resolver.year_2024;

import java.util.List;
import java.util.function.Predicate;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver5p2 extends Resolver5p1 {

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);

        // Restore ordering
        List<List<Integer>> missordered = this.updates.stream().filter(Predicate.not(this::isValidUpdate)).toList();

        for (List<Integer> update : missordered) {
            ErrorDescription error;

            while ((error = this.hasError(update)) != null) {
                swap(update, error.ia, error.ib);
            }
        }

        this.updates = missordered;
    }

    private void swap(List<Integer> update, int ia, int ib) {
        Integer a = update.get(ia);
        Integer b = update.get(ib);
        update.set(ia, b);
        update.set(ib, a);
    }

    private ErrorDescription hasError(List<Integer> update) {
        for (int i = 0; i < update.size(); i++) {
            Integer n = update.get(i);
            List<Duo<Integer, Integer>> relatedOrdering = getRelatedOrdering(n);

            for (Duo<Integer, Integer> order : relatedOrdering) {
                int ortherIndex = isBefore(update, i, order.b());

                if (order.a() == n && ortherIndex != -1) {
                    return new ErrorDescription(n, order, i, ortherIndex);
                }

                ortherIndex = isAfter(update, i, order.b());

                if (order.b() == n && ortherIndex != -1) {
                    return new ErrorDescription(n, order, i, ortherIndex);
                }
            }
        }

        return null;
    }

    private record ErrorDescription(Integer n, Duo<Integer, Integer> rule, int ia, int ib) {}
}
