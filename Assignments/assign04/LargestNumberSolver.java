package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class for static methods related to finding the largest number possibly formed out of an array of
 * Integers. For example, in an array defined as {23, 16, 52, 3, 6}, the largest possible number to be
 * formed is 65232316, which is returned as a BigInteger.
 * 
 * This class also contains methods for finding the array with the kth largest number possible from the
 * above method, a method for an insertion sort for generic arrays, the sum of the largest numbers possible
 * from each array in a list of Integers, and methods for finding the largest number possible as an Integer
 * or Long (so long as the largest number is not too big for either data type).
 * 
 * Class: CS 2420
 * Assignment 4: LargestNumberSolver
 * 
 * @author Christopher Hunter and Aiden De Boer
 * @version 02/08/2024
 */
public class LargestNumberSolver {

	
	/**
	 * Takes in an array of any type as well as a custom ordering in the form of a comparator object or lambda
	 * expression to define how to sort the array through an insertion sort (Increasing how much of the array is
	 * sorted by one and placing that element in the right order in the sorted portion, then increasing sorted portion
	 * and repeating).
	 * 
	 * @param <T> - Type of array to be sorted
	 * @param arr - Array of elements to be sorted
	 * @param cmp - Comparator object to sort the given array using
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && cmp.compare(arr[j], arr[j+1]) > 0; j--){
				T temp = arr[j+1];
				arr[j+1] = arr[j];
				arr[j] = temp;
			}
		}
		
	}
	/**
	 * Takes in an Integer array and finds the largest number possible as a BigInteger object.
	 * For example, in an array defined as {23, 16, 52, 3, 6}, the largest possible number to be
	 * formed is 65232316, which is returned as a BigInteger. Does not modify original array
	 * 
	 * @param arr - Array of Integers to search for biggest number possible
	 * @return - BigInteger value for the Largest Number possible from combining elements
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		Integer[] tempArr = arr.clone();
		
		if (arr.length == 0)
			return new BigInteger("0");
		
		Comparator<Integer> cmp = (num1, num2) -> {
			if (num1 == num2)
				return 0;
			String strNum1 = num1.toString();
			String strNum2 = num2.toString();
			String comp1 = strNum1 + strNum2;
			String comp2 = strNum2 + strNum1;
			if (comp1.equals(comp2))
				return 0;
			else if (Integer.parseInt(comp1) > Integer.parseInt(comp2)) {
				return -1;
			} else {
				return 1;
			}
		};
		
		insertionSort(tempArr, cmp);
		
		StringBuilder bigNumber = new StringBuilder();
		for (int i : tempArr)
			bigNumber.append(i);
		
		
		return new BigInteger(bigNumber.toString());
	}
	
	/**
	 * Finds and returns largest possible number as outlined in the findLargestNumber 
	 * method as an int, unless the largest number is too large for the int data type, 
	 * in which case an OutOfRangeException is thrown
	 * 
	 * @param arr - Integer array to search for largest number
	 * @return - int value for largest number possible, if it isn't too big for the int data type
	 * @throws OutOfRangeException - Exception thrown if largest number possible for the given array
	 * is too large for the int data type
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		String bigNum = "";
		bigNum += (findLargestNumber(arr).intValue());
		BigInteger largestInt = new BigInteger(bigNum);
		
		
		if(!(findLargestNumber(arr).equals(largestInt)))
			throw new OutOfRangeException("int");
				
		
		return findLargestNumber(arr).intValue();
	}
	
	/**
	 * Finds and returns largest possible number as outlined in the findLargestNumber 
	 * method as a long, unless the largest number is too large for the long data type, 
	 * in which case an OutOfRangeException is thrown
	 * 
	 * @param arr - Integer array to search for largest number
	 * @return - long value for largest number possible, if it isn't too big for the long data type
	 * @throws OutOfRangeException - Exception thrown if largest number possible for the given array
	 * is too large for the long data type
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		String bigNum = "";
		bigNum += (findLargestNumber(arr).longValue());
		BigInteger largestLong = new BigInteger(bigNum);
		
		
		if(!(findLargestNumber(arr).equals(largestLong)))
			throw new OutOfRangeException("long");
				
		
		return findLargestNumber(arr).longValue();
	}
	
	/**
	 * Adds all largest numbers possible in a list of Integer arrays together then returns
	 * that value as a BigInteger value
	 * 
	 * @param list - Given list of Integer Arrays to add together
	 * @return - BigInteger value for the sum of all largest numbers possible in a list of Integer Arrays
	 */
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger sum = new BigInteger("0");
		for (Integer[] arr : list) {
			sum = sum.add(findLargestNumber(arr));
		}
		return sum;
	}
	
	/**
	 * Takes in a list of Integer arrays, then finds the largest numbers possible for each array,
	 * then finds the kth largest number (with k being given in the parameter). Throws IllegalArgumentException
	 * if k is larger than the size of the list.
	 * 
	 * @param list - List of Integer arrays to look through
	 * @param k - The position in the largest number ranking to have the array returned for (i.e. 
	 * 0 would be largest, 2, would be third largest, 26 would be 26th largest, etc)
	 * @return - Array in the given list that has the kth largest number possible
	 * @throws IllegalArgumentException - Exception thrown if k is larger than list size
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		if (k > list.size()-1 || k < 0)
			throw new IllegalArgumentException("Enter a k value within the range of list size.");
		
		BigInteger[] arr = new BigInteger[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = findLargestNumber(list.get(i));
		
		BigInteger[] arrUnsorted = arr.clone();
		insertionSort(arr, (num1, num2) -> (num2.compareTo(num1)));
		
		for(int i = 0; i < arr.length; i++) {
			if (arr[k].equals(arrUnsorted[i]))
				return list.get(i);
		}
		return null;
	}
	
	/**
	 * Takes in a fileName, then looks for said file reads its content, then translates it
	 * to an Integer ArrayList
	 * 
	 * @param filename - Name of file to be read
	 * @return - Integer ArrayList with file contents within
	 */
	public static List<Integer[]> readFile(String filename) {
		List<Integer[]> fullList = new ArrayList<>();
		Scanner file;
		
		try {
			file = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			return new ArrayList<Integer[]>();
		}
		
		while (file.hasNextLine()) {
			
			ArrayList<Integer> tempList = new ArrayList<>();
			Scanner tempScan = new Scanner(file.nextLine());
			
			while (tempScan.hasNextInt())
				tempList.add(tempScan.nextInt());
			
			Integer[] intArr = new Integer[tempList.size()];
			for (int i = 0; i < intArr.length; i++)
				intArr[i] = tempList.get(i);
			
			fullList.add(intArr);
		}
		
		return fullList;
	}
	
	
	
	
	
	
	
}
