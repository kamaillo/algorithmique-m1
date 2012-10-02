package Core;

import java.util.ArrayList;

public class Graphe {
	
	private ArrayList<Sommet> listeAdjacence;
	private int nb_sommets;
	
	public Graphe(int nb_sommets){
		this.nb_sommets = nb_sommets;
		this.listeAdjacence = new ArrayList<Sommet>();
		for(int i=1; i<=this.nb_sommets; ++i){
			this.listeAdjacence.add(new Sommet(i));
			this.listeAdjacence.get(i-1).setNumero(i);
		}
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

	public boolean areAllColored(){
		for(int i=0; i<this.listeAdjacence.size(); ++i){
			if(this.listeAdjacence.get(i).getCouleur() == -1)
				return false;
		}
		return true;
	}
	
}
