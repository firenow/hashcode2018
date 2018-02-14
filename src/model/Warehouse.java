package model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

	public int id;
	public int row;
	public int col;
	public int[] products;
	public boolean isEmpty;

	public Warehouse(int id, int row, int col) {
		this.id = id;
		this.row = row;
		this.col = col;
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "{id : " + this.id + ", row : " + this.row + ", col : " + this.col + ", products nb : " + this.products.length + "}";
	}
}
