package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Algorithme {

	public static void Welsh_Powell(Graphe g)
	{	
		
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
		
		System.out.println("K = "+nombre_couleurs);
	}
}
