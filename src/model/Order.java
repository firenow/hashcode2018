package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	public int id;
	public int row;
	public int col;
	
	public Integer deliveredTurn;
	
	public int[] products;
	public Integer currentTurn;
	
	public Order(int id, int row, int col) {
		this.id = id;
		this.row = row;
		this.col = col;
		
		this.deliveredTurn = null;
		this.currentTurn = null;
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "{id : " + this.id + ", row : " + this.row + ", col : " + this.col + ", products nb : " + this.products.length + "}";
	}

}
