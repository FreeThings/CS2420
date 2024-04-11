package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the FindKLargest class.
 *
 * Class: CS 2420
 * Assignment 10: BinaryMaxHeap
 *
 * @author - Christopher Hunter and Aiden De Boer
 * @version - 04/11/2024
 */
public class FindKLargestTest {

    private List<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
    }

    @Test
    void testFindKLargestHeapNormal() {
        List<Integer> kLargest = FindKLargest.findKLargestHeap(list, 3);
        assertEquals(3, kLargest.size());
        assertEquals(5, kLargest.get(0));
        assertEquals(4, kLargest.get(1));
        assertEquals(3, kLargest.get(2));
    }

    @Test
    void testFindKLargestHeapNormalComparator() {
        List<Integer> kLargest = FindKLargest.findKLargestHeap(list, 3, (item1, item2) -> item2.compareTo(item1));
        assertEquals(3, kLargest.size());
        assertEquals(0, kLargest.get(0));
        assertEquals(1, kLargest.get(1));
        assertEquals(2, kLargest.get(2));
    }

    @Test
    void testFindKLargestHeapIllegalNegative() {
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, -1));
    }

    @Test
    void testFindKLargestHeapIllegalTooLarge(){
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 7));
    }

    @Test
    void testFindKLargestHeapIllegalNegativeComparator() {
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, -1, (item1, item2) -> item2.compareTo(item1)));
    }

    @Test
    void testFindKLargestHeapIllegalTooLargeComparator(){
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 7, (item1, item2) -> item2.compareTo(item1)));
    }

    @Test
    void testFindKLargestHeapEmptyList() {
        list.clear();
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 3));
    }

    @Test
    void testFindKLargestHeapEmptyListComparator() {
        list.clear();
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 3, (item1, item2) -> item2.compareTo(item1)));
    }

    @Test
    void testFindKLargestHeapNormalKZero() {
        List<Integer> kLargest = FindKLargest.findKLargestHeap(list, 0);
        assertEquals(0, kLargest.size());
    }

    @Test
    void testFindKLargestHeapNormalKZeroComparator() {
        List<Integer> kLargest = FindKLargest.findKLargestHeap(list, 0, (item1, item2) -> item2.compareTo(item1));
        assertEquals(0, kLargest.size());
    }

    @Test
    void testFindKLargestSortNormal() {
        List<Integer> kLargest = FindKLargest.findKLargestSort(list, 3);
        assertEquals(3, kLargest.size());
        assertEquals(5, kLargest.get(0));
        assertEquals(4, kLargest.get(1));
        assertEquals(3, kLargest.get(2));
    }

    @Test
    void testFindKLargestSortNormalComparator() {
        List<Integer> kLargest = FindKLargest.findKLargestSort(list, 3, (item1, item2) -> item2.compareTo(item1));
        assertEquals(3, kLargest.size());
        assertEquals(0, kLargest.get(0));
        assertEquals(1, kLargest.get(1));
        assertEquals(2, kLargest.get(2));
    }

    @Test
    void testFindKLargestSortIllegalNegative() {
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(list, -1));
    }

    @Test
    void testFindKLargestSortIllegalTooLarge(){
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(list, 7));
    }

    @Test
    void testFindKLargestSortIllegalNegativeComparator() {
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(list, -1, (item1, item2) -> item2.compareTo(item1)));
    }

    @Test
    void testFindKLargestSortIllegalTooLargeComparator(){
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(list, 7, (item1, item2) -> item2.compareTo(item1)));
    }

    @Test
    void testFindKLargestSortNormalComparatorKZero() {
        List<Integer> kLargest = FindKLargest.findKLargestSort(list, 0, (item1, item2) -> item2.compareTo(item1));
        assertEquals(0, kLargest.size());
    }

    @Test
    void testFindKLargestSortEmptyList() {
        list.clear();
        List<Integer> kLargest = FindKLargest.findKLargestSort(list, 0);
        assertEquals(0, kLargest.size());
    }

    @Test
    void testFindKLargestSortEmptyListComparator() {
        list.clear();
        List<Integer> kLargest = FindKLargest.findKLargestSort(list, 0, (item1, item2) -> item2.compareTo(item1));
        assertEquals(0, kLargest.size());
    }
}
