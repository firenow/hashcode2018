package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.Solution;

public class IoSolution {

  public static void save(File repertory, Solution sol) {
    try {
      System.out.println("... Saving");
      File savedOutputFile = new File(repertory, getSolutionName(sol));
      BufferedWriter bw = new BufferedWriter(new FileWriter(savedOutputFile));

      bw.write(sol.slices.size());
      bw.newLine();

      //
      for (int i = 0; i < sol.slices.size(); i++) {
        bw.write(sol.slices.get(i).toString());

        //
        if (i != sol.slices.size() - 1) {
          bw.newLine();
        }
      }

      bw.close();
      System.out.println("... Saved : " + savedOutputFile.toString());
    } catch (IOException e) {
      System.out.println("... ERROR while saving file");
      e.printStackTrace();
    }
  }

  private static String getSolutionName(Solution sol) {
    return sol.problem.getName() + "_" + sol.getScore() + ".txt";
  }
}
