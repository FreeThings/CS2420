package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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
     * Adds the given item to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
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
     * Returns, but does not remove, the maximum item this priority queue.
     * O(1)
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
     * Returns and removes the maximum item this priority queue.
     * O(log N)
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
     * Returns the number of items in this priority queue.
     * O(1)
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this priority queue is empty, false otherwise.
     * O(1)
     */
    @Override
    public boolean isEmpty() {
        return backingArr[1] == null;
    }

    /**
     * Empties this priority queue of items.
     * O(1)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        backingArr = (E[])new Object[10];
    }

    /**
     * Creates and returns an array of the items in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is needed for grading purposes. The root item
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
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
     * Builds a heap from the given list of items.
     */
    private void buildHeap(List<? extends E> list) {
        this.size = list.size();

        for (int i = 0; i < list.size(); i++)
            backingArr[i + 1] = list.get(i);

        for (int i = (backingArr.length-1)/2; i > 0; i-- )
            percolateDown(i);

    }

    private void percolateUp(int index) {
        if(innerCompare()) {
            if (getParentIndex(index) != null){
                if (cmp.compare(backingArr[index], backingArr[getParentIndex(index)]) > 0){
                    swap(index, getParentIndex(index));
                    percolateUp(getParentIndex(index));
                }
            }
        } else {
            if (getParentIndex(index) != null){
                if (((Comparable<? super E>)backingArr[index]).compareTo(backingArr[getParentIndex(index)]) > 0){
                    swap(index, getParentIndex(index));
                    percolateUp(getParentIndex(index));
                }
            }
        }

    }

    private void percolateDown(int index) {
        if (innerCompare()){
            if (getRightChildIndex(index) != null)
                if(cmp.compare(backingArr[index], backingArr[getRightChildIndex(index)]) < 0){
                    swap(index, getRightChildIndex(index));
                    percolateDown(getRightChildIndex(index));
                }

            if (getLeftChildIndex(index) != null)
                if(cmp.compare(backingArr[index], backingArr[getLeftChildIndex(index)]) < 0){
                    swap(index, getLeftChildIndex(index));
                    percolateDown(getLeftChildIndex(index));
                }

        } else {
            if (getRightChildIndex(index) != null)
                if(((Comparable<? super E>)backingArr[index]).compareTo(backingArr[getRightChildIndex(index)]) < 0) {
                    swap(index, getRightChildIndex(index));
                    percolateDown(getRightChildIndex(index));
                }
            if (getLeftChildIndex(index) != null)
                if(((Comparable<? super E>)backingArr[index]).compareTo(backingArr[getLeftChildIndex(index)]) < 0){
                    swap(index, getLeftChildIndex(index));
                    percolateDown(getLeftChildIndex(index));
                }


        }
    }

    /**
     * Returns whether the BinaryMaxHeap is using a comparator or not
     * @return - True if using a comparator, false otherwise
     */
    private boolean innerCompare() {
        return this.cmp != null;
    }

    private Integer getParentIndex(int index){
        int temp = index / 2;
        if (temp < 1)
            return null;
        return temp;
    }

    private Integer getLeftChildIndex(int index){
        int temp = index * 2;
        if (temp > size)
            return null;
        return temp;
    }

    private Integer getRightChildIndex(int index){
        int temp = index * 2 + 1;
        if (temp > size)
            return null;
        return temp;
    }

    private void swap(int index1, int index2){
        E temp = backingArr[index1];
        backingArr[index1] = backingArr[index2];
        backingArr[index2] = temp;
    }
    @SuppressWarnings("unchecked")
    private void resizeArray() {
        E[] newArr = (E[]) new Object[backingArr.length * 2];
        for(int i = 0; i < backingArr.length; i++) {
            newArr[i] = backingArr[i];
        }
        backingArr = newArr;
    }
}
