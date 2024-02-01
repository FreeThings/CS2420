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
		
	}

	
	protected void timingIteration(int n) {
		for(int i = 0; i < n; i++)
			insertQueue.insert(777);
		
		
	}

	
	protected void compensationIteration(int n) {
		for(int i = 0; i < n; i++) {
			//do nothing
		}
	}
	
	
	public static void main(String[] args) {

		int[] pSize = new int[] {100000, 200000, 300000, 400000, 500000, 600000, 700000,
				800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000, 
				1800000, 1900000, 2000000};
		
		
		PriorityQueueTimer time = new PriorityQueueTimer(20, pSize);
		
		time.run();
	}
	
	
	
}
