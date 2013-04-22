package modele;

import java.util.ArrayList;

public class RechercheParcoursProfondeur {
	public static ArrayList<Noeud> recherche(Graph g, String nomNoeudDep,
			ArrayList<String> listLiens, ArrayList<Sens> listSens, int niveauMax,
			boolean relationGlobal) {
		if (g == null || listLiens == null || listSens == null) {
			System.out.println("Un des arguments est a null");
			return null;
		}
		if (listLiens.size() != listSens.size() || listLiens.size() == 0) {
			System.out.println("Taille de la liste Sens et la liste Liens différentes ou nulle");
			return null;
		}
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			int niveauSens = 0;
			int niveauLastNoeud = 0;
			String lienInitial = listLiens.get(0);
			ArrayList<Noeud> listNoeudsResul = new ArrayList<Noeud>();
			ArrayList<Relation> listRelationGlobal = new ArrayList<Relation>();
			ArrayList<Relation> listRelations = noeudDep.getFlux(listSens.get(niveauSens));
			if (listLiens.size() != 1) {
				// Suppression du premier lien et du premier sens
				listLiens.remove(0);
				listSens.remove(0);
			}
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(lienInitial)) {
					if (relationGlobal) {
						listRelationGlobal.add(r);
						rechercheRelationGlobal(r.getNoeudDestination(), listLiens, listSens,
								niveauMax, niveauSens, niveauLastNoeud + 1, listNoeudsResul,
								listRelationGlobal);
					} else {
						if (!listNoeudsResul.contains(r.getNoeudDestination())) {
							rechercheNoeudGlobal(r.getNoeudDestination(), listLiens, listSens,
									niveauMax, niveauSens, niveauLastNoeud + 1, listNoeudsResul);
						}
					}
				}
			}
			return listNoeudsResul;
		}
		return null;
	}

	private static void rechercheRelationGlobal(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveauSens, int niveauLastNoeud,
			ArrayList<Noeud> listNoeudsResul, ArrayList<Relation> listRelationGlobal) {
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauLastNoeud == niveauMax) {
			return;
		}
		if (niveauSens >= listSens.size()) {
			niveauSens = listSens.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveauSens));
		for (Relation r : listRelations) {
			if (r.estDuTypes(listLiens)) {
				boolean present = false;
				for (Relation glob : listRelationGlobal) {
					if (glob.equals(r) || glob.equalsReverse(r)) {
						present = true;
					}
				}
				if (!present) {
					listRelationGlobal.add(r);
					rechercheRelationGlobal(r.getNoeudDestination(), listLiens, listSens,
							niveauMax, niveauSens + 1, niveauLastNoeud + 1, listNoeudsResul,
							listRelationGlobal);
				}
			}
		}
	}

	private static void rechercheNoeudGlobal(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveauSens, int niveauLastNoeud,
			ArrayList<Noeud> listNoeudsResul) {
		listNoeudsResul.add(noeud);
		if (niveauLastNoeud == niveauMax) {
			return;
		}
		if (niveauSens >= listSens.size()) {
			niveauSens = listSens.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveauSens));
		for (Relation r : listRelations) {
			if (r.estDuTypes(listLiens) && !listNoeudsResul.contains(r.getNoeudDestination())) {
				rechercheNoeudGlobal(r.getNoeudDestination(), listLiens, listSens, niveauMax,
						niveauSens + 1, niveauLastNoeud + 1, listNoeudsResul);
			}
		}
	}

}
