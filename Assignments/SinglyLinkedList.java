package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>{

    private Node head; // The first node of the list
    private int size; // The number of elements in the list

    /**
     * This is a zero parameter constructor for the SinglyLinkedList that sets the default value
     * of the constructor to null, indicating that the there are not elements in the list.
     */
    public SinglyLinkedList() {
        head = null;
    }

    /**
     * This method inserts an element at the front of the list by setting the head Node value to
     * be equal to the new element which places the old head value as its next Node value.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        Node temp = new Node();
        temp.data = element;
        temp.next = head;
        head = temp;
        size++;
    }

    /**
     * This method inserts a given element at a specified index. It does this by creating a new
     * Node with the given element's data and setting the next Node to be the current Node at the
     * specified index. Then, the current Node's next value is set to the new Node. If the index is
     * out of bounds, an IndexOutOfBoundsException is thrown.
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException - if the index is out of range
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            insertFirst(element);
            return;
        }

        Node temp = new Node();
        temp.data = element;
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        temp.next = current.next;
        current.next = temp;

        size++;
    }

    /**
     * This method returns the first element in the list. If the list is empty, a NoSuchElementException
     * is thrown.
     *
     * @return - the first element in the list
     * @throws NoSuchElementException - if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    /**
     * This method returns a given element at a specified index. It does this by iterating through
     * the list until it finds the specified index and returns the element at that index. If the
     * index is not in range, an IndexOutOfBoundsException is thrown.
     *
     * @param index - the specified index
     * @return - the element at the index
     * @throws IndexOutOfBoundsException - if the index is out of range
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i  = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * This method deletes the first element in the list. It does this by setting the head Node to
     * be equal to the next Node in the list. If the list is empty, a NoSuchElementException is thrown.
     *
     * @return - the first element in the list
     * @throws NoSuchElementException - if the list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        Node temp = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        size--;
        return temp.data;
    }

    /**
     * This method deletes an element at a given index. It does this by iterating through the list
     * until it finds the specified index and deletes the element at that index. If the index is out
     * of the set bounds, an IndexOutOfBoundsException is thrown.
     *
     * @param index - the specified position
     * @return - the element at the given index
     * @throws IndexOutOfBoundsException - if the index is out of bounds
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            return deleteFirst();
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++)
            current = current.next;
        Node temp = current.next;
        current.next = current.next.next;
        size--;
        return temp.data;
    }

    /**
     * This method returns the first index of a specified element in the list. It does this by
     * iterating through the list until it finds the specified element and returns the index of that
     * element. If the element is not in the list or the list is of size 0, -1 is returned.
     *
     * @param element - the element to search for
     * @return - the index of the element
     */
    @Override
    public int indexOf(E element) {
        if (size == 0)
            return -1;

        if (head.data == null)
            return -1;

        for (int i = 0; i < size; i++){
            if (get(i).equals(element))
                return i;
        }
        return -1;
    }

    /**
     * This method returns the size of the list.
     *
     * @return - the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method returns true if the list is empty or false if it is not empty by checking
     * if evaluating head is equal to null.
     *
     * @return - true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * This method clears the list by setting the head node equal to null and size to 0.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }


    /**
     * This method returns an array of all the elements in the list. It does this by iterating
     * through the linked list and setting the array values to be equal to the elements in the list.
     *
     * @return - an array of all the elements in the list
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        if (isEmpty())
            return arr;
        Node current = head;
        for (int i = 0; i < size; i++){
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    /**
     * This method returns an iterator, the nested class below, for the list.
     *
     * @return - an iterator for the list
     */
    @Override
    public Iterator<E> iterator() {
        return new SLLIterator();
    }

    protected class SLLIterator implements Iterator<E> {

        private int pointer;
        private Node previous;
        private Node current;

        private boolean nextCalled;

        public SLLIterator() {
            current = new Node();
            nextCalled = false;
            current.next = head;
            previous = null;
            pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return pointer < size();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            previous = current;
            current = current.next;
            pointer++;

            nextCalled = true;

            return current.data;
        }

        @Override
        public void remove() {

            if (pointer == 1) {
                deleteFirst();
                pointer--;
                previous = null;
                current.next = head;
                nextCalled = false;
                return;
            }

            if (pointer == 0) {
                throw new IllegalStateException();
            }

            previous.next = current.next;
            current = previous;
            size--;
            pointer--;
            nextCalled = false;

        }
    }

    private class Node {
        private E data;
        private Node next;
        private Node() {
            data = null;
            next = null;
        }
    }

}
