package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 *
 * Class: CS 2420
 * Assignment 10: BinaryMaxHeap
 *
 * @author Erin Parker, Aiden de Boer, and Christopher Hunter
 * @version 04/11/2024
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {

		BinaryMaxHeap<E> kLargest = new BinaryMaxHeap<>(items);

		if (k < 0 || k > kLargest.size())
			throw new IllegalArgumentException("ILLEGAL!");

		List<E> kList = new ArrayList<>();
		for (int i = 0; i < k; i++)
			kList.add(kLargest.extractMax());

		return kList;

	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		BinaryMaxHeap<E> kLargest = new BinaryMaxHeap<>(items, cmp);

		if (k < 0 || k > kLargest.size())
			throw new IllegalArgumentException("ILLEGAL!");

		List<E> kList = new ArrayList<>();
		for (int i = 0; i < k; i++)
			kList.add(kLargest.extractMax());

		return kList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {

		Collections.sort(items);
		Collections.reverse(items);

		if (k < 0 || k > items.size())
			throw new IllegalArgumentException("ILLEGAL!");

		ArrayList<E> ourList = new ArrayList<>();
		for (int i = 0; i < k; i++)
			ourList.add(items.get(i));

		return ourList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		Collections.sort(items, cmp);
		Collections.reverse(items);

		if (k < 0 || k > items.size())
			throw new IllegalArgumentException("ILLEGAL!");

		ArrayList<E> ourList = new ArrayList<>();
		for (int i = 0; i < k; i++)
			ourList.add(items.get(i));

		return ourList;
	}
}
