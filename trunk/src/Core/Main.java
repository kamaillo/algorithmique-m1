package Core;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Graphe g = Parser.parse("/home/etudiant/Master1/Algorithmique des graphes/Projet_Coloration_Arbre/r213.col.txt");
		
		int t[] = new int[g.getNbSommets()];
		t = Algorithme.Welsh_Powell(g);
		
		for(int i=0; i<t.length; i++)
			System.out.print(" " + t[i] + " ");
	}
	
}
