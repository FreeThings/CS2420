package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
		intCmp = ((num1, num2) -> num2.compareTo(num1));
		emptyIntArr = new Integer[0];
		smallIntArr = new Integer[] {1, 3, 45, 16, 23, 36, 79};
		smallIntList = new ArrayList<>();
		emptyIntList = new ArrayList<>();
		smallIntList.add(smallIntArr);
		smallIntList.add(smallIntArr);
	}
	
	// Testing insertionSort --------------------------------------------------------------------------
	
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
	void testInsertionSortWithAlternateGeneric() {
		String[] arr = new String[] {"Birds", "Tigers", "Elephants", "bonobos", "apple"};
		Comparator<String> strCmp = (str1, str2) -> (str1.compareTo(str2));
		LargestNumberSolver.insertionSort(arr, strCmp);
		String[] expectedArr = new String[] {"Birds", "Elephants", "Tigers", "apple", "bonobos"};
		assertArrayEquals(expectedArr, arr);
	}
	
	@Test
	void testInsertionSortAllSameValues() {
		Integer[] sameValuesArr = new Integer[] {1, 1, 1, 1, 1};
		Integer[] expectedArr = sameValuesArr.clone();
		LargestNumberSolver.insertionSort(sameValuesArr, intCmp);
		assertArrayEquals(expectedArr, sameValuesArr);
	}

	// Testing findLargestNumber ----------------------------------------------------------------------
	
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
	void testFindLargestNumberSameNumbers() {
		Integer[] sameNumsArr = new Integer[] {1, 1, 1, 1};
		assertEquals(LargestNumberSolver.findLargestNumber(sameNumsArr), new BigInteger("1111"));
	}
	
	@Test
	void testFindLargestNumberZero() {
		Integer[] zerosArr = new Integer[] {0, 0, 0, 0, 0};
		assertEquals(LargestNumberSolver.findLargestNumber(zerosArr), new BigInteger("0"));
	}
	
	// Testing findLargestInt -------------------------------------------------------------------------
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
	void testFindLaregstIntZeros() {
		Integer[] zerosArr = new Integer[] {0, 0, 0, 0};
		assertEquals(0, LargestNumberSolver.findLargestInt(zerosArr));
	}
	
	// Testing findLargestLong ------------------------------------------------------------------------
	
	@Test
	void testFindLargestLongNormal() {
		long l = 794536323161L;
		assertEquals(l, LargestNumberSolver.findLargestLong(smallIntArr));
	}
	
	@Test
	void testFindLargestLongOutOfBounds() {
		Integer[] bigIntArr = new Integer[] {123, 456, 789, 123, 456, 987, 123, 543};
		assertThrows(OutOfRangeException.class,() -> LargestNumberSolver.findLargestLong(bigIntArr));
	}
	
	@Test
	void testFindLargestLongEmpty() {
		assertEquals(0, LargestNumberSolver.findLargestLong(emptyIntArr));

	}
	
	@Test
	void testFindLaregstLongZeros() {
		Integer[] zerosArr = new Integer[] {0, 0, 0, 0};
		assertEquals(0L, LargestNumberSolver.findLargestLong(zerosArr));
	}
	
	// Testing sum ------------------------------------------------------------------------------------
	
	@Test
	void testSumEmpty() {
		assertEquals(LargestNumberSolver.sum(emptyIntList), new BigInteger("0"));
	}
	
	@Test
	void testSumNormal() {
		BigInteger b = new BigInteger("1589072646322");
		assertEquals(LargestNumberSolver.sum(smallIntList), b);
	}
	
	// Testing findKthLargest -------------------------------------------------------------------------
	
	@Test
	void testKthLargestEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(emptyIntList, 0);});
	}
	
	@Test
	void testKthLargestNormal() {
		Integer[] i = new Integer[] {0, 3, 5};
		Integer[] j = new Integer[] {1, 2, 3, 4};
		Integer[] k = new Integer[] {9, 8, 7};
		emptyIntList.add(i);
		emptyIntList.add(j);
		emptyIntList.add(k);
		assertEquals(LargestNumberSolver.findKthLargest(emptyIntList, 0), j);
		assertEquals(LargestNumberSolver.findKthLargest(emptyIntList, 1), k);
		assertEquals(LargestNumberSolver.findKthLargest(emptyIntList, 2), i);
		assertEquals(0, LargestNumberSolver.findKthLargest(emptyIntList, 2)[0]);
	}
	
	// Testing readFile -------------------------------------------------------------------------------
	
	@Test
	void testReadEmptyFile() {
		assertEquals(LargestNumberSolver.readFile("src/assign04/emptyText.txt"), emptyIntList);
	}
	
	@Test
	void testReadNonEmptyFile() {
		List<Integer[]> expected = new ArrayList<>();
		expected.add(new Integer[] {67, 10, 45, 31, 61, 17, 59, 68, 93, 46, 52});
		
		assertArrayEquals(LargestNumberSolver.readFile("src/assign04/integers.txt").get(5), expected.get(0));
	}
	
}
