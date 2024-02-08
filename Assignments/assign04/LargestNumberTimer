package assign04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class LargestNumberTimer extends TimerTemplate{

	private List<Integer[]> intList;
	
	public LargestNumberTimer(int timesToLoop, int[] problemSizes) {
		super(problemSizes, timesToLoop);
		
	}


	@Override
	protected void setup(int n) {
		intList = new ArrayList<Integer[]>();
		Random r = new Random();
		
		for(int i = 0; i < n; i++) {
			Integer[] intArr = new Integer[] {r.nextInt(100), r.nextInt(100), r.nextInt(100)};
			intList.add(i, intArr);
		}
		
		
		
		
	}



	@Override
	protected void timingIteration(int n) {
		
		LargestNumberSolver.findKthLargest(intList, n-1);
		
	}



	@Override
	protected void compensationIteration(int n) {

		
		
	}
	
		public static void main(String[] args) {

			int[] pSize = new int[] {100000, 200000, 300000, 400000, 500000, 600000, 700000,
					800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000, 
					1800000, 1900000, 2000000};

			int[] pSize = new int[] {10000, 20000, 30000, 40000, 50000, 60000,
					70000, 80000, 90000, 100000, 110000, 120000, 130000, 140000, 150000, 160000,
					170000, 180000, 190000, 200000};

			//int[] pSize = new int[] {100, 200, 300, 400};
			LargestNumberTimer time = new LargestNumberTimer(5, pSize);

			time.run();
		
	}

}
