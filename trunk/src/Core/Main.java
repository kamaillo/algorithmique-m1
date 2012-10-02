package Core;

public class Main {
	
	public static void main(String[] args) {
		
		// Parsing Graph file
		Graphe g = Parser.parse("/home/etudiant/r125.col");
		
		// Calculating Graph coloration
		int[] result = Algorithme.Welsh_Powell(g);
		
		// Saving result
		Parser.saveResult(result, "/home/etudiant/r125.col.result");
		
		// Debug display
		for(int i=0; i<result.length; i++)
			System.out.print(" " + result[i] + " ");
	}
	
}
