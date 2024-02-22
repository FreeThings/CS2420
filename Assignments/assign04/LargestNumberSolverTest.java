package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class ArrayListSorterTest {
    private Comparator<Integer> intAscendingCmp;
    private Comparator<Integer> intDescendingCmp;
    private Comparator<String> lexComparator;

    @BeforeEach
    void setup() {
        intAscendingCmp = ((num1, num2) -> num2.compareTo(num1));
        intDescendingCmp = ((num1, num2) -> num1.compareTo(num2));
        lexComparator = ((word1, word2) -> word1.compareTo(word2));

    }

    //List generating tests
    @Test
    void testGeneratingAscending() {
        ArrayList<Integer> ascend = ArrayListSorter.generateAscending(10);
        //Implement something to check that sorted properly
        ArrayList<Integer> toBeSorted = ArrayListSorter.generatePermuted(10);
        Collections.sort(toBeSorted);
        assertEquals(ascend.size(), 10);
        assertEquals(toBeSorted, ascend);
    }

    @Test
    void testGeneratingPermuted() {
        ArrayList<Integer> permuted = ArrayListSorter.generatePermuted(10);
        //Implement something to check that sorted properly
        ArrayList<Integer> elem = ArrayListSorter.generateAscending(10);

        assertEquals(permuted.size(), 10);
        assertTrue(permuted.containsAll(elem));
    }

    @Test
    void testGeneratingDescending() {
        ArrayList<Integer> descend = ArrayListSorter.generateDescending(10);
        //Implement something to check that sorted properly
        ArrayList<Integer> toBeSorted = ArrayListSorter.generatePermuted(10);
        toBeSorted.sort(intAscendingCmp);
        assertEquals(descend.size(), 10);
        ArrayList<Integer> sorted = new ArrayList<>();
        sorted.add(10);
        sorted.add(9);
        sorted.add(8);
        sorted.add(7);
        sorted.add(6);
        sorted.add(5);
        sorted.add(4);
        sorted.add(3);
        sorted.add(2);
        sorted.add(1);
        assertEquals(sorted, descend);
    }

    //Mergesort tests
    @Test
    void testMergeSortEmptyInt() {
        ArrayList<Integer> toSort = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>(toSort);
        sorted.sort(intDescendingCmp);
        ArrayListSorter.mergesort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testMergeSortNormalInt() {
        ArrayList<Integer> toSort = new ArrayList<>(10);
        toSort.add(3);
        toSort.add(2);
        toSort.add(20);
        toSort.add(15);
        toSort.add(1);
        toSort.add(16);
        toSort.add(8);
        toSort.add(10);
        toSort.add(9);
        toSort.add(10);
        ArrayList<Integer> sorted = new ArrayList<>(toSort);
        sorted.sort(intDescendingCmp);
        ArrayListSorter.mergesort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testMergesortNormalStrings() {
        ArrayList<String> toSort = new ArrayList<>(10);
        toSort.add("hi");
        toSort.add("a");
        toSort.add("bc");
        toSort.add("yes");
        toSort.add("aiden");
        toSort.add("hello");
        toSort.add("world");
        toSort.add("cs");
        toSort.add("midterm");
        toSort.add("arraylist");
        ArrayList<String> sorted = new ArrayList<>(toSort);
        sorted.sort(lexComparator);
        ArrayListSorter.mergesort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testMergeSortAtInsertionSortThreshold() {
        ArrayList<Integer> toSort = new ArrayList<>(10);
        toSort.add(3);
        toSort.add(2);
        toSort.add(20);
        toSort.add(15);
        toSort.add(1);
        toSort.add(16);
        toSort.add(8);
        toSort.add(10);
        toSort.add(9);
        toSort.add(10);


        ArrayList<Integer> sorted = new ArrayList<>(toSort);
        sorted.sort(intDescendingCmp);
        ArrayListSorter.mergesort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testMergesortSizeOne(){
        ArrayList<Integer> toSort = new ArrayList<>();
        toSort.add(0);
        ArrayList<Integer> sorted = new ArrayList<>();
        sorted.add(0);
        ArrayListSorter.mergesort(toSort);
        assertEquals(sorted, toSort);
    }

    //Quicksort tests
    @Test
    void testQuickSortEmptyInt() {
        ArrayList<Integer> toSort = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>(toSort);
        sorted.sort(intDescendingCmp);
        ArrayListSorter.quicksort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testQuickSortNormalInt() {
        ArrayList<Integer> toSort = new ArrayList<>(10);
        toSort.add(3);
        toSort.add(2);
        toSort.add(20);
        toSort.add(15);
        toSort.add(1);
        toSort.add(16);
        toSort.add(8);
        toSort.add(10);
        toSort.add(9);
        toSort.add(10);

        ArrayList<Integer> sorted = new ArrayList<>(toSort);
        sorted.sort(intDescendingCmp);
        ArrayListSorter.quicksort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testQuicksortNormalStrings() {
        ArrayList<String> toSort = new ArrayList<>(10);
        toSort.add("hi");
        toSort.add("a");
        toSort.add("bc");
        toSort.add("yes");
        toSort.add("aiden");
        toSort.add("hello");
        toSort.add("world");
        toSort.add("cs");
        toSort.add("midterm");
        toSort.add("arraylist");
        ArrayList<String> sorted = new ArrayList<>(toSort);
        sorted.sort(lexComparator);
        ArrayListSorter.quicksort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testQuicksortSmallInts() {
        ArrayList<Integer> toSort = new ArrayList<>(2);
        toSort.add(3);
        toSort.add(2);
        ArrayList<Integer> sorted = new ArrayList<>(toSort);
        sorted.sort(intDescendingCmp);
        ArrayListSorter.quicksort(toSort);
        assertEquals(toSort, sorted);
    }

    @Test
    void testQuicksortSizeOne(){
        ArrayList<Integer> toSort = new ArrayList<>();
        toSort.add(0);
        ArrayList<Integer> sorted = new ArrayList<>();
        sorted.add(0);
        ArrayListSorter.quicksort(toSort);
        assertEquals(sorted, toSort);
    }

    @Test
    void testLastPivot(){
        ArrayList<String> toSort = new ArrayList<>(10);
        toSort.add("hi");
        toSort.add("a");
        toSort.add("bc");
        toSort.add("yes");
        toSort.add("aiden");
        toSort.add("hello");
        toSort.add("world");
        toSort.add("cs");
        toSort.add("midterm");
        toSort.add("arraylist");


        assertEquals(ArrayListSorter.lastPivot(toSort.size() - 1), 9);
        assertEquals(ArrayListSorter.lastPivot(5), 5);
        assertEquals(ArrayListSorter.lastPivot(2), 2);
        assertEquals(ArrayListSorter.lastPivot(0), 0);

    }

    @Test
    void testMedianPivot(){
        ArrayList<String> toSort = new ArrayList<>(10);
        toSort.add("hi");
        toSort.add("a");
        toSort.add("bc");
        toSort.add("yes");
        toSort.add("aiden");
        toSort.add("hello");
        toSort.add("world");
        toSort.add("cs");
        toSort.add("midterm");
        toSort.add("arraylist");


        assertEquals(ArrayListSorter.medianPivot(0, toSort.size() - 1), 4);
        assertEquals(ArrayListSorter.medianPivot(0, 4), 2);
        assertEquals(ArrayListSorter.medianPivot(0, 2), 1);
        assertEquals(ArrayListSorter.medianPivot(0, 1), 0);
        assertEquals(ArrayListSorter.medianPivot(0, 0), 0);
    }

    @Test
    void testLargeMergesort() {
        ArrayList<Integer> largeList = ArrayListSorter.generateAscending(100);
        ArrayList<Integer> permList = ArrayListSorter.generatePermuted(100);
        ArrayListSorter.mergesort(permList);
        assertEquals(largeList, permList);
    }

}
