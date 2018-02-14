package algos;

import model.Solution;

public class Neighborhood {

	private Solution solution;

	public Neighborhood(Solution solution) {
		this.solution = solution;
	}
	
	public Solution getRandomNeighbor () {
		Solution rndSol = new Solution(this.solution);
		
		// TODO Tirer au hasard une solution voisine et la retourner;
		
		return rndSol;
	}
	
	public boolean hasNext () {
		// TODO Vrai tant qu'il y a des voisins non explores
		return false;
	}
	
	public Solution getNext () {
		Solution next = new Solution(this.solution);

		// TODO Retourne le voisin non exploré suivant
		
		return next;
	}

}
