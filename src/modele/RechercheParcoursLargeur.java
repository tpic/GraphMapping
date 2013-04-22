package modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class RechercheParcoursLargeur {

	public static ArrayList<Noeud> recherche(Graph g, String nomNoeudDep,
			ArrayList<String> listLiens, ArrayList<Sens> listSens, int niveauMax,
			boolean relationGlobal) {
		if (g == null || listLiens == null || listSens == null) {
			System.out.println("Un des arguments est a null");
			return null;
		}
		if (listLiens.size() != listSens.size() || listLiens.size() == 0) {
			System.out.println("Taille de la liste Sens et la liste Liens différente ou nulle");
			return null;
		}
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			int niveau = 0;
			int niveauLastNoeud = 0;
			String lienInitial = listLiens.get(0);
			ArrayList<Noeud> listNoeuds = new ArrayList<Noeud>();
			LinkedList<Noeud> listNoeudsToVisit = new LinkedList<Noeud>();
			ArrayList<Relation> listRelationGlobal = new ArrayList<Relation>();
			ArrayList<Relation> listRelations = noeudDep.getFlux(listSens.get(niveau));
			if (listLiens.size() != 1) {
				// Suppression du premier lien et du premier sens
				listLiens.remove(0);
				listSens.remove(0);
			}
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(lienInitial)) {
					if (relationGlobal) {
						listRelationGlobal.add(r);
					}
					listNoeudsToVisit.add(r.getNoeudDestination());
				}
			}
			if (relationGlobal) {
				// listNoeuds.add(noeudDep);
				rechercheRelationGlobal(listNoeudsToVisit.getFirst(), listLiens, listSens,
						niveauMax, niveau, niveauLastNoeud + 1, listNoeudsToVisit.size(),
						listNoeuds, listNoeudsToVisit, listRelationGlobal);
			} else {
				rechercheNoeudGlobal(listNoeudsToVisit.getFirst(), listLiens, listSens, niveauMax,
						niveau, niveauLastNoeud + 1, listNoeudsToVisit.size(), listNoeuds,
						listNoeudsToVisit);
			}
			return listNoeuds;
		}
		return null;
	}

	private static void rechercheRelationGlobal(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveauSens, int niveauLastNoeud,
			int niveauSaut, ArrayList<Noeud> listNoeudsResul, LinkedList<Noeud> listNoeudsToVisit,
			ArrayList<Relation> listRelationGlobal) {
		if (niveauMax != 0 && niveauLastNoeud > niveauMax) {
			return;
		}
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauSens >= listSens.size()) {
			niveauSens = listSens.size() - 1;
		}
		System.out.println(niveauSens + "-" + niveauLastNoeud);
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveauSens));
		for (Relation r : listRelations) {
			boolean present = false;
			for (Relation glob : listRelationGlobal) {
				if (glob.equals(r) || glob.equalsReverse(r)) {
					present = true;
				}
			}
			if (r.estDuTypes(listLiens) && !listNoeudsResul.contains(r.getNoeudDestination())
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
			rechercheRelationGlobal(listNoeudsToVisit.getFirst(), listLiens, listSens, niveauMax,
					niveauSens + 1, niveauLastNoeud, niveauSaut, listNoeudsResul,
					listNoeudsToVisit, listRelationGlobal);
		}

	}

	private static void rechercheNoeudGlobal(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveauSens, int niveauLastNoeud,
			int niveauSaut, ArrayList<Noeud> listNoeudsResul, LinkedList<Noeud> listNoeudsToVisit) {
		if (niveauMax != 0 && niveauLastNoeud > niveauMax) {
			return;
		}
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauSens >= listSens.size()) {
			niveauSens = listSens.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveauSens));
		for (Relation r : listRelations) {
			if (r.estDuTypes(listLiens) && !listNoeudsResul.contains(r.getNoeudDestination())
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
			rechercheNoeudGlobal(listNoeudsToVisit.getFirst(), listLiens, listSens, niveauMax,
					niveauSens + 1, niveauLastNoeud, niveauSaut, listNoeudsResul, listNoeudsToVisit);
		}

	}

}
