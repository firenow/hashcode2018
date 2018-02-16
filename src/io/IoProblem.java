package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.Drone;
import model.Order;
import model.Problem;
import model.Warehouse;

public class IoProblem {

	public static Problem load(File problemFile) throws IOException {

		System.out.println("... Reading : " + problemFile.toString());

		Problem problem = new Problem(problemFile.getName());

		BufferedReader br = new BufferedReader(new FileReader(problemFile));

		// read 1ere line;

		String line = br.readLine();

		String[] params = line.split(" ");

		problem.setRowNumber(Integer.valueOf(params[0]));
		problem.setColumnNumber(Integer.valueOf(params[1]));
		problem.setMinEachIngredientPerSlice(Integer.valueOf(params[2]));
		problem.setMaxtotalIngredientsPerSlice(Integer.valueOf(params[3]));

		boolean[][] tableau = new boolean[problem.getRowNumber()][problem.getColumnNumber()];

		int i = 0;
		line = br.readLine();
		while (line != null) {
			for(int j=0; j<problem.getColumnNumber();j++) {
				tableau[i][j] = line.charAt(j) == 'T';
			}
            line = br.readLine();
		}

		problem.setPizza(tableau);

		br.close();

		return problem;

	}
}
