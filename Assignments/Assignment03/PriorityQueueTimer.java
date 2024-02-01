package assign03;

import java.util.List;

public class PriorityQueueTimer extends TimerTemplate{

	private SimplePriorityQueue<Integer> insertQueue;
	private SimplePriorityQueue<Integer> maxQueue;

	
	public PriorityQueueTimer(int timesToLoop, int[] problemSizes) {
		super(problemSizes, timesToLoop);
		
	}

	
	protected void setup(int n) {
		insertQueue = new SimplePriorityQueue<Integer>();
		maxQueue = new SimplePriorityQueue<Integer>();
		maxQueue.insert(0);
		
	}

	
	protected void timingIteration(int n) {
//		for(int i = 0; i < n; i++)
//			insertQueue.insert(777);
		
//		maxQueue.findMax();
	}

	
	protected void compensationIteration(int n) {
//		for(int i = 0; i < n; i++) {
			//do nothing
//		}
	}
	
	
	public static void main(String[] args) {

		int[] pSize = new int[] {100000, 200000, 300000, 400000, 500000, 600000, 700000,
				800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000, 
				1800000, 1900000, 2000000};
		
//		int[] pSize = new int[] {1000};
		
		PriorityQueueTimer time = new PriorityQueueTimer(20, pSize);
		
		Record[] times = time.run();
		
		for (int i = 0; i < times.length; i++) {
			System.out.println(times[i].toString());
		}
			
	}
	
	
	
}
