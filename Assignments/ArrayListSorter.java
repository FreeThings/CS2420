package assign05;

import java.util.ArrayList;
import java.util.Random;

public class ArrayListSorter {
private static int threshold = 10;

    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr) {
        for (int i = 1; i < arr.size(); i++) {
            T val = arr.get(i);
            int j;
            for (j = i - 1; j >= 0 && arr.get(j).compareTo(val) > 0; j--) {
               T temp = arr.get(j+1);
                arr.set(j+1, arr.get(j));
                arr.set(j, temp);

            }
        }
    }

    private static <T extends Comparable<? super T>> void merge(ArrayList<T> entryArr, ArrayList<T> copy, int startL, int startR, int endR) {

    }
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int low, int high) {
        // Base-case
        if (low >= high)
            return;
// Recursive step (divide and conquer)
//int mid = (low + high) / 2;
        int mid = low + (high - low) / 2;
        mergesort(arr, temp, low, mid);
        mergesort(arr, temp, mid + 1, high);
// Combine
        merge(arr, temp, low, mid + 1, high);
    }



    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {

    }

    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> ascending = new ArrayList<>(size);
        Random r = new Random();
        ascending.add(r.nextInt(50));
        for(int i = 1; i < size; i++) {
            ascending.add(ascending.get(i-1) + r.nextInt(50));
        }

        return ascending;
    }

    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> permuted = new ArrayList<>(size);
        Random r = new Random();
        for(int i = 0; i < size; i++) {
            permuted.add(r.nextInt(1000));
        }

        return permuted;
    }

    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> descending = new ArrayList<>(size);
        Random r = new Random();
        descending.add(r.nextInt(50) + 50);
        for(int i = 1; i < size; i++) {
            descending.add(descending.get(i-1) - r.nextInt(50));
        }

        return descending;
    }


}
