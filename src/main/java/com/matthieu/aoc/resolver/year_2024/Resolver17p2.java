package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.matthieu.aoc.controller.DayController;
import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.year_2024.ComputerDay17;

public class Resolver17p2 extends Resolver17p1 {

    private static final Logger logger = LoggerFactory.getLogger(DayController.class);

    private List<Long> goodRegisterValues;
    private ThreadPoolExecutor executor;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);
        this.computer.setRegisterA(0);
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(12);
        this.goodRegisterValues = new ArrayList<>();
    }

    @Override
    public boolean solve() throws Exception {
        int batchSize = 4_000_000;
        long maxValueBrowsed = 3095483647l; // Already check value before this one
        List<Future<List<Long>>> futures = new ArrayList<>();

        while (goodRegisterValues.isEmpty()) {
            while (executor.getQueue().size() < 12) {
                final long fromValue = maxValueBrowsed;
                final long toValue = maxValueBrowsed + batchSize;
                futures.add(executor.submit(() -> search(fromValue, toValue)));
                maxValueBrowsed = toValue;
            }

            goodRegisterValues = futures.stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return null;
                }
            }).flatMap(List::stream).toList();
        }

        return true;
    }

    @Override
    public String get() {
        return goodRegisterValues.stream().sorted().findFirst().get() + "";
    }

    private List<Long> search(long fromValue, long toValue) {
        List<Integer> producedProgram = new ArrayList<>();
        List<Long> goodRegisterValue = new ArrayList<>();
        ComputerDay17 clone = new ComputerDay17(computer.getRegisterA(), computer.getRegisterB(), computer.getRegisterC(),
                computer.getInstructions());
        
        logger.info("Start searching between {} and {}", fromValue, toValue);

        int offset = 0;

        for (long i = fromValue; i < toValue; i++) {
            producedProgram.clear();
            initializeClone(clone);
            clone.setRegisterA(fromValue + offset);

            while (!clone.hasHalts()) {
                Integer out = clone.executeInstruction();

                if (out != null) {
                    producedProgram.add(out);
                }
            }

            if (areSame(producedProgram, computer.getInstructions())) {
                goodRegisterValue.add(clone.getRegisterA());
                logger.info("Found value ! {}", fromValue + offset);
            }

            offset++;
        }

        return goodRegisterValue;
    }

    private void initializeClone(ComputerDay17 clone) {
        clone.setInstructionPointer(0);
        clone.setRegisterA(computer.getRegisterA());
        clone.setRegisterB(computer.getRegisterB());
        clone.setRegisterC(computer.getRegisterB());
    }

    private boolean areSame(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) {
                return false;
            }
        }

        return true;
    }
}
