package assign06;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Class for a Stack of backed by a SinglyLinkedList, which can be full of elements of any type, as it
 * is generic. The type is given in the creation of the object.
 * @param <E> - Type of elements to be stored in the stack
 *
 *
 * @author - Aiden De Boer and Christopher Hunter
 * @version - 2/29/2024
 */
public class LinkedListStack<E> implements Stack<E>{
    private SinglyLinkedList<E> backingList;
    private E top;

    /**
     * Creates a LinkedListStack object with a backing SinglyLinkedList and an empty top item
     */
    public LinkedListStack() {
        this.backingList = new SinglyLinkedList<>();
        this.top = null;
    }

    /**
     * Clears the stack of any objects, setting the top to null and clearing the backing
     * SinglyLinkedList
     */
    @Override
    public void clear() {
        backingList.clear();
        this.top = null;
    }

    /**
     * Returns whether or not the stack is empty as a boolean
     * @return - True if stack is empty, false if not empty
     */
    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    /**
     * Returns the top element of the stack without removing it
     * @return - The top element of the stack
     * @throws NoSuchElementException - If there are no elements in the stack
     */
    @Override
    public E peek() throws NoSuchElementException {
        if(this.backingList.isEmpty())
            throw new NoSuchElementException();

        return top;
    }

    /**
     * Removes the top element of the stack and returns it
     * @return - Top element of the stack
     * @throws NoSuchElementException - If there are no elements in the stack
     */
    @Override
    public E pop() throws NoSuchElementException {
        if(this.backingList.isEmpty())
            throw new NoSuchElementException();
        E temp = this.top;
        backingList.deleteFirst();
        if(backingList.isEmpty())
            this.top = null;
        else
            this.top = backingList.getFirst();

        return temp;
    }

    /**
     * Adds an element to the top of the stack
     * @param element - the element to be added
     */
    @Override
    public void push(E element) {
        this.top = element;
        backingList.insertFirst(element);
    }

    /**
     * Returns the size of the stack
     * @return - The size of the stack
     */
    @Override
    public int size() {
        return backingList.size();
    }
}
