package Core;

import java.util.ArrayList;

public class Sommet {
	
	private int m_numero;
	private ArrayList<Sommet> m_adjacents;
	
	public Sommet(int numero) {
		this.m_numero = numero;
		this.m_adjacents = new ArrayList<Sommet>();
	}
	
	public int getNumero(){
		return this.m_numero;
	}
	
	public void setNumero(int numero){
		this.m_numero = numero;
	}
	
	public boolean ajouterSommetAdjacent(Sommet s){
		return this.m_adjacents.add(s);
	}
	
	public ArrayList<Sommet> getAdjacents(){
		return m_adjacents;
	}

}
