package assign03;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * For testing the SimplePriorityQueue class.
 * 
 * Class CS: 2420
 * Assignment 3: SimplePriorityQueue
 * 
 * @author Christopher Hunter and Aiden de Boer
 * @version Feb. 1, 2024
 */
class SimplePriorityQueueTesting {

	private SimplePriorityQueue<String> emptyStringQueue;
	private SimplePriorityQueue<String> smallStringQueue;
	
	private SimplePriorityQueue<Integer> emptyIntegerQueue;
	private SimplePriorityQueue<Integer> smallIntegerQueue;

	
	@BeforeEach
	void setup() throws Exception {
		emptyStringQueue = new SimplePriorityQueue<>();
		smallStringQueue = new SimplePriorityQueue<>();
		
		emptyIntegerQueue = new SimplePriorityQueue<>();
		smallIntegerQueue = new SimplePriorityQueue<>();
		
		smallStringQueue.insert("Aiden");
		smallStringQueue.insert("Chris");
		smallStringQueue.insert("Hello");
		smallStringQueue.insert("World");
		smallStringQueue.insert("CS2420");
		
		smallIntegerQueue.insert(1);
		smallIntegerQueue.insert(5);
		smallIntegerQueue.insert(3);
		smallIntegerQueue.insert(23);
		smallIntegerQueue.insert(9);

	}
	
	// Testing Comparable Interface Implementation ----------------------------------------------------
	@Test
	void testSizeOnNotEmptyQueue() {
		assertEquals(smallStringQueue.size(), 5);
	}
	
	@Test
	void testIsEmptyOnEmptyStringQueue(){
		assertTrue(emptyStringQueue.isEmpty());
	}
	
	@Test
	void testIsEmptyOnFilledStringQueue() {
		emptyStringQueue.insert("Hello");
		assertFalse(emptyStringQueue.isEmpty());
	}
	
	@Test
	void testInsertOnEmptyStringQueue() {
		assertDoesNotThrow(() -> emptyStringQueue.insert("Hello"));
	}
	
	@Test
	void testFindMaxOnSingleElementStringQueue() {
		emptyStringQueue.insert("Hello");
		assertEquals("Hello", emptyStringQueue.findMax());
	}
	
	@Test
	void testFindMaxOnTwoElementStringQueue() {
		emptyStringQueue.insert("Hello");
		emptyStringQueue.insert("Goodbye");
		assertEquals("Hello", emptyStringQueue.findMax());
	}
	
	@Test 
	void testSizeOnEmptyStringQueue(){
		assertEquals(0, emptyStringQueue.size());
	}
	
	@Test
	void testContainsOnEmptyStringQueue() {
		assertFalse(emptyStringQueue.contains("Hello World"));
	}
	
	@Test
	void testContainsOnSmallStringQueue() {
		assertTrue(smallStringQueue.contains("Hello"));
	}
	
	@Test
	void testContainsOnEmptyIntegerQueue() {
		assertFalse(emptyIntegerQueue.contains(1));
	}
	
	@Test
	void testContainsOnSmallIntegerQueue() {
		assertTrue(smallIntegerQueue.contains(5));
	}
	
	@Test
	void testInsertCollectionOnEmptyStringQueue() {
		List<String> listOfStrings = List.of("a", "b", "c", "d", "e", "f");
		emptyStringQueue.insertAll(listOfStrings);
		assertTrue(emptyStringQueue.contains("a"));
		assertTrue(emptyStringQueue.contains("b"));
		assertTrue(emptyStringQueue.contains("c"));
		assertTrue(emptyStringQueue.contains("d"));
		assertTrue(emptyStringQueue.contains("e"));
		assertTrue(emptyStringQueue.contains("f"));
	}

	@Test
	void testOnEmptyStringQueueOutOfOrderCollection() {
		List<String> listOfStrings = List.of("c", "a", "d", "b", "a", "e");
		emptyStringQueue.insertAll(listOfStrings);
		assertEquals(emptyStringQueue.deleteMax(), "e");
		assertEquals(emptyStringQueue.deleteMax(), "d");
		assertEquals(emptyStringQueue.deleteMax(), "c");
		assertEquals(emptyStringQueue.deleteMax(), "b");
		assertEquals(emptyStringQueue.deleteMax(), "a");
		assertEquals(emptyStringQueue.deleteMax(), "a");
	}
	
	@Test
	void testOnEmptyIntegerQueueOutOfOrderCollection() {
		List<Integer> listOfInts = List.of(100, -2, 0, 0, 0, 5, -100, 22, 43);
		emptyIntegerQueue.insertAll(listOfInts);
		assertEquals(emptyIntegerQueue.deleteMax(), 100);
		assertEquals(emptyIntegerQueue.deleteMax(), 43);
		assertEquals(emptyIntegerQueue.deleteMax(), 22);
		assertEquals(emptyIntegerQueue.deleteMax(), 5);
		assertEquals(emptyIntegerQueue.deleteMax(), 0);
		assertEquals(emptyIntegerQueue.deleteMax(), 0);
		assertEquals(emptyIntegerQueue.deleteMax(), 0);
		assertEquals(emptyIntegerQueue.deleteMax(), -2);
		assertEquals(emptyIntegerQueue.deleteMax(), -100);

	}
	
	@Test
	void testOnEmptyIntegerQueueIncreaseBackingArray() {
		List<Integer> listOfInts = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		emptyIntegerQueue.insertAll(listOfInts);
		assertEquals(emptyIntegerQueue.size(), 15);
	}
	
	@Test
	void testDeleteMaxOnFullStringQueue() {
		List<String> listOfStrings = List.of("a", "b", "c", "d", "e", "f");
		emptyStringQueue.insertAll(listOfStrings);
		assertEquals(emptyStringQueue.deleteMax(), "f");
		assertEquals(emptyStringQueue.deleteMax(), "e");
	}
	
	@Test
	void testIsEmptyOnNotEmpty() {
		assertFalse(smallStringQueue.isEmpty());
	}
	
	@Test
	void testClearEmptyQueue() {
		smallStringQueue.clear();
		assertEquals(smallStringQueue.size(), 0);
	}
	
	@Test
	void testFindMaxAllTheSame() {
		for(int i = 0; i < 40; i++) {
			emptyIntegerQueue.insert(0);
		}
		assertEquals(emptyIntegerQueue.findMax(), 0);
	}
	
	@Test
	void testDeleteMaxAllTheSame() {
		for(int i = 0; i < 40; i++) {
			emptyIntegerQueue.insert(0);
		}
		assertEquals(emptyIntegerQueue.deleteMax(), 0);
		assertEquals(emptyIntegerQueue.size(), 39);
	}
	
	@Test
	void testThrowsWhenMaxDoesntExistEmptyQueue() {
		assertThrows(NoSuchElementException.class,() -> emptyStringQueue.findMax());
	}
	
	@Test
	void testVeryBigPriorityQueueDoesNotThrow() {
		for (int i = 0; i < 100000; i++)
			assertDoesNotThrow(() -> emptyIntegerQueue.insert(0));
	}
	
	@Test
	void testThrowsWhenNoMoreElementsToDelete() {
		assertThrows(NoSuchElementException.class,() -> emptyStringQueue.deleteMax());

	}
	
	// Testing Comparator Interface Implementation ----------------------------------------------------
	
	@Test
	void testReverseOrderingIntInsert() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		smallIntegerQueue.insert(3);
		smallIntegerQueue.insert(5);
		smallIntegerQueue.insert(1);
		
		assertEquals(smallIntegerQueue.findMax(), 1);
	}
	
	@Test
	void testReverseOrderingIntDelete() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		smallIntegerQueue.insert(3);
		smallIntegerQueue.insert(4);
		smallIntegerQueue.insert(5);
		smallIntegerQueue.insert(1);
		smallIntegerQueue.deleteMax();
		
		assertEquals(smallIntegerQueue.findMax(), 3);
	}
	
	@Test
	void testReverseOrderingIntIsNotEmpty() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		smallIntegerQueue.insert(3);
		
		assertFalse(smallIntegerQueue.isEmpty());
	}
	
	@Test
	void testReverseOrderingIntClear() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		smallIntegerQueue.insert(3);
		smallIntegerQueue.clear();
		assertEquals(smallIntegerQueue.size(), 0);
	}
	
	@Test
	void testReverseOrderingIntContains() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		smallIntegerQueue.insert(3);
		smallIntegerQueue.insert(0);
		smallIntegerQueue.insert(-2);
		assertTrue(smallIntegerQueue.contains(-2) && smallIntegerQueue.contains(0) && smallIntegerQueue.contains(3));
		assertFalse(smallIntegerQueue.contains(1));
	}
	
	@Test
	void testReverseOrderingStringInsert() {
		smallStringQueue = new SimplePriorityQueue<String>((str1, str2) -> str2.compareTo(str1));
		smallStringQueue.insert("I LOVE CS 2420");
		smallStringQueue.insert("Aiden");
		smallStringQueue.insert("Chris");
		smallStringQueue.insert("Hello");
		smallStringQueue.insert("World");
		smallStringQueue.insert("CS2420");
		assertEquals(smallStringQueue.deleteMax(), "Aiden");
		assertEquals(smallStringQueue.size(), 5);
	}

	@Test
	void testInsertsNewValueInSameValue() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		for (int i = 0; i < 40; i++)
			smallIntegerQueue.insert(0);
		smallIntegerQueue.insert(1);
		assertEquals(smallIntegerQueue.deleteMax(), 0);
		assertEquals(smallIntegerQueue.size(), 40);
	}
}
