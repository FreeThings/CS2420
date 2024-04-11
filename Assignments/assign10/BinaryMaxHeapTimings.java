package assign10;

import  java.util.Hashtable;
import java.util.Random;

public class BinaryMaxHeapTimings extends TimerTemplate {
    public BinaryMaxHeapTimings(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
    }

    private BinaryMaxHeap<Integer> ourHeap;

    @Override
    protected void setup(int n) {
        ourHeap = new BinaryMaxHeap<>();
    }

    @Override
    protected void timingIteration(int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            ourHeap.add(rand.nextInt());
        }
        for (int i = 0; i < n; i++) {
            ourHeap.extractMax();
        }
    }

    @Override
    protected void compensationIteration(int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            ourHeap.add(rand.nextInt());
        }
    }

    public static void main(String[] args) {

//			int[] pSize = new int[] {100000, 200000, 300000, 400000, 500000, 600000, 700000,
//					800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000,
//					1800000, 1900000, 2000000};

        int[] pSize = new int[] {10000, 20000, 30000, 40000, 50000, 60000,
                70000, 80000, 90000, 100000, 110000, 120000, 130000, 140000, 150000, 160000,
                170000, 180000, 190000, 200000};


//        int[] pSize = new int[] {1000, 2000, 3000, 4000, 5000, 6000,
//                7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000,
//                17000, 18000, 19000, 20000};

//        int[] pSize = new int[] {1000, 1500, 2000, 2500, 3000, 3500,
//                4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500,
//                9000, 9500, 10000, 10500};

//        int[] pSize = new int[] {100, 200, 300, 400, 500, 600,
//                700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600,
//                1700, 1800, 1900, 2000};

//        int[] pSize = new int[] {10, 20, 30, 40, 50, 60,
//                70, 80, 90, 100, 110, 120, 130, 140, 150, 160,
//                170, 180, 190, 200};
        BinaryMaxHeapTimings time = new BinaryMaxHeapTimings(pSize, 50);

        time.run();

    }
}