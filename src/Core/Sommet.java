package Core;

import java.util.ArrayList;
import java.util.Comparator;



public class Sommet{
	
	private int m_numero;
	private int m_couleur;
	private ArrayList<Sommet> m_adjacents;
	
	public Sommet(int numero) {
		this.m_numero = numero;
		this.m_couleur = -1;
		this.m_adjacents = null;
	}
	
	public int getNumero(){
		return this.m_numero;
	}
	
	public void setNumero(int numero){
		this.m_numero = numero;
	}
	
	public void setCouleur(int couleur){
		this.m_couleur = couleur;
	}
	
	public int getCouleur(){
		return this.m_couleur;
	}
	
	public boolean ajouterSommetAdjacent(Sommet s){
		if(this.m_adjacents == null){
			this.m_adjacents = new ArrayList<Sommet>();
		}
		return this.m_adjacents.add(s);
	}
	
	public ArrayList<Sommet> getAdjacents(){
		return this.m_adjacents;
	}
	
	public int degreSommet(){
		if(this.m_adjacents != null)
			return this.m_adjacents.size();
		return 0;
	}

}

class SommetComparator implements Comparator<Sommet>{
	
	public int compare(Sommet a, Sommet b){
		int degre1 = a.degreSommet(); 
		int degre2 = b.degreSommet(); 
		if (degre1 > degre2)  return -1; 
		else if(degre1 == degre2) return 0; 
		else return 1; 
	}
	
}
