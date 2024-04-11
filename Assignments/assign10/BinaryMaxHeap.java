package assign10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class implements a binary max heap data structure using a priority queue interface.
 * It has a backing array that stores the items in the heap and a comparator to decide the ordering
 * of the items in the heap, if natural ordering is not used.
 * @param <E> - Type of items to be stored in the BinaryMaxHeap
 *
 * Class: CS 2420
 * Assignment 10: BinaryMaxHeap
 *
 * @author - Aiden De Boer and Christopher Hunter
 * @version - 04/11/2024
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

    private E[] backingArr;

    private Comparator<? super E> cmp;

    private int size;

    /**
     * Default constructor for BinaryMaxHeap with natural ordering
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap() {

        this.backingArr = (E[])new Object[10];
        this.backingArr[0] = null;
        this.size = 0;
    }

    /**
     * Creates an empty BinaryMaxHeap with a comparator chosen in method call for a custom ordering
     * @param cmp - Comparator to organize BinaryMaxHeap with
     */
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        this();
        this.cmp = cmp;
    }

    /**
     * Constructs a BinaryMaxHeap using a list and the buildHeap function (natural ordering is used)
     * @param list - List of items to create BinaryMaxHeap with
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list) {
        this.backingArr = (E[])new Object[list.size() + 1];
        this.backingArr[0] = null;
        this.cmp = null;
        buildHeap(list);
    }

    /**
     * Constructs a BinaryMaxHeap using a list and the buildHeap function (ordering is done using the
     * inputted comparator)
     * @param list - List of items to create BinaryMaxHeap with
     * @param cmp - Comparator to decide ordering of BinaryMaxHeap
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        this.backingArr = (E[])new Object[list.size() + 1];
        this.backingArr[0] = null;
        this.cmp = cmp;
        buildHeap(list);
    }

    /**
     * This method adds an item to the BinaryMaxHeap and positions it correctly
     * in the heap by percolating it up. Will resize the array if the backing array is full.
     *
     * @param item - Item to add to the BinaryMaxHeap
     */
    @Override
    public void add(E item) {
        if (size >= backingArr.length - 1)
            resizeArray();

        backingArr[size+1] = item;

        percolateUp(size+1);
        ++size;
    }

    /**
     * This method returns the maximum item in the BinaryMaxHeap. If the BinaryMaxHeap is empty,
     * a NoSuchElementException is thrown.
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if(backingArr[1] == null)
            throw new NoSuchElementException("The BinaryMaxHeap is empty");
        return backingArr[1];
    }

    /**
     * This method removes and returns the maximum item in the BinaryMaxHeap. If the BinaryMaxHeap is empty,
     * a NoSuchElementException is thrown.
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extractMax() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("The BinaryMaxHeap is empty");
        E max = backingArr[1];
        swap(1, size);
        backingArr[size] = null;
        --size;
        percolateDown(1);
        return max;
    }

    /**
     * This method returns the number of items in the BinaryMaxHeap
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * This method returns whether the BinaryMaxHeap is empty or not
     */
    @Override
    public boolean isEmpty() {
        return backingArr[1] == null;
    }

    /**
     * This method empties the BinaryMaxHeap of all items by creating a new backing array
     * and setting the size to 0
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        backingArr = (E[])new Object[10];
        size = 0;
    }

    /**
     * This method returns an array of the items in the BinaryMaxHeap in the same order they appear in the backing array
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        E[] newArr = (E[]) new Object[size];

        for(int i = 1; i < size + 1; i++) {
            if(backingArr[i] != null)
                newArr[i-1] = backingArr[i];
        }

        return newArr;
    }


    /**
     * Builds the BinaryMaxHeap using the list given
     *
     * @param list - List of items used to build BinaryMaxHeap
     */
    private void buildHeap(List<? extends E> list) {
        this.size = list.size();

        for (int i = 0; i < list.size(); i++)
            backingArr[i + 1] = list.get(i);

        for (int i = (backingArr.length-1)/2; i > 0; i-- )
            percolateDown(i);

    }

    /**
     * Percolates the item at the given index up to its correct position
     *
     * @param index - Index of item to percolate up
     */
    private void percolateUp(int index) {
        if(innerCompare()) { // Using comparator
            if (getParentIndex(index) != null){ // If parent exists
                if (cmp.compare(backingArr[index], backingArr[getParentIndex(index)]) > 0){ // If parent is less than child
                    swap(index, getParentIndex(index));
                    percolateUp(getParentIndex(index)); // Recursively call percolateUp
                }
            }
        } else { // Using natural ordering
            if (getParentIndex(index) != null){ // If parent exists
                if (((Comparable<? super E>)backingArr[index]).compareTo(backingArr[getParentIndex(index)]) > 0){ // If parent is less than child
                    swap(index, getParentIndex(index));
                    percolateUp(getParentIndex(index)); // Recursively call percolateUp
                }
            }
        }

    }

    /**
     * Percolates the item at the given index down to its correct position
     *
     * @param index - Index of item to percolate down
     */
    private void percolateDown(int index) {
        if (innerCompare()){ // Using comparator
            if (getRightChildIndex(index) != null) // If right child exists
                if(cmp.compare(backingArr[index], backingArr[getRightChildIndex(index)]) < 0){ // If parent is less than right child
                    swap(index, getRightChildIndex(index));
                    percolateDown(getRightChildIndex(index)); // Recursively call percolateDown
                }
            if (getLeftChildIndex(index) != null) // If left child exists
                if(cmp.compare(backingArr[index], backingArr[getLeftChildIndex(index)]) < 0){ // If parent is less than left child
                    swap(index, getLeftChildIndex(index));
                    percolateDown(getLeftChildIndex(index)); // Recursively call percolateDown
                }

        } else { // Using natural ordering
            if (getRightChildIndex(index) != null) // If right child exists
                if(((Comparable<? super E>)backingArr[index]).compareTo(backingArr[getRightChildIndex(index)]) < 0) { // If parent is less than right child
                    swap(index, getRightChildIndex(index));
                    percolateDown(getRightChildIndex(index)); // Recursively call percolateDown
                }
            if (getLeftChildIndex(index) != null)  // If left child exists
                if(((Comparable<? super E>)backingArr[index]).compareTo(backingArr[getLeftChildIndex(index)]) < 0){ // If parent is less than left child
                    swap(index, getLeftChildIndex(index));
                    percolateDown(getLeftChildIndex(index)); // Recursively call percolateDown
                }
        }
    }

    /**
     * Returns whether the BinaryMaxHeap is using a comparator or not
     *
     * @return - True if using a comparator, false otherwise
     */
    private boolean innerCompare() {
        return this.cmp != null;
    }

    /**
     * Returns the index of the parent of the item from a given index or if parent does not exist
     *
     * @param index - Index of item to find parent of
     * @return - Index of parent of item from given index or null if parent does not exist
     */
    private Integer getParentIndex(int index){
        int temp = index / 2;
        if (temp < 1)
            return null;
        return temp;
    }

    /**
     * Returns the index of the left child of the item from a given index or if left child does not exist
     *
     * @param index - Index of item to find left child of
     * @return - Index of left child of item from given index or null if left child does not exist
     */
    private Integer getLeftChildIndex(int index){
        int temp = index * 2;
        if (temp > size)
            return null;
        return temp;
    }

    /**
     * Returns the index of the right child of the item from a given index or if right child does not exist
     *
     * @param index - Index of item to find right child of
     * @return - Index of right child of item from given index or null if right child does not exist
     */
    private Integer getRightChildIndex(int index){
        int temp = index * 2 + 1;
        if (temp > size)
            return null;
        return temp;
    }

    /**
     * Swaps the items at the given indices
     *
     * @param index1 - Index of first item to swap
     * @param index2 - Index of second item to swap
     */
    private void swap(int index1, int index2){
        E temp = backingArr[index1];
        backingArr[index1] = backingArr[index2];
        backingArr[index2] = temp;
    }

    /**
     * Resizes the backing array to double its current size
     */
    @SuppressWarnings("unchecked")
    private void resizeArray() {
        E[] newArr = (E[]) new Object[backingArr.length * 2];
        for(int i = 0; i < backingArr.length; i++) { // Copy over all elements
            newArr[i] = backingArr[i];
        }

        backingArr = newArr; // Set backing array to new array
    }
}
