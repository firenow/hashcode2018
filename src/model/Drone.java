package model;

import java.util.ArrayList;
import java.util.List;

public class Drone {
	public int id;
	public int row;
	public int col;
	public int turnsNb;
	public List<String> instructions;
	public int availableSpace;

	public Drone(int id, int row, int col, int maxSpace) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.row = row;
		this.col = col;
		this.turnsNb = 0;
		this.instructions = new ArrayList<String> ();
		this.availableSpace = maxSpace;
	}

	public String toString() {
		return "{id : " + this.id + ", row : " + this.row + ", col : " + this.col + ", turnsNb : " + this.turnsNb + "};"
				+ " - turnsNb : " + this.turnsNb + " }";
	}
	
	public String deliver(Order order, int productTypeNb, int productTypeQty, int weight) {
		this.turnsNb+= (getDistance(order.row, order.col, this.row, this.col) + 1);
		this.row = order.row;
		this.col = order.col;
		this.instructions.add(this.turnsNb + " // " + this.id + " D " + order.id + " " + productTypeNb + " " +  productTypeQty);
		
		this.availableSpace+=weight;
			
		return this.id + " D " + order.id + " " + productTypeNb + " " +  productTypeQty;
	}
	
	public String unload(Warehouse wh, int productTypeNb, int productTypeQty, int weight) {
		this.turnsNb+= (getDistance(wh.row, wh.col, this.row, this.col) + 1);
		this.row = wh.row;
		this.col = wh.col;
		this.instructions.add(this.turnsNb + " // " +this.id + " U " + wh.id + " " + productTypeNb + " " +  productTypeQty);
		
		this.availableSpace+=weight;
		
		return this.id + " U " + wh.id + " " + productTypeNb + " " +  productTypeQty;
	}

	public String load(Warehouse wh, int productTypeNb, int productTypeQty, int weight) {
		this.turnsNb+= (getDistance(wh.row, wh.col, this.row, this.col) + 1);
		this.row = wh.row;
		this.col = wh.col;
		this.instructions.add(this.turnsNb + " // " +this.id + " L " +  wh.id + " " + productTypeNb + " " +  productTypeQty);
		
		this.availableSpace-=weight;
			
		return this.id + " L " +  wh.id + " " + productTypeNb + " " +  productTypeQty;
	}
	
	public String wait_(){
		this.turnsNb+= 1;
		this.instructions.add(this.turnsNb + " // " +this.id + " W 1");
		
		return this.id + " W 1";
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return new Double(Math.ceil(Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)))).intValue();
	}
		// TODO Auto-generated method stub
}
