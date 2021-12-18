package com.matthieu.aoc_2021;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import com.matthieu.aoc.model.year_2021.SnailfishNumber;

public class SnailfishTest {

	@Test
	public void singleExplodeTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[[9,8],1],2],3],4]");
		
		assertTrue(n.explodeIfAny());
		
		assertEquals("[[[[0,9],2],3],4]", n.toString());
	}
	
	@Test
	public void singleExplodeTestB() {
		SnailfishNumber n = new SnailfishNumber("[7,[6,[5,[4,[3,2]]]]]");
		
		assertTrue(n.explodeIfAny());
		
		assertEquals("[7,[6,[5,[7,0]]]]", n.toString());
	}
	
	@Test
	public void singleExplodeTestC() {
		SnailfishNumber n = new SnailfishNumber("[[6,[5,[4,[3,2]]]],1]");
		
		assertTrue(n.explodeIfAny());
		
		assertEquals("[[6,[5,[7,0]]],3]", n.toString());
	}
	
	@Test
	public void singleExplodeTestD() {
		SnailfishNumber n = new SnailfishNumber("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
		
		assertTrue(n.explodeIfAny());
		
		assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", n.toString());
	}

	@Test
	public void singleExplodeTestE() {
		SnailfishNumber n = new SnailfishNumber("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
		
		assertTrue(n.explodeIfAny());
		
		assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", n.toString());
	}
	
	@Test
	public void singleSplitTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[0,7],4],[15,[0,13]]],[1,1]]");
		
		assertTrue(n.splitIfAny());
		
		assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", n.toString());
	}

	@Test
	public void singleSplitTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]");
		
		assertTrue(n.splitIfAny());
		
		assertEquals("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]", n.toString());
	}
	
	@Test
	public void takeNumberTestA() {
		SnailfishNumber n = new SnailfishNumber("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
		
		String numberTaken = n.takeNumber(7);
		
		assertEquals("[8,0]", numberTaken);
		assertEquals("[[3,[2,]],[9,[5,[4,[3,2]]]]]", n.toString());
	}

	@Test
	public void takeNumberTestB() {
		SnailfishNumber n = new SnailfishNumber("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
		
		String numberTaken = n.takeNumber(4);
		
		assertEquals("[2,[8,0]]", numberTaken);
		assertEquals("[[3,],[9,[5,[4,[3,2]]]]]", n.toString());
	}
	
	@Test
	public void addTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]");
		
		n.add("[1,1]");
		
		assertEquals("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]", n.toString());
	}

	@Test
	public void addTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]");
		
		n.add("[1,[4,4]]");
		
		assertEquals("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,[4,4]]]", n.toString());
	}
	
	@Test
	public void getNumberTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		assertEquals(4, n.getNumber(4));
	}

	@Test
	public void getNumberTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		assertEquals(3, n.getNumber(6));
	}

	@Test
	public void getNumberTestC() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		assertNull(n.getNumber(3));
	}

	@Test
	public void getNumberTestD() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		assertNull(n.getNumber(5));
	}

	@Test
	public void getNumberTestE() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,15],2],1]");
		
		assertEquals(15, n.getNumber(6));
	}

	@Test
	public void getNumberTestF() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,15],2],1]");
		
		assertEquals(15, n.getNumber(7));
	}

	@Test
	public void getNumberTestG() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,15],2],1]");
		
		assertNull(n.getNumber(8));
	}
	
	@Test
	public void addToLeftTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");

		n.addToTheLeft(6, 1);
		
		assertEquals("[[[[5,3],2],1]", n.toString());
	}

	@Test
	public void addToLeftTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		n.addToTheLeft(4, 1);
		
		assertEquals("[[[[4,3],2],1]", n.toString());
	}

	@Test
	public void addToRightTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		n.addToTheRight(4, 1);
		
		assertEquals("[[[[4,4],2],1]", n.toString());
	}
	
	@Test
	public void addToRightTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		n.addToTheRight(6, 1);
		
		assertEquals("[[[[4,3],3],1]", n.toString());
	}

	@Test
	public void addToRightTestC() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		n.addToTheRight(12, 1);
		
		assertEquals("[[[[4,3],2],1]", n.toString());
	}
	
	@Test
	public void setNumberTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");

		n.setNumber(6, 9);
		
		assertEquals("[[[[4,9],2],1]", n.toString());
	}

	@Test
	public void setNumberTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],2],1]");
		
		n.setNumber(4, 10);
		
		assertEquals("[[[[10,3],2],1]", n.toString());
	}

	@Test
	public void setNumberTestC() {
		SnailfishNumber n = new SnailfishNumber("[[[[10,3],2],1]");
		
		n.setNumber(4, 9);
		
		assertEquals("[[[[9,3],2],1]", n.toString());
	}
	
	@Test
	public void sequentialTestA() {
		SnailfishNumber n = new SnailfishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]");
		String toAdd = "[1,1]";
		
		n.add(toAdd);

		assertEquals("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]", n.toString());
		assertTrue(n.explodeIfAny());
		assertEquals("[[[[0,7],4],[7,[[8,4],9]]],[1,1]]", n.toString());
		assertTrue(n.explodeIfAny());
		assertEquals("[[[[0,7],4],[15,[0,13]]],[1,1]]", n.toString());
		assertTrue(n.splitIfAny());
		assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", n.toString());
		assertTrue(n.splitIfAny());
		assertEquals("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]", n.toString());
		assertTrue(n.explodeIfAny());
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", n.toString());
		
		assertFalse(n.explodeIfAny());
		assertFalse(n.splitIfAny());
		
	}
	
	@Test
	public void sequentialTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]");
		
		n.add("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]");
		n.reduce();
		
		assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", n.toString());
		
		n.add("[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]");
		n.reduce();
		
		assertEquals("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]", n.toString());
		
		n.add("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]");
		n.reduce();
		
		assertEquals("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]", n.toString());
		
		n.add("[7,[5,[[3,8],[1,4]]]]");
		n.reduce();
		
		assertEquals("[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]", n.toString());
		
		n.add("[[2,[2,2]],[8,[8,1]]]");
		n.reduce();
		
		assertEquals("[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]", n.toString());
		
		n.add("[2,9]");
		n.reduce();
		
		assertEquals("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]", n.toString());
		
		n.add("[1,[[[9,3],9],[[9,0],[0,7]]]]");
		n.reduce();
		
		assertEquals("[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]", n.toString());
		
		n.add("[[[5,[7,4]],7],1]");
		n.reduce();
		
		assertEquals("[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]", n.toString());
		
		n.add("[[[[4,2],2],6],[8,7]]");
		n.reduce();
		
		assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", n.toString());
	}
	
	@Test
	public void magnitudeTestA() {
		SnailfishNumber n = new SnailfishNumber("[[1,2],[[3,4],5]]");
		
		assertEquals(143, n.getMagnitude());
	}
	
	@Test
	public void magnitudeTestB() {
		SnailfishNumber n = new SnailfishNumber("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]");
		
		assertEquals(1384, n.getMagnitude());
	}
	
	@Test
	public void magnitudeTestC() {
		SnailfishNumber n = new SnailfishNumber("[[[[1,1],[2,2]],[3,3]],[4,4]]");
		
		assertEquals(445, n.getMagnitude());
	}
	
	@Test
	public void magnitudeTestD() {
		SnailfishNumber n = new SnailfishNumber("[[[[3,0],[5,3]],[4,4]],[5,5]]");
		
		assertEquals(791, n.getMagnitude());
	}
	
	@Test
	public void magnitudeTestE() {
		SnailfishNumber n = new SnailfishNumber("[[[[5,0],[7,4]],[5,5]],[6,6]]");
		
		assertEquals(1137, n.getMagnitude());
	}
	
	@Test
	public void magnitudeTestF() {
		SnailfishNumber n = new SnailfishNumber("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]");
		
		assertEquals(3488, n.getMagnitude());
	}
}
