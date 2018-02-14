package main;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import algos.Greedy;
import algos.HillClimbing;
import io.IoProblem;
import io.IoSolution;
import model.Problem;
import model.Solution;

public class Main {
	
	private static String[] fileNames = {"busy_day.in", "mother_of_all_warehouses.in", "redundancy.in"};

	public static void main(String[] args)
			throws InterruptedException, ExecutionException, TimeoutException, IOException {
		for (String filename : fileNames) {
			System.out.println("... [MAIN] - Launching");
	
			// Loading
			System.out.println("... [MAIN] - Loading input data");
			File problemFile = new File("resources", filename);
			Problem problem = IoProblem.load(problemFile);
			
			problem.print();
			System.out.println("... [MAIN] - Ending");
	
			
			// Basic solving
			System.out.println("... [MAIN] - Solving problem");
			Solution sol = new Solution(problem);
			Greedy greedy = new Greedy(sol);
			sol = greedy.solve();
	
			
			IoSolution.save(new File("resources/out"), sol);
			/**
			// Hill Climbing
			System.out.println("... [MAIN] - Improving solution");
			HillClimbing hc = new HillClimbing(sol);
	
			long MAX_RUN_TIME_MIN = 120;
			HillClimbingTask hcTask = new HillClimbingTask(hc, MAX_RUN_TIME_MIN);
			hcTask.run();
	
			System.out.println("... [MAIN] - Ending");
			*/
		}
	}

}
