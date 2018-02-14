package main;

import io.IoProblem;
import model.Problem;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Main {
	
	private static String[] fileNames = {"busy_day.in", "mother_of_all_warehouses.in", "redundancy.in"};

	public static void main(String[] args)
			throws InterruptedException, ExecutionException, TimeoutException, IOException {
		for (String filename : fileNames) {
			System.out.println("... [MAIN] - Launching");
	
			// Loading
			System.out.println("... [MAIN] - Loading input data");
			File problemFile = new File("src/resources", "medium.in");

			Problem problem = IoProblem.load(problemFile);
			
			problem.print();
			/*System.out.println("... [MAIN] - Ending");
	
			
			// Basic solving
			System.out.println("... [MAIN] - Solving problem");
			Solution sol = new Solution(problem);
			Greedy greedy = new Greedy(sol);
			sol = greedy.solve();
	
			
			IoSolution.save(new File("resources/out"), sol);*/

		}
	}

}
