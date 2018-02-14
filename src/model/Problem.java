package model;

import java.util.ArrayList;
import java.util.List;

public class Problem {

	public String name;
	
	public int rowsNb;
	public int colsNb;
	public int turnsNb;
	public int maxPayload;
	public int dronesNb;
	
	public List<Order> orders;
	public List<Warehouse> warehouses;
	
	public List<Integer> orderWeights;

	
	public Problem(String name) {
		// TODO Auto-generated constructor stub
		this.orders = new ArrayList<Order> ();
		this.warehouses = new ArrayList<Warehouse> ();
		this.orderWeights = new ArrayList<Integer> ();
		
		this.name = name;
	}
	
	public void print() {
		System.out.println("");
		System.out.println("... Warehouses");
		for (Warehouse wh : this.warehouses) {
			System.out.print(wh.toString() + ", ");
		}
		
		System.out.println("");
		System.out.println("... Order Weights");
		for (Integer orderWeight : this.orderWeights) {
			System.out.print(orderWeight + ", ");
		}
		
		System.out.println("");
		System.out.println("... Orders");
		for (Order order : this.orders) {
			System.out.print(order.toString() + ", ");
		}
		
	}
	
}
