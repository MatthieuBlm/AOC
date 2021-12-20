package com.matthieu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.matrix.Row;

public class MatrixTest {

	private List<String> values;
	private Matrix<String> matrix;
	
	/**
	 * Hello	World	!
	 * foo		bar		baz
	 * france	suisse	italy
	 * ###		000		@@@
	 */
	@Before
	public void initialize() {
		values = Arrays.asList("Hello,World,!", "foo,bar,baz", "france,suisse,italy", "###,000,@@@");
		matrix = new Matrix<>(values, s -> s, ","); 
	}
	
	@Test
	public void setGetTest() {
		assertEquals("Hello", matrix.get(0, 0));
		
		matrix.set(0, 0, "123");
		assertEquals("123", matrix.get(0, 0));
		
		matrix.set(2, 2, "890");
		assertEquals("890", matrix.get(2, 2));
		
		matrix.set(0, 1, "---");
		assertEquals("---", matrix.get(0, 1));
	}
	
	@Test
	public void getQuietly() {
		
		assertNull(matrix.getQuietly(3, 0));
		assertNull(matrix.getQuietly(1, 4));
		
		assertEquals("bar", matrix.getQuietly(1, 1));
	}
	
	@Test
	public void getMaxXTest() {
		assertEquals(2, matrix.getMaxX());
	}

	@Test
	public void getMaxYTest() {
		assertEquals(3, matrix.getMaxY());
	}
	
	@Test
	public void getWidthTest() {
		assertEquals(3, matrix.getWidth());
	}
	
	@Test
	public void getHeightTest() {
		assertEquals(4, matrix.getHeight());
	}
	
	@Test
	public void getRowTest() {
		Row<String> row = matrix.getRow(1);
		
		assertEquals(3, row.size());
		assertEquals("foo", row.get(0));
		assertEquals("bar", row.get(1));
		assertEquals("baz", row.get(2));
	}
	
	@Test
	public void getColumnTest() {
		Row<String> column = matrix.getColumn(2);
		
		assertEquals(4, column.size());
		assertEquals("!", column.get(0));
		assertEquals("baz", column.get(1));
		assertEquals("italy", column.get(2));
		assertEquals("@@@", column.get(3));
	}
	
	@Test
	public void get8NeightboursTest() {
		List<String> n = matrix.getNeightbours(1, 1);
		
		assertEquals(8, n.size());
		
		assertTrue(n.contains("Hello"));
		assertTrue(n.contains("World"));
		assertTrue(n.contains("!"));
		assertTrue(n.contains("foo"));
		assertTrue(n.contains("baz"));
		assertTrue(n.contains("france"));
		assertTrue(n.contains("suisse"));
		assertTrue(n.contains("italy"));
		
		assertFalse(n.contains("###"));
		assertFalse(n.contains("000"));
		assertFalse(n.contains("@@@"));
	}

	@Test
	public void get3NeightboursTest() {
		List<String> n = matrix.getNeightbours(2, 0);
		
		assertEquals(3, n.size());
		
		assertTrue(n.contains("World"));
		assertTrue(n.contains("baz"));
		assertTrue(n.contains("bar"));
		
		assertFalse(n.contains("Hello"));
		assertFalse(n.contains("france"));
		assertFalse(n.contains("italy"));
		assertFalse(n.contains("###"));
		assertFalse(n.contains("000"));
		assertFalse(n.contains("@@@"));
	}

	@Test
	public void get5NeightboursTest() {
		List<String> n = matrix.getNeightbours(2, 1);
		
		assertEquals(5, n.size());
		
		assertTrue(n.contains("World"));
		assertTrue(n.contains("!"));
		assertTrue(n.contains("bar"));
		assertTrue(n.contains("suisse"));
		assertTrue(n.contains("italy"));
		
		assertFalse(n.contains("Hello"));
		assertFalse(n.contains("france"));
		assertFalse(n.contains("###"));
		assertFalse(n.contains("000"));
		assertFalse(n.contains("@@@"));
	}

	@Test
	public void get4NeightboursCrossTest() {
		List<String> n = matrix.getNeightboursCross(1, 1);
		
		assertEquals(4, n.size());
		
		assertTrue(n.contains("World"));
		assertTrue(n.contains("baz"));
		assertTrue(n.contains("suisse"));
		assertTrue(n.contains("foo"));
		
		assertFalse(n.contains("!"));
		assertFalse(n.contains("italy"));
		assertFalse(n.contains("Hello"));
		assertFalse(n.contains("france"));
		assertFalse(n.contains("###"));
		assertFalse(n.contains("000"));
		assertFalse(n.contains("@@@"));
	}

	@Test
	public void get2NeightboursCrossTest() {
		List<String> n = matrix.getNeightboursCross(0, 3);
		
		assertEquals(2, n.size());
		
		assertTrue(n.contains("france"));
		assertTrue(n.contains("000"));
		
		assertFalse(n.contains("Hello"));
		assertFalse(n.contains("foo"));
		assertFalse(n.contains("suisse"));
		assertFalse(n.contains("!"));
		assertFalse(n.contains("italy"));
		assertFalse(n.contains("###"));
		assertFalse(n.contains("@@@"));
	}

	@Test
	public void get3NeightboursCrossTest() {
		List<String> n = matrix.getNeightboursCross(0, 2);
		
		assertEquals(3, n.size());
		
		assertTrue(n.contains("foo"));
		assertTrue(n.contains("suisse"));
		assertTrue(n.contains("###"));
		
		assertFalse(n.contains("000"));
		assertFalse(n.contains("france"));
		assertFalse(n.contains("Hello"));
		assertFalse(n.contains("!"));
		assertFalse(n.contains("italy"));
		assertFalse(n.contains("@@@"));
	}

	@Test
	public void forEachTest() {
		matrix.forEach((x, y, s) -> {
			assertEquals(matrix.get(x, y), s);
		});
	}
	
	@Test
	public void getSubMatrixTest() {
		Matrix<String> subMatrix = matrix.submatrix(1, 2, 0, 1);

		assertEquals(2, subMatrix.getWidth());
		assertEquals(2, subMatrix.getHeight());
		assertEquals("World", subMatrix.get(0, 0));
		assertEquals("!", subMatrix.get(1, 0));
		assertEquals("bar", subMatrix.get(0, 1));
		assertEquals("baz", subMatrix.get(1, 1));
	}
}
