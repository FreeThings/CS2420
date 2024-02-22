package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListSorter {
    private final static int threshold = 1000;

    /**
     * This is a basic insertion sort method that takes in two index values, a high and low, that can be
     * used to represent a subarray that can be sorted through in an ArrayList.
     *
     * @param arr - the ArrayList to be sorted
     * @param low - the low index value of the ArrayList
     * @param high - the high index value of the ArrayList
     * @param <T> - the type of the ArrayList
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int low, int high) {
        for (int i = low + 1; i <= high; i++){
            T val = arr.get(i);
            int j = i - 1;

            while (j >= low && arr.get(j).compareTo(val) > 0){
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, val);
        }
    }

    /**
     * A public driver method for mergesort that sets up initial temp ArrayList, high, and low index values.
     *
     * @param arr - the ArrayList to be sorted
     * @param <T> - the type of the ArrayList
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {

        ArrayList<T> temp = new ArrayList<>(arr.size());

        temp.addAll(arr);

        int low = 0;
        int high = arr.size() - 1;

        mergesort(arr, temp, low, high);
    }

    /**
     *The recursive private method that is called from the driver mergesort method. This method recursively
     * calls itself to narrow down the indices of the ArrayList to be of size 1. Once in this state, it
     * will call the merge method which sorts the values to be switched within the temp ArrayList, which then
     * applies to the actual arr ArrayList.
     *
     * @param arr - the primary ArrayList to be sorted
     * @param temp - the temp array where the values are switched in before being applied to arr
     * @param low - the low index of the ArrayList
     * @param high - the high index of the ArrayList
     * @param <T> - the type of the ArrayList
     */
    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int low, int high){

        if (low >= high)
            return;

        if(high - low <= threshold && threshold > 0) {
            insertionSort(arr, low, high);
        } else {
            int mid = low + (high - low) / 2;

            mergesort(arr, temp, low, mid);
            mergesort(arr, temp, mid + 1, high);

            merge(arr, temp, low, mid + 1, high);
        }

    }

    /**
     * This method "merges" the two sub-arrays together. It does this by using the startL, startR, and endR value
     * to identify the sub-arrays in the larger arr ArrayList. From there, the values are compared on the currL
     * and currR to create a sub-array that is the appropriate length. If there are values left over in either
     * subarray they are just added on to the end of the new sub-array. The arr is altered by first altering
     * the temp ArrayList, and then copied over to the arr ArrayList.
     *
     * @param arr - the ArrayList to be sorted
     * @param temp - the temporary ArrayList to be used to help sort arr
     * @param startL - the start of the left sub-array
     * @param startR - the start of the right sub-array
     * @param endR - the end of the right sub-array
     * @param <T> - the type of the ArrayList
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int startL, int startR, int endR) {
        int index = startL;
        int currL = startL;
        int currR = startR;
        int endL = startR - 1;

        while  (currL < endL+1 && currR < endR+1){
            if (arr.get(currL).compareTo(arr.get(currR)) <= arr.get(currR).compareTo(arr.get(currL))){
                temp.set(index, arr.get(currL));
                currL++;
            } else {
                temp.set(index, arr.get(currR));
                currR++;
            }
            index++;
        }

        while (currL < endL + 1){
            temp.set(index, arr.get(currL));
            currL++;
            index++;
        }

        while (currR < endR + 1){
            temp.set(index, arr.get(currR));
            currR++;
            index++;
        }

        for (int i = startL; i < endR + 1; i++)
            arr.set(i, temp.get(i));

    }

    /**
     * The public driver method of the quicksort method that calls the recursive quicksort method.
     *
     * @param arr - the ArrayList to be sorted
     * @param <T> - the type of the ArrayList
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {

        quicksort(arr, 0, arr.size()-1);

    }

    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int low, int high){

        if (low >= high)
            return;



//        int pivot = randomPivot(low, high);
        int pivot = medianPivot(low, high);
//        int pivot = lastPivot(high);

        swap(arr, pivot, high);

        pivot = high;

        int i = low;
        int j = high;

        while(i < j){
            while(arr.get(i).compareTo(arr.get(pivot)) <= arr.get(pivot).compareTo(arr.get(i)) && i < j)
                i++;

            while(arr.get(j).compareTo(arr.get(pivot)) >= arr.get(pivot).compareTo(arr.get(j)) && i < j)
                j--;

            swap(arr, i, j);

        }

        swap(arr, i, high);

        quicksort(arr, low, i - 1);
        quicksort(arr, i + 1, high);

    }

    private static <T extends Comparable<? super T>> void swap(ArrayList<T> arr, int index1, int index2){
        T temp = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, temp);
    }

    public static int randomPivot(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low + 1) + low;
    }

    public static int medianPivot(int low, int high) {
        return (high - low) / 2 + low;
    }

    public static int lastPivot(int size){
        return size;
    }

    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> ascending = new ArrayList<>(size);

        ascending.add(1);
        for(int i = 1; i < size; i++) {
            ascending.add(ascending.get(i-1) + 1);
        }

        return ascending;
    }

    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> permuted = generateAscending(size);
        Collections.shuffle(permuted);
        return permuted;
    }

    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> descending = new ArrayList<>();
        for(int i = size; i > 0; i--) {
            descending.add(i);
        }

        return descending;
    }


}
