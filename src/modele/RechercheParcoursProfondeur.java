package modele;

import java.util.ArrayList;

public class RechercheParcoursProfondeur {
	protected static ArrayList<Noeud> recherche(Graph g, String nomNoeudDep,
			ArrayList<Filtre> listFiltres, int niveauMax, TypeUnicite tUnicite) {
		if (g == null || listFiltres == null) {
			System.out.println("Un des arguments est a null");
			return null;
		}
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			int niveauSens = 0;
			int niveauLastNoeud = 0;
			// String lienInitial = listLiens.get(0);
			Filtre fInitial = listFiltres.get(0);
			ArrayList<Noeud> listNoeudsResul = new ArrayList<Noeud>();
			ArrayList<Relation> listRelationGlobal = new ArrayList<Relation>();
			ArrayList<Relation> listRelations = noeudDep.getFlux(fInitial.getSens());
			if (listFiltres.size() != 1) {
				// Suppression du premier filtre
				listFiltres.remove(0);
			}
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(fInitial.getNomFiltre())) {
					if (tUnicite.toString().equals("RELATION_GLOBAL")) {
						listRelationGlobal.add(r);
						rechercheRelationGlobal(r.getNoeudDestination(), listFiltres, niveauMax,
								niveauSens, niveauLastNoeud + 1, listNoeudsResul,
								listRelationGlobal);
					} else {
						if (!listNoeudsResul.contains(r.getNoeudDestination())) {
							rechercheNoeudGlobal(r.getNoeudDestination(), listFiltres, niveauMax,
									niveauSens, niveauLastNoeud + 1, listNoeudsResul);
						}
					}
				}
			}
			return listNoeudsResul;
		}
		return null;
	}

	private static void rechercheRelationGlobal(Noeud noeud, ArrayList<Filtre> listFiltres,
			int niveauMax, int niveauSens, int niveauLastNoeud, ArrayList<Noeud> listNoeudsResul,
			ArrayList<Relation> listRelationGlobal) {
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauLastNoeud == niveauMax) {
			return;
		}
		if (niveauSens >= listFiltres.size()) {
			niveauSens = listFiltres.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listFiltres.get(niveauSens).getSens());
		for (Relation r : listRelations) {
			if (r.estDuTypes(listFiltres)) {
				boolean present = false;
				for (Relation glob : listRelationGlobal) {
					if (glob.equals(r) || glob.equalsReverse(r)) {
						present = true;
					}
				}
				if (!present) {
					listRelationGlobal.add(r);
					rechercheRelationGlobal(r.getNoeudDestination(), listFiltres, niveauMax,
							niveauSens + 1, niveauLastNoeud + 1, listNoeudsResul,
							listRelationGlobal);
				}
			}
		}
	}

	private static void rechercheNoeudGlobal(Noeud noeud, ArrayList<Filtre> listFiltres,
			int niveauMax, int niveauSens, int niveauLastNoeud, ArrayList<Noeud> listNoeudsResul) {
		listNoeudsResul.add(noeud);
		if (niveauLastNoeud == niveauMax) {
			return;
		}
		if (niveauSens >= listFiltres.size()) {
			niveauSens = listFiltres.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listFiltres.get(niveauSens).getSens());
		for (Relation r : listRelations) {
			if (r.estDuTypes(listFiltres) && !listNoeudsResul.contains(r.getNoeudDestination())) {
				rechercheNoeudGlobal(r.getNoeudDestination(), listFiltres, niveauMax,
						niveauSens + 1, niveauLastNoeud + 1, listNoeudsResul);
			}
		}
	}

}
