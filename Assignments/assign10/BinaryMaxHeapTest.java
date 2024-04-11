package assign10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the BinaryMaxHeap class.
 *
 * Class: CS 2420
 * Assignment 10: BinaryMaxHeap
 *
 * @author - Christopher Hunter and Aiden De Boer
 * @version - 04/11/2024
 */
public class BinaryMaxHeapTest {

    private BinaryMaxHeap<Integer> intHeap;
    private final Comparator<Integer> cmp = (item1, item2) -> item2.compareTo(item1);;

    @Test
    void testZeroParamConstructor(){
        intHeap = new BinaryMaxHeap<>();
        assertEquals(0, intHeap.toArray().length);
    }

    @Test
    void testListConstructor(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = new Integer[6];
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        arr[0] = 5;
        arr[1] = 4;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 1;
        arr[5] = 0;
        intHeap = new BinaryMaxHeap<>(list);

        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testComparatorConstructor(){
        intHeap = new BinaryMaxHeap<>(cmp);
        assertEquals(0, intHeap.toArray().length);
    }

    @Test
    void testComparatorListConstructor(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = new Integer[6];
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        arr[5] = 5;
        intHeap = new BinaryMaxHeap<>(list, cmp);

        assertArrayEquals(arr, intHeap.toArray());
    }



    @Test
    void testAdd(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = new Integer[7];
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        intHeap.add(10);

        arr[0] = 10;
        arr[1] = 4;
        arr[2] = 5;
        arr[3] = 3;
        arr[4] = 1;
        arr[5] = 0;
        arr[6] = 2;

        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testAdd2(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = new Integer[8];
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        intHeap.add(10);
        intHeap.add(11);

        arr[0] = 11;
        arr[1] = 10;
        arr[2] = 5;
        arr[3] = 4;
        arr[4] = 1;
        arr[5] = 0;
        arr[6] = 2;
        arr[7] = 3;


        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testAdd3(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = new Integer[9];
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        intHeap.add(10);
        intHeap.add(11);
        intHeap.add(12);

        arr[0] = 12;
        arr[1] = 11;
        arr[2] = 5;
        arr[3] = 10;
        arr[4] = 1;
        arr[5] = 0;
        arr[6] = 2;
        arr[7] = 3;
        arr[8] = 4;

        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testAddComparator(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i  + 10);
        }
        intHeap = new BinaryMaxHeap<>(list, cmp);

        intHeap.add(1);

        Integer[] arr = new Integer[7];
        arr[0] = 1;
        arr[1] = 11;
        arr[2] = 10;
        arr[3] = 13;
        arr[4] = 14;
        arr[5] = 15;
        arr[6] = 12;


        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testPeekNormal(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        assertEquals(5, intHeap.peek());
        intHeap.add(10);
        assertEquals(10, intHeap.peek());
    }

    @Test
    void testPeekNormalComparator(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i + 10);
        }
        intHeap = new BinaryMaxHeap<>(list, cmp);

        assertEquals(10, intHeap.peek());
        intHeap.add(1);
        assertEquals(1, intHeap.peek());
    }

    @Test
    void testPeekEmptyComparator(){
        intHeap = new BinaryMaxHeap<>(cmp);
        assertThrows(NoSuchElementException.class, () -> intHeap.peek());
    }

    @Test
    void testPeekEmpty(){
        intHeap = new BinaryMaxHeap<>();
        assertThrows(NoSuchElementException.class, () -> intHeap.peek());
    }

    @Test
    void testExtractMax(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = new Integer[5];
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        arr[0] = 4;
        arr[1] = 3;
        arr[2] = 0;
        arr[3] = 2;
        arr[4] = 1;


        assertEquals(5, intHeap.extractMax());
        assertEquals(5, intHeap.size());
        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testExtractMaxEmptyComparator(){
        intHeap = new BinaryMaxHeap<>(cmp);
        assertThrows(NoSuchElementException.class, () -> intHeap.extractMax());
    }

    @Test
    void testExtractMaxEmpty(){
        intHeap = new BinaryMaxHeap<>();
        assertThrows(NoSuchElementException.class, () -> intHeap.extractMax());
    }

    @Test
    void testExtractMaxComparator(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = new Integer[5];
        for(int i = 0; i < 6; i++) {
            list.add(i + 10);
        }
        intHeap = new BinaryMaxHeap<>(list, cmp);

        arr[0] = 11;
        arr[1] = 12;
        arr[2] = 15;
        arr[3] = 13;
        arr[4] = 14;

        assertEquals(10, intHeap.extractMax());
        assertEquals(5, intHeap.size());
        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testExtractMaxNormal(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        assertEquals(5, intHeap.extractMax());
        assertEquals(5, intHeap.size());
        assertEquals(4, intHeap.peek());

        Integer[] arr = new Integer[5];
        arr[0] = 4;
        arr[1] = 3;
        arr[2] = 0;
        arr[3] = 2;
        arr[4] = 1;

        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testSize(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        assertEquals(6, intHeap.size());
        intHeap.add(10);
        assertEquals(7, intHeap.size());
        intHeap.extractMax();
        assertEquals(6, intHeap.size());
    }

    @Test
    void testSizeComparator(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i + 10);
        }
        intHeap = new BinaryMaxHeap<>(list, cmp);

        assertEquals(6, intHeap.size());
        intHeap.add(1);
        assertEquals(7, intHeap.size());
        intHeap.extractMax();
        assertEquals(6, intHeap.size());
    }

    @Test
    void testIsEmptyComparator(){
        intHeap = new BinaryMaxHeap<>(cmp);
        assertTrue(intHeap.isEmpty());
        intHeap.add(10);
        assertFalse(intHeap.isEmpty());
        intHeap.extractMax();
        assertTrue(intHeap.isEmpty());
    }

    @Test
    void testIsEmpty(){
        intHeap = new BinaryMaxHeap<>();
        assertTrue(intHeap.isEmpty());
        intHeap.add(10);
        assertFalse(intHeap.isEmpty());
        intHeap.extractMax();
        assertTrue(intHeap.isEmpty());
    }

    @Test
    void testClear(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        intHeap.clear();
        assertEquals(0, intHeap.size());
        assertTrue(intHeap.isEmpty());
        intHeap.add(10);
        assertEquals(1, intHeap.size());

    }

    @Test
    void testClearComparator(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i + 10);
        }
        intHeap = new BinaryMaxHeap<>(list, cmp);

        intHeap.clear();
        assertEquals(0, intHeap.size());
        assertTrue(intHeap.isEmpty());
        intHeap.add(10);
        assertEquals(1, intHeap.size());

    }

    @Test
    void testToArray(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }
        intHeap = new BinaryMaxHeap<>(list);

        Integer[] arr = new Integer[6];
        arr[0] = 5;
        arr[1] = 4;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 1;
        arr[5] = 0;

        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testToArrayComparator(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            list.add(i + 10);
        }
        intHeap = new BinaryMaxHeap<>(list, cmp);

        Integer[] arr = new Integer[6];
        arr[0] = 10;
        arr[1] = 11;
        arr[2] = 12;
        arr[3] = 13;
        arr[4] = 14;
        arr[5] = 15;

        assertArrayEquals(arr, intHeap.toArray());
    }

    @Test
    void testToArrayEmpty(){
        intHeap = new BinaryMaxHeap<>();
        assertEquals(0, intHeap.toArray().length);
    }

}
