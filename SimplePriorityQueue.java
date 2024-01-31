package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>, Comparator, Comparable{

	private E[] backingArray;
//	private E maxElem;
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		this.backingArray = (E[]) new Object[10];
//		this.maxElem = null;
		
	}
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		this.backingArray = (E[]) new Object[10];
//		this.maxElem = null;
		
	}
	
	
	
	
	public E findMax() throws NoSuchElementException {
		return backingArray[0];
	}

	
	public E deleteMax() throws NoSuchElementException {

		E maxElem = backingArray[0];
		
		@SuppressWarnings("unchecked")
		E[] tempArr = (E[])new Object[backingArray.length];
		
		for (int i = 1; i < tempArr.length; i++) {
			tempArr[i-1] = backingArray[i];
		}
		backingArray = tempArr;
		
		
		return maxElem;
	}

	
	public void insert(Object item) {
		

		
		
	}

	
	public void insertAll(Collection coll) {


		
		
		
		
		
	}

	
	public boolean contains(Object item) {


		
		
		
		return false;
	}

	
	public int size() {


		
		
		return 0;
	}

	
	public boolean isEmpty() {


		
		
		return false;
	}

	
	public void clear() {


		
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
