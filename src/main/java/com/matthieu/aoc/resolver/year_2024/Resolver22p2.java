package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Resolver22p2 extends Resolver22p1 {

    private List<LinkedList<Long>> changes;
    private List<Map<List<Long>, Integer>> cache;
    private long maxBananas;

    public static void main(String[] args) {
        List<Long> l = List.of(-2l, 1l, -4l, 8l);

        Map<List<Long>, Integer> map = new HashMap<>();
        map.put(l, 20);

        List<Long> l2 = List.of(1l, -4l, 8l, -2l);

        l2.hashCode();

        System.out.println(map.get(l2));
    }

    @Override
    public boolean solve() throws Exception {
        super.solve();
        this.changes = this.secretNumbers.stream().map(this::calculateChanges).toList();
        this.cache = new ArrayList<>();
        
        // Pre calculate sequence by banana
        for (int buyerIndex = 0; buyerIndex < changes.size(); buyerIndex++) {
            LinkedList<Long> buyerChanges = changes.get(buyerIndex);
            Map<List<Long>, Integer> buyerCache = new HashMap<>();

            for (int i = 3; i < buyerChanges.size(); i++) {
                List<Long> sequence = List.of(
                        buyerChanges.get(i - 3),
                        buyerChanges.get(i - 2),
                        buyerChanges.get(i - 1),
                        buyerChanges.get(i));
                int banana = (int) (secretNumbers.get(buyerIndex).get(i + 1) % 10);

                buyerCache.put(sequence, banana);
            }

            cache.add(buyerCache);
        }
        
        // For each buyer changes
        for (int buyerChangeId = 0; buyerChangeId < changes.size(); buyerChangeId++) {
            LinkedList<Long> buyerChanges = changes.get(buyerChangeId);

            // Browse 4 changes sequences
            for (int i = 3; i < buyerChanges.size(); i++) {
                long sequenceBananas = 0;
                List<Long> sequence = List.of(
                        buyerChanges.get(i - 3),
                        buyerChanges.get(i - 2),
                        buyerChanges.get(i - 1),
                        buyerChanges.get(i));

                int n = this.cache.get(buyerChangeId).get(sequence);

                // For each other buyer changes
                for (int buyerChangesToTestId = 0; buyerChangesToTestId < changes.size(); buyerChangesToTestId++) {
                    LinkedList<Long> buyerChangesToTest = changes.get(buyerChangesToTestId);

                    int changeValue = cache.get(buyerChangesToTestId).get(sequence);
                    sequenceBananas += changeValue;

                    // Search same 4 changes sequence
                    // for (int sellIndex = 3; sellIndex < buyerChangesToTest.size(); sellIndex++) {
                    // List<Long> sequenceToTest = List.of(
                    // buyerChangesToTest.get(sellIndex - 3),
                    // buyerChangesToTest.get(sellIndex - 2),
                    // buyerChangesToTest.get(sellIndex - 1),
                    // buyerChangesToTest.get(sellIndex));
                    //
                    // if (ListUtils.isSame(sequence, sequenceToTest)) {
                    // Sell !
                    // sequenceBananas += secretNumbers.get(buyerChangesToTestId).get(sellIndex + 1)
                    // % 10;
                    // break;
                    // }
                    // }
                }

                if (sequenceBananas > maxBananas) {
                    this.maxBananas = sequenceBananas;
                }
            }
        }

        return true;
    }

    @Override
    public String get() {
        return this.maxBananas + "";
    }

    private LinkedList<Long> calculateChanges(LinkedList<Long> secretSequence) {
        LinkedList<Long> changeSequence = new LinkedList<>();

        for (int i = 1; i < secretSequence.size(); i++) {
            changeSequence.add((secretSequence.get(i) % 10) - (secretSequence.get(i - 1) % 10));
        }

        return changeSequence;
    }

}
