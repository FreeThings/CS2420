package assign10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryMaxHeapTest {

    private BinaryMaxHeap<Integer> intHeap;

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

}
