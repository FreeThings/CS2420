package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a simple priority queue that sorts the queue as elements are
 * added using a binary search. This comparisons take place using either
 * the comparator or comparable interface. This is a generic class.
 * 
 * Class CS: 2420
 * Assignment 3: SimplePriorityQueue
 * 
 * @author Christopher Hunter and Aiden de Boer
 * @version Feb. 1, 2024
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>{

	private E[] backingArray;
	private int numItems;
	private Comparator<? super E> cmp;
	
	/**
	 * This constructor creates a priority queue that utilizes the comparable
	 * interface.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		this.backingArray = (E[]) new Object[10];
		this.numItems = 0;
		this.cmp = null;
	}
	
	/**
	 * This Constructor establishes a priority queue that uses the comparator
	 * interface.
	 * 
	 * @param cmp - the comparator object to be used
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		this.backingArray = (E[]) new Object[10];
		this.numItems = 0;
		this.cmp = cmp;
	}
	
	/**
	 * Method that returns the maximum value of the queue, which is the
	 * last element of the backingArray.
	 * 
	 * @return the last element of the backingArray
	 * @throws NoSuchElementException - when queue is empty
	 */
	public E findMax() throws NoSuchElementException {
		if (numItems <= 0)
			throw new NoSuchElementException();
		return backingArray[numItems-1];
	}

	/**
	 * Method that returns the maximum value of the queue, which is the
	 * last element of the backingArray, then deletes said element.
	 * 
	 * @return the last element of the backingArray
	 * @throws NoSuchElementException - when queue is empty
	 */
	public E deleteMax() throws NoSuchElementException {

		if (numItems <= 0)
			throw new NoSuchElementException();
		
		E maxElem = backingArray[numItems-1];
		
		backingArray[numItems-1] = null;
		
		--numItems;
		
		return maxElem;
	}

	/**
	 * This method starts by checking the backingArray is big enough to take another
	 * element. If it is not big enough, the backingArray is doubled in length. Then,
	 * the item is inserted at an index that is determined via a binarySearch, 
	 * assuming that there are elements in the array, to compare to by shifting all 
	 * the elements to the right of the determined index.
	 * 
	 * @param item - the item to be inserted into the backingArray
	 */
	public void insert(E item) {
		
		if (numItems+1 > backingArray.length) {
			@SuppressWarnings("unchecked")
			E[] tempArray = (E[]) new Object[backingArray.length*2];
			for (int i = 0; i < numItems; i++) {
				tempArray[i] = backingArray[i];
			}
			backingArray = tempArray;
		}
		
		int index = 0; 
		if (!isEmpty())
			index = binarySearch(item);
		
		for (int i = numItems; i > index ; i--) 
			backingArray[i] = backingArray[i-1];
		backingArray[index] = item;
		++numItems;
		
	}

	/**
	 * Inserts a collection of elements into the backingArray by looping through
	 * each element in the collection, and calling the insert method on the given
	 * element.
	 * 
	 * @param coll - the collection of elements to be inserted
	 */
	public void insertAll(Collection<? extends E> coll) {
		for (E element : coll)
			insert(element);
	}

	/**
	 * Determines whether or not the given item is contained in the backingArray.
	 * Starts by searching for an item in the backingArray, assuming that there 
	 * are elements in the array, by performing a binarySearch. Binary search will
	 * return an index that either does or does not contain the element. This is
	 * checked with an if statement to see if the item and the element of the 
	 * backingArray are equal.
	 * 
	 * @param item - the item to be checked
	 * 
	 * @return true if the item is contained within the backingArray, or false
	 * if the item is not contained within the backingArray.
	 */
	public boolean contains(E item) {

		if (numItems <= 0) {
			return false;
		}
		
		int index = binarySearch(item);
		
		if (item.equals(backingArray[index]))
			return true;
		return false;
		
	}

	/**
	 * Gets the current number of items in the array
	 * 
	 * @return numItems - the current number of items in the array
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Returns true or false depending on if there are items in the array
	 * 
	 * @return true if there are items in the array, or false if there are
	 * no items in the array
	 */
	public boolean isEmpty() {
		
		if (numItems > 0 )
			return false;
		else
			return true;
		
	}

	
	/**
	 * Deletes each element of the backingArray resulting in an empty array
	 */
	public void clear() {
		
		@SuppressWarnings("unchecked")
		E[] tempArr = (E[]) new Object[10];
		
		backingArray = tempArr;
		
		numItems = 0;
	}
	
	/**
	 * Binary search algorithm that determines the index of a given element
	 * 
	 * @param element - the object that is being searched for
	 * 
	 * @return the currentIndex of wherever element fits into the backingArray
	 */
	@SuppressWarnings("unchecked")
	private int binarySearch(E element) {
		int firstIndex = 0;
		int lastIndex = numItems-1;
		if (cmp == null) {
			while(true) {
				int currentIndex = lastIndex - (lastIndex - firstIndex)/2;
				int order = ((Comparable<? super E>)element).compareTo(backingArray[currentIndex]);
				if (order == 0)
					return currentIndex;
				else if (order < 0)
					lastIndex = currentIndex - 1;
				else if (order > 0)
					firstIndex = currentIndex + 1;
				if (firstIndex > lastIndex && order > 0)
					return firstIndex;
				else if (firstIndex > lastIndex && order < 0)
					return lastIndex + 1;
			}
		}
		while(true) {
			int currentIndex = lastIndex - (lastIndex - firstIndex)/2;
			int order = cmp.compare(element, backingArray[currentIndex]);
			if (order == 0)
				return currentIndex;
			else if (order < 0)
				lastIndex = currentIndex - 1;
			else if (order > 0)
				firstIndex = currentIndex + 1;
			if (firstIndex > lastIndex && order > 0)
				return firstIndex;
			else if (firstIndex > lastIndex && order < 0)
				return lastIndex + 1;
		}
	}
}
