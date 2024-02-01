package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimplePriorityQueueTesting {

	private SimplePriorityQueue<String> emptyStringQueue;
	private SimplePriorityQueue<String> smallStringQueue;
	private SimplePriorityQueue<String> largeStringQueue;
	private SimplePriorityQueue<String> veryLargeStringQueue;
	
	private SimplePriorityQueue<Integer> emptyIntegerQueue;
	private SimplePriorityQueue<Integer> smallIntegerQueue;
	private SimplePriorityQueue<Integer> largeIntegerQueue;
	private SimplePriorityQueue<Integer> veryLargeIntegerQueue;

	
	@BeforeEach
	void setup() throws Exception {
		emptyStringQueue = new SimplePriorityQueue<>();
		smallStringQueue = new SimplePriorityQueue<>();
		largeStringQueue = new SimplePriorityQueue<>();
		veryLargeStringQueue = new SimplePriorityQueue<>();
		
		emptyIntegerQueue = new SimplePriorityQueue<>();
		smallIntegerQueue = new SimplePriorityQueue<>();
		largeIntegerQueue = new SimplePriorityQueue<>();
		veryLargeIntegerQueue = new SimplePriorityQueue<>();
		
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
		assertEquals("Goodbye", emptyStringQueue.findMax());
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
	
	// Need to make sure the contains method works before testing this.
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
	void testDeleteMaxOnFullStringQueue() {
		List<String> listOfStrings = List.of("a", "b", "c", "d", "e", "f");
		emptyStringQueue.insertAll(listOfStrings);
		assertEquals(emptyStringQueue.deleteMax(), "a");
		assertEquals(emptyStringQueue.deleteMax(), "b");
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
	// Testing Comparator Interface Implementation ----------------------------------------------------
	
	/**
	 * @TODO - I haven't started testing comparator yes
	 */
	@Test
	void testReverseOrderingIntInsert() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		smallIntegerQueue.insert(3);
		smallIntegerQueue.insert(5);
		smallIntegerQueue.insert(1);
		
		assertEquals(smallIntegerQueue.findMax(), 5);
	}
	
	@Test
	void testReverseOrderingIntDelete() {
		smallIntegerQueue = new SimplePriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
		smallIntegerQueue.insert(3);
		smallIntegerQueue.insert(4);
		smallIntegerQueue.insert(5);
		smallIntegerQueue.insert(1);
		smallIntegerQueue.deleteMax();
		
		assertEquals(smallIntegerQueue.findMax(), 4);
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
	// Testing Bianry Search -----------------------------------------------------------------

}
