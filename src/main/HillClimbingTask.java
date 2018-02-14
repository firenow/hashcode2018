package main;

import java.io.File;
import java.util.concurrent.TimeUnit;

import algos.HillClimbing;
import io.IoSolution;
import model.Solution;

public class HillClimbingTask {

	HillClimbing hc;
	long runTimeInMinutes;

	public HillClimbingTask(HillClimbing hc, long runTimeInMinutes) {
		this.hc = hc;
		this.runTimeInMinutes = runTimeInMinutes;
	}

	public HillClimbingTask(HillClimbing hc) {
		this.hc = hc;
		// 1 year
		this.runTimeInMinutes = 525600;
	}

	private long getRunTimeInMillis() {
		return TimeUnit.MINUTES.toMillis(this.runTimeInMinutes);
	}

	public void run() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		long endTime = startTime + this.getRunTimeInMillis();
		System.out.println(" ... Hill Climbing planned for " + this.runTimeInMinutes + "mins");

		while (System.currentTimeMillis() < endTime) {
			Solution newSolution = hc.nextStep();
			if (newSolution.equals(hc.solution)) {
				System.out.println("... Solution HAS NOT been improved");
				System.out.println("... Quitting");
				break;
			} else {
				hc.solution = newSolution;
				System.out.println("... Solution HAS been improved");
				System.out.println(hc.solution.toString());
				IoSolution.save(new File("src/main/resources/out"), hc.solution);
			}
		}
		
		System.out.println("... Normal Hill Climbing ending (TIMEOUT)");
	}

}