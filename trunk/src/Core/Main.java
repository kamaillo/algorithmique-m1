package Core;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Graphe g = Parser.parse("/home/etudiant/r125.col");
		g.getClass();
		Graphe g = new Graphe(7);
		
		g.getListeAdjacence().get(0).ajouterSommetAdjacent(g.getListeAdjacence().get(2));
		g.getListeAdjacence().get(2).ajouterSommetAdjacent(g.getListeAdjacence().get(0));
		g.getListeAdjacence().get(0).ajouterSommetAdjacent(g.getListeAdjacence().get(1));
		g.getListeAdjacence().get(1).ajouterSommetAdjacent(g.getListeAdjacence().get(0));
		g.getListeAdjacence().get(2).ajouterSommetAdjacent(g.getListeAdjacence().get(1));
		g.getListeAdjacence().get(1).ajouterSommetAdjacent(g.getListeAdjacence().get(2));
		g.getListeAdjacence().get(2).ajouterSommetAdjacent(g.getListeAdjacence().get(3));
		g.getListeAdjacence().get(3).ajouterSommetAdjacent(g.getListeAdjacence().get(2));
		g.getListeAdjacence().get(2).ajouterSommetAdjacent(g.getListeAdjacence().get(5));
		g.getListeAdjacence().get(5).ajouterSommetAdjacent(g.getListeAdjacence().get(2));
		g.getListeAdjacence().get(3).ajouterSommetAdjacent(g.getListeAdjacence().get(5));
		g.getListeAdjacence().get(5).ajouterSommetAdjacent(g.getListeAdjacence().get(3));
		g.getListeAdjacence().get(3).ajouterSommetAdjacent(g.getListeAdjacence().get(4));
		g.getListeAdjacence().get(4).ajouterSommetAdjacent(g.getListeAdjacence().get(3));
		g.getListeAdjacence().get(4).ajouterSommetAdjacent(g.getListeAdjacence().get(5));
		g.getListeAdjacence().get(5).ajouterSommetAdjacent(g.getListeAdjacence().get(4));
		g.getListeAdjacence().get(4).ajouterSommetAdjacent(g.getListeAdjacence().get(1));
		g.getListeAdjacence().get(1).ajouterSommetAdjacent(g.getListeAdjacence().get(4));
		g.getListeAdjacence().get(5).ajouterSommetAdjacent(g.getListeAdjacence().get(1));
		g.getListeAdjacence().get(1).ajouterSommetAdjacent(g.getListeAdjacence().get(5));
		
		Algorithme.Welsh_Powell(g);
	}
	
}
