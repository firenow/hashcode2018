package algos;

import model.Solution;

public class HillClimbing {

	public Solution solution;
	private Neighborhood nh;

	public HillClimbing(Solution sol) {
		this.solution = sol;
		this.nh = new Neighborhood (this.solution);
	}

	public Solution nextStep() {
		return this.nh.getNext();
	}
	
	public Solution getCompleteStep () {
		while (this.nh.hasNext()) {
			Solution sol = this.nh.getNext();
			if (sol.isBetterOrEquals(this.solution))
				this.solution = sol;
		}
		
		return this.solution;
	}
	
	public Solution randomStep () {
		return this.nh.getRandomNeighbor();
	}

}
