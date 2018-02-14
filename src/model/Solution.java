package model;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public Problem problem;

	public List<Slice> slices = new ArrayList<>();


	public Solution(Problem problem) {
		this.problem = problem;
	}

	public Solution(Solution sol) {
		this.problem = sol.problem;
	}
	
	public void computeScore() {

	}

	public int getScore() {
		return 0;
	}

	public boolean isBetter(Solution solution) {
		return (this.getScore() > solution.getScore()) ? true : false;
	}
	
	// Necessaire pour parcourir les plateaux de solutions equivalentes
	public boolean isBetterOrEquals(Solution solution) {
		return (this.getScore() >= solution.getScore()) ? true : false;
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
