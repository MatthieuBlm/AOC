package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.year_2024.ComputerDay17;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver17p1 implements Resolver {

    protected ComputerDay17 computer;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        int registerA = Extractor.extractNumbers(values.get(0)).get(0).intValue();
        int registerB = Extractor.extractNumbers(values.get(1)).get(0).intValue();
        int registerC = Extractor.extractNumbers(values.get(2)).get(0).intValue();

        List<Integer> program = Extractor.extractNumbers(values.get(4)).stream().map(Long::intValue).toList();

        this.computer = new ComputerDay17(registerA, registerB, registerC, program);
    }

    @Override
    public boolean solve() throws Exception {
        while (!computer.hasHalts()) {
            Integer out = computer.executeInstruction();

            if (out != null) {
                System.out.print(out + ",");
            }
        }

        System.out.println();
        return true;
    }

    @Override
    public String get() {
        return null;
    }

}
