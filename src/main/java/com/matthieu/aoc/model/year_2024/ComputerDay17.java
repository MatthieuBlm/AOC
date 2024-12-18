package com.matthieu.aoc.model.year_2024;

import java.nio.ByteBuffer;
import java.util.List;

public class ComputerDay17 {

    private long registerA;
    private long registerB;
    private long registerC;

    private List<Integer> instructions;

    private int instructionPointer;

    public ComputerDay17(long registerA, long registerB, long registerC, List<Integer> instructions) {
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
        int value = (int) getComboOperand(getLitteralOperand()) % 8;
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

    private long getComboOperand(int bit) {
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

    private static byte[] toByte(long i) {
        return ByteBuffer.allocate(8).putLong(i).array();
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
    
    
    public static void main(String[] args) {
		long a = 0, b = 0, c = 0, out = 0;
		
		a = 3095483647l;
		
		while(a != 0) {
			b = a % 8;
			b = xor(b, 1);
			c = a / (1 << b);
			b = xor(b, c);
			a = a / 8;
			b = xor(b, 4);
			out = b % 8;
			System.out.print(out + ",");
		}
		
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

    public long getRegisterA() {
        return registerA;
    }

    public void setRegisterA(long registerA) {
        this.registerA = registerA;
    }

    public long getRegisterB() {
        return registerB;
    }

    public void setRegisterB(long registerB) {
        this.registerB = registerB;
    }

    public long getRegisterC() {
        return registerC;
    }

    public void setRegisterC(long registerC) {
        this.registerC = registerC;
    }

    public int getInstructionPointer() {
        return instructionPointer;
    }

}
