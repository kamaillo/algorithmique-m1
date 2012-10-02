package Core;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String url_graph_file = args[1];
		String url_coloration_file = args[2];
		
		Graphe g = Parser.parse(url_graph_file);
		
		int t[] = new int[g.getNbSommets()];
		t = Algorithme.welshPowell(g);
		
		for(int i=0; i<t.length; i++)
			System.out.print(" " + t[i] + " ");
		
		Parser.saveResult(t, url_coloration_file);
	}
	
}
