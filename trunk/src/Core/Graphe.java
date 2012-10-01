package Core;

import java.util.ArrayList;

public class Graphe {
	
	private ArrayList<Sommet> listeAdjacence;
	private int nb_sommets;
	
	public Graphe(int nb_sommets){
		this.nb_sommets = nb_sommets;
		this.listeAdjacence = new ArrayList<Sommet>();
			
	}
	
	public int getNbSommets(){
		return this.nb_sommets;
	}
	
	public void setNbSommets(int nb_sommets){
		this.nb_sommets = nb_sommets;
	}
	
	public boolean ajouterSommetListeAdjacence(Sommet s){
		return this.listeAdjacence.add(s);
	}
	
	public ArrayList<Sommet> getListeAdjacence(){
		return this.listeAdjacence;
	}
	
}
