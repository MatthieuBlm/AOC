package com.matthieu.aoc.model.year_2024;

import java.nio.ByteBuffer;
import java.util.List;

public class ComputerDay17 {

    private int registerA;
    private int registerB;
    private int registerC;

    private List<Integer> instructions;

    private int instructionPointer;

    public ComputerDay17(int registerA, int registerB, int registerC, List<Integer> instructions) {
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        this.instructions = instructions;
        this.instructionPointer = 0;
    }

    public Integer executeInstruction() {
        switch (getCurrentInstruction()) {
            case 0:
                adv();
                break;
            case 1:
                bxl();
                break;
            case 2:
                bst();
                break;
            case 3:
                jnz();
                break;
            case 4:
                bxc();
                break;
            case 5:
                return out();
            case 6:
                bdv();
                break;
            case 7:
                cdv();
                break;
        }

        return null;
    }

    public boolean hasHalts() {
        return instructionPointer > instructions.size() - 1;
    }

    private void adv() {
        registerA = (int) (registerA / Math.pow(2d, getComboOperand(getLitteralOperand())));
        instructionPointer += 2;
    }

    private void bxl() {
        registerB = xor(registerB, getLitteralOperand());
        instructionPointer += 2;
    }

    private void bst() {
        registerB = getComboOperand(getLitteralOperand()) % 8;
        instructionPointer += 2;
    }

    private void jnz() {
        if (registerA != 0) {
            this.instructionPointer = getLitteralOperand();
        } else {
            instructionPointer += 2;
        }
    }

    private void bxc() {
        registerB = xor(registerB, registerC);
        instructionPointer += 2;
    }

    private int out() {
        int value = getComboOperand(getLitteralOperand()) % 8;
        instructionPointer += 2;
        return value;
    }

    private void bdv() {
        registerB = (int) (registerA / Math.pow(2d, getComboOperand(getLitteralOperand())));
        instructionPointer += 2;
    }

    private void cdv() {
        registerC = (int) (registerA / Math.pow(2d, getComboOperand(getLitteralOperand())));
        instructionPointer += 2;
    }

    private int getCurrentInstruction() {
        return this.instructions.get(instructionPointer);
    }

    private int getLitteralOperand() {
        return this.instructions.get(instructionPointer + 1);
    }

    private int getComboOperand(int bit) {
        if (bit >= 0 && bit <= 3) {
            return bit;
        } else if (bit == 4) {
            return registerA;
        } else if (bit == 5) {
            return registerB;
        } else if (bit == 6) {
            return registerC;
        }

        throw new IllegalStateException("Recieved bit " + bit);
    }

    private byte[] toByte(int i) {
        return ByteBuffer.allocate(4).putInt(i).array();
    }

    private int xor(int a, int b) {
        byte[] result = new byte[4];
        byte[] aByte = toByte(a);
        byte[] bByte = toByte(b);

        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (aByte[i] ^ bByte[i]);
        }

        return ByteBuffer.wrap(result).getInt();
    }

    public void setInstructions(List<Integer> instructions) {
        this.instructions = instructions;
    }

    public void setInstructionPointer(int instructionPointer) {
        this.instructionPointer = instructionPointer;
    }

    public List<Integer> getInstructions() {
        return instructions;
    }

    public int getRegisterA() {
        return registerA;
    }

    public void setRegisterA(int registerA) {
        this.registerA = registerA;
    }

    public int getRegisterB() {
        return registerB;
    }

    public void setRegisterB(int registerB) {
        this.registerB = registerB;
    }

    public int getRegisterC() {
        return registerC;
    }

    public void setRegisterC(int registerC) {
        this.registerC = registerC;
    }

    public int getInstructionPointer() {
        return instructionPointer;
    }

}
