package com.matthieu.aoc.resolver.year_2024;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver22p1 implements Resolver {

    protected List<Long> initalValues;
    protected List<LinkedList<Long>> secretNumbers;
    protected long nth;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.initalValues = values.stream().map(Long::parseLong).toList();
        this.secretNumbers = new ArrayList<>();
        this.nth = 2000;
    }

    @Override
    public boolean solve() throws Exception {
        this.secretNumbers = this.initalValues.parallelStream().map(n -> nthNextSecret(n, nth)).toList();
        return true;
    }

    @Override
    public String get() {
        return this.secretNumbers.stream().map(LinkedList::getLast).mapToLong(Long::longValue).sum() + "";
    }

    protected static LinkedList<Long> nthNextSecret(Long secretNumber, long nth) {
        LinkedList<Long> sequence = new LinkedList<>();
        sequence.add(secretNumber);

        for (int i = 0; i < nth; i++) {
            sequence.add(nextSecret(sequence.getLast()));
        }

        return sequence;
    }

    protected static long nextSecret(long secretNumber) {
        long result = secretNumber * 64;
        result = xor(result, secretNumber);
        result = result % 16777216l;

        result = xor(result, (long) (result / 32d));
        result = result % 16777216l;

        result = xor(result, (result * 2048));
        result = result % 16777216l;

        return result;
    }

    private static long xor(long a, long b) {
        byte[] result = new byte[8];
        byte[] aByte = toByte(a);
        byte[] bByte = toByte(b);

        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (aByte[i] ^ bByte[i]);
        }

        return ByteBuffer.wrap(result).getLong();
    }

    private static byte[] toByte(long i) {
        return ByteBuffer.allocate(8).putLong(i).array();
    }
}
