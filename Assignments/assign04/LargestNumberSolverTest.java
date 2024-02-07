package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LargestNumberSolverTest {

	private Comparator<Integer> intCmp;
	private Integer[] emptyIntArr;
	private Integer[] smallIntArr;
	private List<Integer[]> emptyIntList;
	private List<Integer[]> smallIntList;


	@BeforeEach
	void setUp() {
		intCmp = ((num1, num2) -> num1.compareTo(num2));
		emptyIntArr = new Integer[0];
		smallIntArr = new Integer[] {1, 3, 45, 16, 23, 36, 79};
		smallIntList = new ArrayList<>();
		emptyIntList = new ArrayList<>();
		smallIntList.add(smallIntArr);
		smallIntList.add(smallIntArr);
	}
	
	@Test
	void testInsertionSortEmpty() {
		Integer[] emptycopyArr = new Integer[0];
		LargestNumberSolver.insertionSort(emptyIntArr, intCmp);
		assertArrayEquals(emptycopyArr, emptyIntArr);
		
	}
	
	@Test
	void testInsertionSortNormalCase() {
		Integer[] copyArr = new Integer[] {79, 45, 36, 23, 16, 3, 1}; 
		LargestNumberSolver.insertionSort(smallIntArr, intCmp);
		assertArrayEquals(copyArr, smallIntArr);
	}

	@Test
	void testFindLargestNumberNormal() {
		BigInteger i = new BigInteger("794536323161");
		assertEquals(i, LargestNumberSolver.findLargestNumber(smallIntArr));

	}
	
	@Test
	void testFindLargestNumberEmpty() {
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(emptyIntArr));
		
	}
	
	@Test
	void testFindLargestIntOutOfBounds() {
		assertThrows(OutOfRangeException.class,() -> LargestNumberSolver.findLargestInt(smallIntArr));
		
	}
	
	@Test
	void testFindLargestIntSmallInBounds() {
		Integer[] smaller = new Integer[] {1, 23, 5};
		assertEquals(5231, LargestNumberSolver.findLargestInt(smaller));
	}
	
	@Test
	void testFindLargestIntEmpty() {
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(emptyIntArr));
	}
	
	@Test
	void testFindLargestLongNormal() {
		//May need to be changed later
		long l = 794536323161L;
		assertEquals(l, LargestNumberSolver.findLargestLong(smallIntArr));
	}
	
	@Test
	void testFindLargestLongEmpty() {
		assertEquals(0, LargestNumberSolver.findLargestLong(emptyIntArr));

	}
	
	@Test
	void testSumEmpty() {
		assertEquals(LargestNumberSolver.sum(emptyIntList), 0);
	}
	
	@Test
	void testSumNormal() {
		BigInteger b = new BigInteger("1589072646322");
		assertEquals(LargestNumberSolver.sum(smallIntList), b);
	}
	
	@Test
	void testKthLargestEmpty() {
		assertEquals(LargestNumberSolver.findKthLargest(emptyIntList, 0), 0);
	}
	
	@Test
	void testKthLargestNormal() {
		Integer[] i = new Integer[] {0, 3, 5};
		smallIntList.add(i);
		assertEquals(LargestNumberSolver.findKthLargest(smallIntList, 2), i);
	}
	
	@Test
	void testReadEmptyFile() {
		assertEquals(LargestNumberSolver.readFile("emptyText.txt"), emptyIntList);
	}
	
	@Test
	void testReadNonEmptyFile() {
		assertEquals(LargestNumberSolver.readFile("smallText.txt"), "");
	}
	
}