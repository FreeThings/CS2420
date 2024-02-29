package assign05;

import java.util.ArrayList;

public class ArrayListSorterTimer extends TimerTemplate{
    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public ArrayListSorterTimer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
    }
    private ArrayList<Integer> intList;
    @Override
    protected void setup(int n) {
        intList = ArrayListSorter.generatePermuted(n);
    }

    @Override
    protected void timingIteration(int n) {
        ArrayListSorter.mergesort(intList);
    }

    @Override
    protected void compensationIteration(int n) {

    }

    public static void main(String[] args) {

//			int[] pSize = new int[] {100000, 200000, 300000, 400000, 500000, 600000, 700000,
//					800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000,
//					1800000, 1900000, 2000000};

        int[] pSize = new int[] {10000, 20000, 30000, 40000, 50000, 60000,
                70000, 80000, 90000, 100000, 110000, 120000, 130000, 140000, 150000, 160000,
                170000, 180000, 190000, 200000};
         ArrayListSorterTimer time = new ArrayListSorterTimer(pSize, 20);

        time.run();

    }
}
