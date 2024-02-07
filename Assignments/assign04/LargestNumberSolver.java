package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LargestNumberSolver {

	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && cmp.compare(arr[j+1], arr[j]) > 0; j--){
				T temp = arr[j+1];
				arr[j+1] = arr[j];
				arr[j] = temp;
			}
		}
	}
	
	public static BigInteger findLargestNumber(Integer[] arr) {
		if (arr.length == 0)
			return new BigInteger("0");
		Comparator<Integer> cmp = (num1, num2) -> {
			if (num1 == num2)
				return 0;
			String strNum1 = num1.toString();
			String strNum2 = num2.toString();
			String comp1 = strNum1 + strNum2;
			String comp2 = strNum2 + strNum1;
			if (Integer.parseInt(comp1) > Integer.parseInt(comp2)) {
				return 1;
			} else {
				return -1;
			}
		};
		
		insertionSort(arr, cmp);
		
		StringBuilder bigNumber = new StringBuilder();
		for (int i : arr)
			bigNumber.append(i);
		
		
		return new BigInteger(bigNumber.toString());
	}
	
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		String bigNum = "";
		bigNum += (findLargestNumber(arr).intValue());
		BigInteger largestInt = new BigInteger(bigNum);
		
		
		if(!(findLargestNumber(arr).equals(largestInt)))
			throw new OutOfRangeException("int");
				
		
		return findLargestNumber(arr).intValue();
	}
	
	
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		String bigNum = "";
		bigNum += (findLargestNumber(arr).longValue());
		BigInteger largestLong = new BigInteger(bigNum);
		
		
		if(!(findLargestNumber(arr).equals(largestLong)))
			throw new OutOfRangeException("long");
				
		
		return findLargestNumber(arr).longValue();
	}
	
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger sum = new BigInteger("0");
		for (Integer[] arr : list) {
			sum = sum.add(findLargestNumber(arr));
		}
		return sum;
	}
	
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		if (k > list.size()-1 || k < 0)
			throw new IllegalArgumentException("Enter a k value within the range of list size.");
		BigInteger[] arr = new BigInteger[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = findLargestNumber(list.get(i));
		BigInteger[] arrUnsorted = arr.clone();
		insertionSort(arr, (num1, num2) -> (num1.compareTo(num2)));
		for(int i = 0; i < arr.length; i++) {
			if (arr[k].equals(arrUnsorted[i]))
				return list.get(i);
		}
		return null;
	}
	
	
	public static List<Integer[]> readFile(String filename) {
		List<Integer[]> fullList = new ArrayList<>();
		Scanner file;
		try {
			file = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			return new ArrayList<Integer[]>();
		}
		while (file.hasNextLine()) {
			ArrayList<Integer> arrList = new ArrayList<>();
			while(file.hasNext())
				arrList.add(file.nextInt());
			Integer[] intArr = new Integer[arrList.size()];
			for (int i = 0; i < arrList.size(); i++)
				intArr[i] = arrList.get(i);
			fullList.add(intArr);
		}
		
		return fullList;
	}
	
	
	
	
	
	
	
}
