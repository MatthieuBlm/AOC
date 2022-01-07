package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2021.Base9Digit;
import com.matthieu.aoc.resolver.Resolver;

/**
 * Max number in base 10 : 22 876 792 454 960
 * @author matthieu
 *
 */
public class Resolver24p1 implements Resolver {

	private static final Logger logger = LoggerFactory.getLogger(Resolver24p1.class);
	
	//														1	2	3	4   4  5   5    3   6   6  7   7    2    1
	private static final int[] xIncrements = 	new int[] {10, 15, 14, 15, -8, 10,-16, -4, 11, -3, 12, -7, -15, -7};
	private static final int[] yIncrements = 	new int[] {2,  16,  9, 0,   1, 12,  6,  6,  3,  5,  9,  3,   2,  3};
	private static final int[] zDivider = 		new int[] {1,  1,   1, 1,  26,  1, 26, 26,  1, 26,  1, 26,  26, 26};
	private static final Object runningThreadCountLock = new Object();
	private static final int THREAD_POOL_SIZE = 12;
	private static final long THREAD_RANGE = 10_000_000_000l;
	
	protected boolean findMax;
	private long searchBound;
	private List<Base9Digit> results;
	private int runningThreadCount;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.findMax = true;
		this.results = new ArrayList<>();
		this.runningThreadCount = 0;
	}

	@Override
	public boolean solve() throws SolveException {
		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

		if(this.findMax) {
			this.searchBound = 22_876_792_454_960l;	// "99999999999999"
	//		this.searchBound = 22_434_439_123_183l;	// part 1 answer - 100_000_000
	//		this.searchBound = 22_434_339_123_183l;	// "98491959997994"  part 1 answer
		} else {
			this.searchBound = 0;
		}

		new Thread(() -> {
			while(results.isEmpty()) {
				
				if(takeThreadSlot()) {
					
					executorService.submit(() -> {
						UUID id = UUID.randomUUID();
						Base9Digit n = takeRange();
						
						logger.info("Searching thread {} started from {}", id, n);
						
						for (long r = 0; r <= THREAD_RANGE; r++) {
							
							if( n.get(0) -5   == n.get(13) &&
								n.get(1) + 1  == n.get(12) &&
								n.get(2) + 5  == n.get(7)  &&
								n.get(3) - 8  == n.get(4)  &&
								n.get(5) - 4  == n.get(6)  &&
								n.get(10) + 2 == n.get(11)) {
								addResult(n);
								break;
							}
							
							if(this.findMax)
								n.decr();
							else
								n.incr();
						}
						
						freeThreadSlot();
						
						logger.info("Searching thread {} terminated", id);
					});
				} else {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						logger.error("Can't pause current thread", e);
						
						Thread.currentThread().interrupt();
					}
				}
			}
			
			logger.info("Model number found : {}", results.stream().map(Base9Digit::toString).collect(Collectors.toList()));
			
		}).start();
		
		return true;
	}

	private int calculateZ(Base9Digit digits) {
		int z = 0;
		
		for (int i = 0; i < 14; i++) {
			if(i >= 11 && z > 17576)
				break;
			
			z = doCycle(digits.get(i), xIncrements[i], yIncrements[i], zDivider[i], z);
		}
		
		return z;
	}

	@Override
	public String get() {
		return "Work in progress. See stdout to get result.";
	}
	
	public synchronized Base9Digit takeRange() {
		char[] base9StringWith0 = Long.toString(searchBound, 9).toCharArray();
		
		if(base9StringWith0.length < 14) {
			char[] fullLengthString = new char[14];
			int missing0 = fullLengthString.length - base9StringWith0.length;
			
			for (int i = 0; i < missing0; i++) {
				fullLengthString[i] = '0';
			}
			
			for (int i = missing0; i < fullLengthString.length; i++) {
				fullLengthString[i] = base9StringWith0[i - missing0];
			}
			
			base9StringWith0 = fullLengthString;
		}
		
		StringBuilder base9StringWithout0 = new StringBuilder();
		
		for (char c : base9StringWith0) {
			base9StringWithout0.append(Character.getNumericValue(c) + 1);
		}
		
		if(this.findMax)
			searchBound -= THREAD_RANGE;
		else
			searchBound += THREAD_RANGE;
		
		return new Base9Digit(base9StringWithout0.toString());
	}
	
	public synchronized void addResult(Base9Digit digit) {
		this.results.add(digit);
		
		logger.info("Found {} !", digit);
	}
	
	public boolean takeThreadSlot() {
		synchronized (runningThreadCountLock) {
			if(runningThreadCount < THREAD_POOL_SIZE) {
				runningThreadCount++;
				return true;
			}
			
			return false;
		}
	}
	
	public void freeThreadSlot() {
		synchronized (runningThreadCountLock) {
			runningThreadCount--;
		}
	}
	
	public int getFreeThreadSlot() {
		synchronized (runningThreadCountLock) {
			return THREAD_POOL_SIZE - this.runningThreadCount;
		}
	}
	
	public int doCycle(int input, int xIncrement, int yIncrement, int zDivider, int z) {
		if((z % 26) + xIncrement == input)
			return z / zDivider;
		else
			return z / zDivider * 26 + input + yIncrement;
	}
	
	// Div 1 / push
	public int doCycleA(int input, int xIncrement, int yIncrement, int z) {
		if((z % 26) + xIncrement == input)
			return z;
		else
			return z * 26 + input + yIncrement;		// <--
	}
	
	// Div 26 / pop
	public int doCycleB(int input, int xIncrement, int yIncrement, int z) {
		if((z % 26) + xIncrement == input)
			return z / 26;							// <--
		else
			return z + input + yIncrement;
	}

	public static void main(String[] args) {
//		System.out.println(Long.parseLong("98491959997994", 9));
		System.out.println(Long.parseLong("87380848886883", 9));	// 22434339123183
		Resolver24p1 r = new Resolver24p1();
		int z = 0;
		
		// Part 1
		z = r.doCycle(9, 10, 2, 1, z);		// 1
		z = r.doCycle(8, 15, 16, 1, z);		// 2
		z = r.doCycle(4, 14, 9, 1, z);		// 3
		z = r.doCycle(9, 15, 0, 1, z);		// 4
		z = r.doCycle(1, -8, 1, 26, z);		// 4
		z = r.doCycle(9, 10, 12, 1, z);		// 5
		z = r.doCycle(5, -16, 6, 26, z);	// 5
		z = r.doCycle(9, -4, 6, 26, z);		// 3
		z = r.doCycle(9, 11, 3, 1, z);		// 6
		z = r.doCycle(9, -3, 5, 26, z);		// 6
		z = r.doCycle(7, 12, 9, 1, z);		// 7
		z = r.doCycle(9, -7, 3, 26, z);		// 7
		z = r.doCycle(9, -15, 2, 26, z);	// 2
		z = r.doCycle(4, -7, 3, 26, z);		// 1

		System.out.println(z);
		z = 0;
		
		// Part 2
		z = r.doCycle(6, 10, 2, 1, z);		// 1
		z = r.doCycle(1, 15, 16, 1, z);		// 2
		z = r.doCycle(1, 14, 9, 1, z);		// 3
		z = r.doCycle(9, 15, 0, 1, z);		// 4
		z = r.doCycle(1, -8, 1, 26, z);		// 4
		z = r.doCycle(5, 10, 12, 1, z);		// 5
		z = r.doCycle(1, -16, 6, 26, z);	// 5
		z = r.doCycle(6, -4, 6, 26, z);		// 3
		z = r.doCycle(1, 11, 3, 1, z);		// 6
		z = r.doCycle(1, -3, 5, 26, z);		// 6
		z = r.doCycle(1, 12, 9, 1, z);		// 7
		z = r.doCycle(3, -7, 3, 26, z);		// 7
		z = r.doCycle(2, -15, 2, 26, z);	// 2
		z = r.doCycle(1, -7, 3, 26, z);		// 1
		
		System.out.println(z);
	}
	
	public static int[] doCycleDetail(int w, int xIncr, int yIncr, int zDiv, int z) {
		int x = z;
		x %= 26;
		z /= zDiv;	// 1 or 26
		x += xIncr;	// variable
		
		x = x != w ? 1 : 0;
		
		int y = 25;
		y *= x;
		y++;
		
		z *= y;
		
		y = w;
		y += yIncr;	// variable
		y *= x;
		z += y;
		
		return new int[] {w, x, y, z};
	}
	
	public static int doCycleVerbose(int input, int z) {
		
		int remainder = (z % 26) + 10; // "+10" or whatever
		int q = z / 1; 		// Or 26
		
		if(remainder != input)
			return q * 26 + input + 2; 	// "+2" or Whatever
		else
			return q;
	}
	
}
