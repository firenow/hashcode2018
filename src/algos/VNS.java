package algos;

import java.util.ArrayList;
import java.util.Collections;

import model.Solution;

public class VNS {
	
	public Solution solution;
	private ArrayList<Neighborhood> nhs;
	boolean randomizedNHOrder;
	
	public VNS(Solution sol) {
		this.solution = sol;
		this.nhs = new ArrayList<Neighborhood>();
		this.randomizedNHOrder = false;
		this.initNeighborhoods();
	}
	
	public VNS(Solution sol, boolean randomizedNHOrder) {
		this.solution = sol;
		this.nhs = new ArrayList<Neighborhood>();
		this.randomizedNHOrder = randomizedNHOrder;
		this.initNeighborhoods();
	}
	
	public void initNeighborhoods() {
		this.nhs.add(new Neighborhood(this.solution));
		//ajouter voisinages ici
		//...
	}
	
	/**
	 * Suppose que nh.getNext() renvoie une meilleure solution, ou la solution initiale si aucune n'est meilleure
	 * Parcours de tous les voisinages disponibles (à chaque étape) jusqu'à trouver une solution améliorante
	 */
	public Solution nextStep() {
		ArrayList<Neighborhood> neighborhoods = this.nhs;
		if(randomizedNHOrder)
			Collections.shuffle(neighborhoods);
		for(Neighborhood nh : neighborhoods) {
			if(nh.hasNext()) {
				Solution tmpSol = nh.getNext();
				if(!tmpSol.equals(this.solution))
					return tmpSol;
			}
		}
		return this.solution;
	}

}
