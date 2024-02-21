package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ArrayListSorterTest {

    @Test
    void testGeneratingAscending() {
        ArrayList<Integer> ascend = ArrayListSorter.generateAscending(10);
        //Implement something to check that sorted properly
        assertEquals(ascend.size(), 10);
    }

    @Test
    void testGeneratingPermutated() {

    }

    @Test
    void testGeneratingDecending() {

    }

    @Test
    void testMergeSortEmpty() {

    }

    @Test
    void testMergeSortNormal() {

    }

    @Test
    void testQuickSortEmpty() {

    }

    @Test
    void testQuickSortNomral() {

    }

    @Test
    void testMergeSortAtInsertionSortThreshold() {

    }



}
