package main;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import algos.AlgoNaifCommeThierry;
import io.IoProblem;
import io.IoSolution;
import model.Problem;
import model.Solution;

public class Main {

  String fileName = "medium.in";

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, TimeoutException, IOException {
    // for (String filename : fileNames) {
    System.out.println("... [MAIN] - Launching");

    // Loading
    System.out.println("... [MAIN] - Loading input data");
    File problemFile = new File("src/resources", "medium.in");

    Problem problem = IoProblem.load(problemFile);

    problem.print();
    Solution sol = new Solution(problem);

    AlgoNaifCommeThierry algoNaifCommeThierry = new AlgoNaifCommeThierry(sol);

    // sol = greedy.solve();
    IoSolution.save(new File("src/resources/out"), sol);

    /*
     * System.out.println("... [MAIN] - Ending");
     * 
     * // Basic solving System.out.println("... [MAIN] - Solving problem"); Solution sol = new
     * Solution(problem); Greedy greedy = new Greedy(sol); sol = greedy.solve();
     * 
     * 
     * IoSolution.save(new File("resources/out"), sol);
     */

    // }
  }

}
