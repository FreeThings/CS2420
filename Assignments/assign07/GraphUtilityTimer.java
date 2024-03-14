package assign07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphUtilityTimer extends TimerTemplate{
    public GraphUtilityTimer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
    }

    List<Integer> sources1 = new ArrayList<>();
    List<Integer> destinations1 = new ArrayList<>();

    @Override
    protected void setup(int n) {
        sources1.clear();
        destinations1.clear();
        List<List<Integer>> sourceAndDest = generateList1(n);
        sources1 = sourceAndDest.get(0);
        destinations1 = sourceAndDest.get(1);

//        for (int i = 0; i < n; i++){
//            System.out.println(sources1.get(i) + " " + destinations1.get(i));
//        }
    }

    @Override
    protected void timingIteration(int n) {
        //THIS IS THE CODE TO TIME areConnected()
//        GraphUtility.areConnected(sources1, destinations1, 0, (n-1));

        //THIS IS THE CODE TO TIME shortestPath()
//        try{
//            GraphUtility.shortestPath(sources1, destinations1, 0, (n-1));
//        } catch (IllegalArgumentException e) {
//        }

        //THIS IS THE CODE TO TIME sort()
        GraphUtility.sort(sources1, destinations1);
    }

    @Override
    protected void compensationIteration(int n) {

    }

    public static void main(String[] args) {

//			int[] pSize = new int[] {100000, 200000, 300000, 400000, 500000, 600000, 700000,
//					800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000,
//					1800000, 1900000, 2000000};

//        int[] pSize = new int[] {10000, 20000, 30000, 40000, 50000, 60000,
//                70000, 80000, 90000, 100000, 110000, 120000, 130000, 140000, 150000, 160000,
//                170000, 180000, 190000, 200000};

//        int[] pSize = new int[] {1000, 2000, 3000, 4000, 5000, 6000,
//                7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000,
//                17000, 18000, 19000, 20000};

        int[] pSize = new int[] {1000, 1500, 2000, 2500, 3000, 3500,
                4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500,
                9000, 9500, 10000, 10500};

//        int[] pSize = new int[] {100, 200, 300, 400, 500, 600,
//                700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600,
//                1700, 1800, 1900, 2000};
        GraphUtilityTimer time = new GraphUtilityTimer(pSize, 500);

        time.run();

    }

    public static List<List<Integer>> generateList1(int n){

        // generate a list of vertices
        List<List<Integer>> vertex = new ArrayList<>();
        List<Integer> sources = new ArrayList<>();
        List<Integer> destinations = new ArrayList<>();

        // generate a list of sources and destinations
        for (int i = 0; i < 2 * n; i++) {
            sources.add((int) (Math.random() * n));
            destinations.add((int) (Math.random() * n));
        }

        sources.add(0);
        destinations.add((int) (Math.random() * n));

        sources.add((int) (Math.random() * n));
        destinations.add(n-1);

        vertex.add(sources);
        vertex.add(destinations);

        return vertex;
    }
}
