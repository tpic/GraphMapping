package modele;

import java.util.ArrayList;

public class Recherche {
	public final static int ALL_GRAPH = 0;

	public static ArrayList<Noeud> recherche(Graph g, String nomNoeudDep,
			ArrayList<Filtre> listFiltres, int niveauMax, TypeRecherche tRecherche,
			TypeUnicite tUnicite) {
		ArrayList<Noeud> listNoeuds = null;
		switch (tRecherche) {
		case LARGEUR:
			listNoeuds = RechercheParcoursLargeur.recherche(g, nomNoeudDep, listFiltres, niveauMax,
					tUnicite);
			break;
		case PROFONDEUR:
			listNoeuds = RechercheParcoursProfondeur.recherche(g, nomNoeudDep, listFiltres,
					niveauMax, tUnicite);
			break;
		}
		return listNoeuds;
	}
}
