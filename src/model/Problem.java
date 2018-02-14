package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Problem {

	boolean[][] pizza;

	String name;

	int rowNumber;
	int columnNumber;
	int minEachIngredientPerSlice;
	int maxtotalIngredientsPerSlice;

	public Problem(String name) {
		this.name = name;
	}

	public boolean[][] getPizza() {
		return pizza;
	}

	public void setPizza(boolean[][] pizza) {
		this.pizza = pizza;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public int getMinEachIngredientPerSlice() {
		return minEachIngredientPerSlice;
	}

	public void setMinEachIngredientPerSlice(int minEachIngredientPerSlice) {
		this.minEachIngredientPerSlice = minEachIngredientPerSlice;
	}

	public int getMaxtotalIngredientsPerSlice() {
		return maxtotalIngredientsPerSlice;
	}

	public void setMaxtotalIngredientsPerSlice(int maxtotalIngredientsPerSlice) {
		this.maxtotalIngredientsPerSlice = maxtotalIngredientsPerSlice;
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
