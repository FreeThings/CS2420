package assign04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver {

	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = i-1; j >= 0 && cmp.compare(arr[j], arr[j+1]) > 0; j--){
				
			}
		
		}
		
		
	}
	
	public static BigInteger findLargestNumber(Integer[] arr) {
		
		
		return null;
	}
	
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		
		
		return 0;
	}
	
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		
		
		return 0;
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
