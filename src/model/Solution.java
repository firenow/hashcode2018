package model;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public Problem problem;
	private Integer score;
	public List<Drone> drones;
	public int currentTurnNb;
	public List<Instruction> instructions;
	

	public Solution(Problem problem) {
		this.problem = problem;
		this.score = 0;					//init à 0 => changer si minimisation !
		this.currentTurnNb = 0;
		this.drones = new ArrayList<Drone> ();
		this.instructions = new ArrayList<Instruction> ();
	}

	public Solution(Solution sol) {
		this.problem = sol.problem;
		this.score = 0;					//init à 0 => changer si minimisation !
		this.currentTurnNb = 0;
		this.drones = new ArrayList<Drone> ();
		this.instructions = new ArrayList<Instruction> ();
	}
	
	public void computeScore() {
		int score = 0;
		
		for (Order order : this.problem.orders) {
			if (order.deliveredTurn != null) {
				score += Math.ceil((((double)(this.problem.turnsNb - order.deliveredTurn)) / this.problem.turnsNb) * 100);
			}
		}
		
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

	public boolean isBetter(Solution solution) {
		return (this.getScore() > solution.getScore()) ? true : false;
	}
	
	// Necessaire pour parcourir les plateaux de solutions equivalentes
	public boolean isBetterOrEquals(Solution solution) {
		return (this.getScore() >= solution.getScore()) ? true : false;
	}
	
	public void createDrones() {
		
		for (int idx = 0; idx < this.problem.dronesNb; idx++) {
			Drone d = new Drone(idx, this.problem.warehouses.get(0).row, this.problem.warehouses.get(0).col, this.problem.maxPayload);
			
			this.drones.add(d);
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Solution))
			return false;

		Solution sol = (Solution) obj;

		// TODO : equals validation

		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "... Solution Score : " + this.getScore();
	}
}
