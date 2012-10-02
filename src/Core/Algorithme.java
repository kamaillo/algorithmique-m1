package Core;

import java.util.Collections;

/**
 * Provides methods for calculate a Graph coloration.
 */
public class Algorithme {

	/**
	 * Use an implementation of Welsh-Powell algorithme to determine a Graph coloration.
	 * @param g
	 * Target Graph for Welsh-Powell algorithme.
	 * @return
	 * Returns an array of integer, where each distinct integer is a distinct color.
	 * Note that result array is sorted by edges number.
	 */
	public static int[] welshPowell(Graphe g)
	{	
		// Sort edges by degrees
		Collections.<Sommet>sort(g.getListeAdjacence(), new SommetComparator());
		
		int nombre_couleurs = 0;
		int i=0;
		
		while(!g.areAllColored()){

			if(g.getListeAdjacence().get(i).getCouleur() == -1){
				nombre_couleurs++;
				g.getListeAdjacence().get(i).setCouleur(nombre_couleurs);
				for (int j = 0; j < g.getListeAdjacence().size(); j++) {
					if(g.getListeAdjacence().get(j).getCouleur() == -1){
						boolean isAdjacentColored = false;
						if(g.getListeAdjacence().get(j).getAdjacents() != null){
							for(int k=0; k<g.getListeAdjacence().get(j).getAdjacents().size(); ++k ){
								if(g.getListeAdjacence().get(j).getAdjacents().get(k).getCouleur() == nombre_couleurs)
									isAdjacentColored = true;
							}
						}
						if(!isAdjacentColored)
							g.getListeAdjacence().get(j).setCouleur(nombre_couleurs);
					}
				}
			}
			i++;
			
		}
		
		Collections.<Sommet>sort(g.getListeAdjacence(), new SommetNumberComparator());
		
		System.out.println("K = "+nombre_couleurs);
		
		int tableau_coloration[] = new int[g.getNbSommets()];
		for(int l=0; l< tableau_coloration.length; ++l){
			tableau_coloration[l] = g.getListeAdjacence().get(l).getCouleur();
		}
		return tableau_coloration;
	}
}
