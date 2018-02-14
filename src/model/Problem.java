package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem {

	boolean[][] pizza;

	int rowNumber;
	int columnNumber;
	int minEachIngredientPerSlice;
	int maxtotalIngredientsPerSlice;
	public Problem(String name) {

	}

	@Override
	public String toString() {
		return "Problem{" +
				"pizza=" + Arrays.toString(pizza) +
				", rowNumber=" + rowNumber +
				", columnNumber=" + columnNumber +
				", minEachIngredientPerSlice=" + minEachIngredientPerSlice +
				", maxtotalIngredientsPerSlice=" + maxtotalIngredientsPerSlice +
				'}';
	}
	
	public void print() {
		System.out.println(this.toString());
	}
	
}
