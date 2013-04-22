package modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class RechercheParcoursLargeur {

	protected static ArrayList<Noeud> recherche(Graph g, String nomNoeudDep,
			ArrayList<Filtre> listFiltres, int niveauMax, TypeUnicite tUnicite) {
		if (g == null || listFiltres == null) {
			System.out.println("Un des arguments est a null");
			return null;
		}
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			int niveau = 0;
			int niveauLastNoeud = 0;
			Filtre fInitial = listFiltres.get(0);
			ArrayList<Noeud> listNoeuds = new ArrayList<Noeud>();
			LinkedList<Noeud> listNoeudsToVisit = new LinkedList<Noeud>();
			ArrayList<Relation> listRelationGlobal = new ArrayList<Relation>();
			ArrayList<Relation> listRelations = noeudDep.getFlux(fInitial.getSens());
			if (listFiltres.size() != 1) {
				// Suppression du premier lien et du premier sens
				listFiltres.remove(0);
			}
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(fInitial.getNomFiltre())) {
					if (tUnicite.toString().equals("RELATION_GLOBAL")) {
						listRelationGlobal.add(r);
					}
					listNoeudsToVisit.add(r.getNoeudDestination());
				}
			}
			if (tUnicite.toString().equals("RELATION_GLOBAL")) {
				rechercheRelationGlobal(listNoeudsToVisit.getFirst(), listFiltres, niveauMax,
						niveau, niveauLastNoeud + 1, listNoeudsToVisit.size(), listNoeuds,
						listNoeudsToVisit, listRelationGlobal);
			} else {
				rechercheNoeudGlobal(listNoeudsToVisit.getFirst(), listFiltres, niveauMax, niveau,
						niveauLastNoeud + 1, listNoeudsToVisit.size(), listNoeuds,
						listNoeudsToVisit);
			}
			return listNoeuds;
		}
		return null;
	}

	private static void rechercheRelationGlobal(Noeud noeud, ArrayList<Filtre> listFiltres,
			int niveauMax, int niveauSens, int niveauLastNoeud, int niveauSaut,
			ArrayList<Noeud> listNoeudsResul, LinkedList<Noeud> listNoeudsToVisit,
			ArrayList<Relation> listRelationGlobal) {
		if (niveauMax != 0 && niveauLastNoeud > niveauMax) {
			return;
		}
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauSens >= listFiltres.size()) {
			niveauSens = listFiltres.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listFiltres.get(niveauSens).getSens());
		for (Relation r : listRelations) {
			boolean present = false;
			for (Relation glob : listRelationGlobal) {
				if (glob.equals(r) || glob.equalsReverse(r)) {
					present = true;
				}
			}
			if (r.estDuTypes(listFiltres) && !listNoeudsResul.contains(r.getNoeudDestination())
					&& !present) {
				listRelationGlobal.add(r);
				listNoeudsToVisit.add(r.getNoeudDestination());
			}
		}
		listNoeudsToVisit.removeFirst();
		niveauSaut--;
		if (!listNoeudsToVisit.isEmpty()) {
			if (niveauSaut == 0) {
				niveauSaut = listNoeudsToVisit.size();
				niveauLastNoeud++;
			}
			rechercheRelationGlobal(listNoeudsToVisit.getFirst(), listFiltres, niveauMax,
					niveauSens + 1, niveauLastNoeud, niveauSaut, listNoeudsResul,
					listNoeudsToVisit, listRelationGlobal);
		}

	}

	private static void rechercheNoeudGlobal(Noeud noeud, ArrayList<Filtre> listFiltres,
			int niveauMax, int niveauSens, int niveauLastNoeud, int niveauSaut,
			ArrayList<Noeud> listNoeudsResul, LinkedList<Noeud> listNoeudsToVisit) {
		if (niveauMax != 0 && niveauLastNoeud > niveauMax) {
			return;
		}
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauSens >= listFiltres.size()) {
			niveauSens = listFiltres.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listFiltres.get(niveauSens).getSens());
		for (Relation r : listRelations) {
			if (r.estDuTypes(listFiltres) && !listNoeudsResul.contains(r.getNoeudDestination())
					&& !listNoeudsToVisit.contains(r.getNoeudDestination())) {
				listNoeudsToVisit.add(r.getNoeudDestination());
			}
		}
		listNoeudsToVisit.removeFirst();
		niveauSaut--;
		if (!listNoeudsToVisit.isEmpty()) {
			if (niveauSaut == 0) {
				niveauSaut = listNoeudsToVisit.size();
				niveauLastNoeud++;
			}
			rechercheNoeudGlobal(listNoeudsToVisit.getFirst(), listFiltres, niveauMax,
					niveauSens + 1, niveauLastNoeud, niveauSaut, listNoeudsResul, listNoeudsToVisit);
		}

	}

}
