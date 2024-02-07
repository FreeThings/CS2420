package assign04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

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
		
		String bigInt = "";
		
		for (int i : arr)
			bigInt += i;
		
		
		return new BigInteger(bigInt);
	}
	
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		return findLargestNumber(arr).intValue();
	}
	
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		return findLargestNumber(arr).longValue();
	}
	
	public static BigInteger sum(List<Integer[]> list) {
	
		
		return null;
	}
	
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		
		
		return null;
	}
	
	
	public static List<Integer[]> readFile(String filename) {
		
		
		
		return null;
	}
	
	
	
	
	
	
	
}
