package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimplePriorityQueueTesting {

	private SimplePriorityQueue<String> emptyStringQueue;
	private SimplePriorityQueue<Integer> integerQueue;
	
	
	@BeforeEach
	void setup() throws Exception {
		emptyStringQueue = new SimplePriorityQueue<>();
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
		r
	}
	
	// Need to make sure the contains method works before testing this.
//	@Test
//	void testInsertCollectionOnEmptyStringQueue() {
//		List<String> listOfStrings = List.of("a", "b", "c", "d", "e", "f");
//		emptyStringQueue.insertAll(listOfStrings);
//		assertTrue(emptyStringQueue.contains("a"));
//		assertTrue(emptyStringQueue.contains("b"));
//		assertTrue(emptyStringQueue.contains("c"));
//		assertTrue(emptyStringQueue.contains("d"));
//		assertTrue(emptyStringQueue.contains("e"));
//		assertTrue(emptyStringQueue.contains("f"));
//	}
	
	@Test
	void testDeleteMaxOnFullStringQueue() {
		List<String> listOfStrings = List.of("a", "b", "c", "d", "e", "f");
		emptyStringQueue.insertAll(listOfStrings);
	}
	// Testing Comparator Interface Implementation ----------------------------------------------------
	
	/**
	 * @TODO - I haven't started testing comparator yes
	 */
}
