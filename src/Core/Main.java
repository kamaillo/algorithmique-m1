package Core;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Graphe g = Parser.parse("/home/etudiant/r125.col");
		g.getClass();
		Algorithme.Welsh_Powell("test");
	}
	
}
