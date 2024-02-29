package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListStackTest {

    private LinkedListStack<Integer> emptyStack;
    private LinkedListStack<Integer> smallStack;

    @BeforeEach
    public void setUp() {
        emptyStack = new LinkedListStack<>();
        smallStack = new LinkedListStack<>();
        smallStack.push(15);
        smallStack.push(30);
        smallStack.push(45);
        smallStack.push(60);
    }

    @Test
    public void testClearNonEmptyStack() {
        smallStack.clear();
        assert(smallStack.isEmpty());
    }

    @Test
    public void testClearEmptyStack() {
        emptyStack.clear();
        assert(emptyStack.isEmpty());
    }

    @Test
    public void testIsEmptyNonEmptyStack() {
        assertFalse(smallStack.isEmpty());
    }

    @Test
    public void testIsEmptyEmptyStack() {
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    public void testPeekNonEmptyStack() {
        assertEquals(60, smallStack.peek());
    }

    @Test
    public void testPeekEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> emptyStack.peek());
    }

    @Test
    public void testPopNonEmptyStack() {
        assertEquals(60, smallStack.pop());
        assertEquals(45, smallStack.pop());
        assertEquals(30, smallStack.pop());
        assertEquals(15, smallStack.pop());
        assertTrue(smallStack.isEmpty());
    }

    @Test
    public void testPopEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> emptyStack.pop());
    }

    @Test
    public void testPeekOnPoppedStack() {
        smallStack.pop();
        assertEquals(45, smallStack.peek());
        smallStack.pop();
        assertEquals(30, smallStack.peek());
        smallStack.pop();
        assertEquals(15, smallStack.peek());
        smallStack.pop();
        assertThrows(NoSuchElementException.class, () -> smallStack.peek());
    }

    @Test
    public void testPushSmallStack() {
        smallStack.push(75);
        assertEquals(75, smallStack.peek());
        smallStack.push(90);
        assertEquals(90, smallStack.peek());
        assertEquals(6, smallStack.size());
    }

    @Test
    public void testPushEmptyStack() {
        emptyStack.push(5);
        assertEquals(5, emptyStack.peek());
        assertEquals(1, emptyStack.size());
    }

    @Test
    public void testSizeNonEmptyStack() {
        assertEquals(4, smallStack.size());
    }

    @Test
    public void testSizeEmptyStack() {
        assertEquals(0, emptyStack.size());
    }

}
