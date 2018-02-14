package main;

import io.IoSolution;

import java.io.File;
import java.util.concurrent.TimeUnit;

import model.Solution;
import algos.VNS;


public class VNSTask {
	
	public VNS vns;
	long runTimeInMinutes;
	
	public VNSTask(VNS vns, long runTimeInMinutes) {
		this.vns = vns;
		this.runTimeInMinutes = runTimeInMinutes;
	}
	
	public VNSTask(VNS vns) {
		this.vns = vns;
		this.runTimeInMinutes = 525600;
	}
	
	private long getRunTimeInMillis() {
		return TimeUnit.MINUTES.toMillis(this.runTimeInMinutes);
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		long endTime = startTime + this.getRunTimeInMillis();
		System.out.println(" ... VNS planned for " + this.runTimeInMinutes + "mins");

		while (System.currentTimeMillis() < endTime) {
			Solution newSolution = vns.nextStep();
			if (newSolution.equals(vns.solution)) {
				System.out.println("... Solution HAS NOT been improved");
				System.out.println("... Quitting");
				break;
			} else {
				vns.solution = newSolution;
				System.out.println("... Solution HAS been improved");
				System.out.println(vns.solution.toString());
				IoSolution.save(new File("src/main/resources/out"), vns.solution);
			}
		}
		
		System.out.println("... VNS ending (TIMEOUT)");
	}

}
