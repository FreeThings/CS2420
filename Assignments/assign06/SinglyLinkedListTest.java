package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> normalIntList;
    private SinglyLinkedList<Integer> emptyIntList;
    private SinglyLinkedList<Integer>.SLLIterator normalIntIterator;
    private SinglyLinkedList<Integer>.SLLIterator emptyIntIterator;

    @BeforeEach
    void setUp() throws Exception {
        normalIntList = new SinglyLinkedList<>();
        normalIntList.insertFirst(15);
        normalIntList.insertFirst(30);
        normalIntList.insertFirst(45);
        normalIntList.insertFirst(60);
        emptyIntList = new SinglyLinkedList<>();
    }

    @Test
    void testGetFirst() {
        assertThrows(NoSuchElementException.class, () -> emptyIntList.getFirst());
    }

    @Test
    void testInsertFirst() {
        emptyIntList.insertFirst(15);
        emptyIntList.insertFirst(30);
        emptyIntList.insertFirst(45);
        assertEquals(45, emptyIntList.deleteFirst());
        assertEquals(30, emptyIntList.deleteFirst());
        assertEquals(15, emptyIntList.deleteFirst());
    }

    @Test
    void testInsertIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> normalIntList.insert(5, 15));
        assertThrows(IndexOutOfBoundsException.class, () -> normalIntList.insert(-1, 15));
    }

    @Test
    void testInsertFront() {
        normalIntList.insert(0, 15);
        assertEquals(15, normalIntList.get(0));
        assertEquals(15, normalIntList.get(4));
    }

    @Test
    void testInsertMiddle() {
        normalIntList.insert(2, 15);
        assertEquals(15, normalIntList.get(2));
        assertEquals(30, normalIntList.get(3));
    }

    @Test
    void testInsertEnd() {
        normalIntList.insert(4, 15);
        assertEquals(15, normalIntList.get(4));
    }

    @Test
    void testGetFirstNormalCase() {
        assertEquals(60, normalIntList.getFirst());
    }

    @Test
    void testGetFirstNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> emptyIntList.getFirst());
        emptyIntList.insertFirst(15);
        emptyIntList.deleteFirst();
        assertThrows(NoSuchElementException.class, () -> emptyIntList.getFirst());
    }

    @Test
    void testGetNormalCase() {
        assertEquals(60, normalIntList.get(0));
        assertEquals(45, normalIntList.get(1));
        assertEquals(30, normalIntList.get(2));
        assertEquals(15, normalIntList.get(3));
    }

    @Test
    void testGetIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> normalIntList.get(4));
        assertThrows(IndexOutOfBoundsException.class, () -> normalIntList.get(-1));
    }

    @Test
    void testDeleteFirstNormalCase() {
        assertEquals(60, normalIntList.deleteFirst());
        assertEquals(45, normalIntList.deleteFirst());
        assertEquals(30, normalIntList.deleteFirst());
        assertEquals(15, normalIntList.deleteFirst());
    }

    @Test
    void testDeleteFirstNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> emptyIntList.deleteFirst());
        emptyIntList.insertFirst(15);
        emptyIntList.deleteFirst();
        assertThrows(NoSuchElementException.class, () -> emptyIntList.deleteFirst());
    }

    @Test
    void testDeleteNormalCase() {
        assertEquals(60, normalIntList.delete(0));
        assertEquals(45, normalIntList.delete(0));
        assertEquals(15, normalIntList.delete(1));
        assertEquals(30, normalIntList.delete(0));
    }

    @Test
    void DeleteIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> normalIntList.delete(4));
        assertThrows(IndexOutOfBoundsException.class, () -> normalIntList.delete(-1));
    }

    @Test
    void testIndexOfNormalCase() {
        assertEquals(0, normalIntList.indexOf(60));
        assertEquals(1, normalIntList.indexOf(45));
        assertEquals(2, normalIntList.indexOf(30));
        assertEquals(3, normalIntList.indexOf(15));
    }

    @Test
    void testIndexOfNoEmptyList() {
        assertEquals(-1, emptyIntList.indexOf(15));
    }

    @Test
    void testIndexOfElementNotInList() {
        assertEquals(-1, normalIntList.indexOf(100));
    }

    @Test
    void testSizeNormalCase() {
        assertEquals(4, normalIntList.size());
    }

    @Test
    void testSizeEmptyList() {
        assertEquals(0, emptyIntList.size());
    }

    @Test
    void testIsEmptyNormalCase() {
        assertFalse(normalIntList.isEmpty());
    }

    @Test
    void testIsEmptyEmptyList() {
        assertTrue(emptyIntList.isEmpty());
    }

    @Test
    void testClearNormalCase() {
        normalIntList.clear();
        assertTrue(normalIntList.isEmpty());
    }

    @Test
    void testClearEmptyList() {
        emptyIntList.clear();
        assertTrue(emptyIntList.isEmpty());
    }

    @Test
    void testToArrayNormalCase() {
        Object[] arr = normalIntList.toArray();
        Object[] expected = {60, 45, 30, 15};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testToArrayEmptyList() {
        Object[] arr = emptyIntList.toArray();
        Object[] expected = {};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testIteratorReturnsNewIterator() {
        assertDoesNotThrow(() -> normalIntList.iterator());
    }

    @Test
    void testIteratorNextNormalCase() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        assertEquals(60, normalIntIterator.next());
        assertEquals(45, normalIntIterator.next());
        assertEquals(30, normalIntIterator.next());
        assertEquals(15, normalIntIterator.next());
        assertThrows(NoSuchElementException.class, () -> normalIntIterator.next());
    }

    @Test
    void testIteratorNextEmptyList() {
        emptyIntIterator = (SinglyLinkedList<Integer>.SLLIterator) emptyIntList.iterator();
        assertThrows(NoSuchElementException.class, () -> emptyIntIterator.next());
    }

    @Test
    void testIteratorHasNextNormalCase() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        assertTrue(normalIntIterator.hasNext());
        normalIntIterator.next();
        assertTrue(normalIntIterator.hasNext());
        normalIntIterator.next();
        assertTrue(normalIntIterator.hasNext());
        normalIntIterator.next();
        assertTrue(normalIntIterator.hasNext());
        normalIntIterator.next();
        assertFalse(normalIntIterator.hasNext());
    }

    @Test
    void testIteratorHasNextEmptyList() {
        emptyIntIterator = (SinglyLinkedList<Integer>.SLLIterator) emptyIntList.iterator();
        assertFalse(emptyIntIterator.hasNext());
    }

    @Test
    void testIteratorRemoveNormalCase() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        normalIntIterator.next();
        normalIntIterator.remove();
        Object[] arr = normalIntList.toArray();
        Object[] expected = {45, 30, 15};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testIteratorRemoveFrontTwice() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        normalIntIterator.next();
        normalIntIterator.remove();
        normalIntIterator.next();
        normalIntIterator.remove();
        Object[] arr = normalIntList.toArray();
        Object[] expected = {30, 15};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testIteratorRemoveMiddle() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.remove();
        Object[] arr = normalIntList.toArray();
        Object[] expected = {60, 30, 15};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testIteratorRemoveMiddleTwice() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.remove();
        normalIntIterator.next();
        normalIntIterator.remove();
        Object[] arr = normalIntList.toArray();
        Object[] expected = {60, 15};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testIteratorRemoveEnd() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.remove();
        Object[] arr = normalIntList.toArray();
        Object[] expected = {60, 45, 30};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testIteratorRemoveEndTwice() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.next();
        normalIntIterator.remove();
        assertThrows(NoSuchElementException.class, () -> normalIntIterator.next());
        Object[] arr = normalIntList.toArray();
        Object[] expected = {60, 45, 30};
        assertArrayEquals(expected, arr);
    }

    @Test
    void testIteratorRemoveWithoutNext() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        assertThrows(IllegalStateException.class, () -> normalIntIterator.remove());
    }

    @Test
    void testIteratorRemoveTwice() {
        normalIntIterator = (SinglyLinkedList<Integer>.SLLIterator) normalIntList.iterator();
        normalIntIterator.next();
        normalIntIterator.remove();
        assertThrows(IllegalStateException.class, () -> normalIntIterator.remove());
    }

    @Test
    void testIteratorRemoveEmptyList() {
        emptyIntIterator = (SinglyLinkedList<Integer>.SLLIterator) emptyIntList.iterator();
        assertThrows(IllegalStateException.class, () -> emptyIntIterator.remove());
    }

}
