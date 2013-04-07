package modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Recherche {

	public static ArrayList<Noeud> parcoursProfondeur(Graph g, String nomNoeudDep,
			ArrayList<String> listLiens, ArrayList<Sens> listSens, int niveauMax,
			boolean relationGlobal) {
		if (listLiens.size() != listSens.size()) {
			System.out.println("Taille de la liste Sens et la liste Liens différentes");
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
			// Suppression du premier lien et du premier sens
			listLiens.remove(0);
			listSens.remove(0);
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(lienInitial)) {
					if (relationGlobal) {
						listRelationGlobal.add(r);
						// listRelationGlobal.add(r.reverse(noeudDep,
						// r.getMapAttribut()));
						parcoursProfondeurRelationGlobalRecur(r.getNoeudDestination(), listLiens,
								listSens, niveauMax, niveauSens, niveauLastNoeud + 1,
								listNoeudsResul, listRelationGlobal);
					} else {
						parcoursProfondeurRecur(r.getNoeudDestination(), listLiens, listSens,
								niveauMax, niveauSens, niveauLastNoeud + 1, listNoeudsResul);
					}
				}
			}
			return listNoeudsResul;
		}
		return null;
	}

	private static void parcoursProfondeurRelationGlobalRecur(Noeud noeud,
			ArrayList<String> listLiens, ArrayList<Sens> listSens, int niveauMax, int niveauSens,
			int niveauLastNoeud, ArrayList<Noeud> listNoeudsResul,
			ArrayList<Relation> listRelationGlobal) {
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauLastNoeud == niveauMax || listSens.size() == 0) {
			return;
		}
		if (niveauSens >= listSens.size()) {
			niveauSens = listSens.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveauSens));
		for (Relation r : listRelations) {
			Relation reverse = r.reverse(noeud, r.getMapAttribut());
			boolean present = false;
			for (Relation glob : listRelationGlobal) {
				if (reverse.equals(glob)) {
					present = true;
				}
			}
			// !listRelationGlobal.contains(reverse)
			if (r.estDuTypes(listLiens) && !present) {
				listRelationGlobal.add(r);
				// listRelationGlobal.add(r.reverse(noeud, r.getMapAttribut()));
				parcoursProfondeurRelationGlobalRecur(r.getNoeudDestination(), listLiens, listSens,
						niveauMax, niveauSens + 1, niveauLastNoeud + 1, listNoeudsResul,
						listRelationGlobal);
			}
		}
	}

	private static void parcoursProfondeurRecur(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveauSens, int niveauLastNoeud,
			ArrayList<Noeud> listNoeudsResul) {
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (niveauLastNoeud == niveauMax || listSens.size() == 0) {
			return;
		}
		if (niveauSens >= listSens.size()) {
			niveauSens = listSens.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveauSens));
		for (Relation r : listRelations) {
			if (r.estDuTypes(listLiens)) {
				parcoursProfondeurRecur(r.getNoeudDestination(), listLiens, listSens, niveauMax,
						niveauSens + 1, niveauLastNoeud + 1, listNoeudsResul);
			}
		}
	}

	public static ArrayList<Noeud> parcoursLargeur(Graph g, String nomNoeudDep,
			ArrayList<String> listLiens, ArrayList<Sens> listSens, int niveauMax) {
		if (listLiens.size() != listSens.size()) {
			System.out.println("Taille de la liste Sens et la liste Liens différentes");
			return null;
		}
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			int niveau = 0;
			int niveauLastNoeud = 0;
			String lienInitial = listLiens.get(0);
			ArrayList<Noeud> listNoeuds = new ArrayList<Noeud>();
			LinkedList<Noeud> listNoeudsToVisit = new LinkedList<Noeud>();
			ArrayList<Relation> listRelations = noeudDep.getFlux(listSens.get(niveau));
			// Suppression du premier lien et du premier sens
			listLiens.remove(0);
			listSens.remove(0);
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(lienInitial)) {
					listNoeudsToVisit.add(r.getNoeudDestination());
				}
			}
			parcoursLargeurRecur(listNoeudsToVisit.getFirst(), listLiens, listSens, niveauMax,
					niveau, niveauLastNoeud + 1, listNoeudsToVisit.size(), listNoeuds,
					listNoeudsToVisit);
			return listNoeuds;
		}
		return null;
	}

	private static void parcoursLargeurRecur(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveauSens, int niveauLastNoeud,
			int niveauSaut, ArrayList<Noeud> listNoeudsResul, LinkedList<Noeud> listNoeudsToVisit) {
		if (niveauLastNoeud > niveauMax) {
			return;
		}
		if (!listNoeudsResul.contains(noeud)) {
			listNoeudsResul.add(noeud);
		}
		if (listSens.size() == 0) {
			return;
		}
		if (niveauSens >= listSens.size()) {
			niveauSens = listSens.size() - 1;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveauSens));
		for (Relation r : listRelations) {
			if (r.estDuTypes(listLiens) && !listNoeudsResul.contains(r.getNoeudDestination())) {
				// et !listNoeudsToVisit?
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
			parcoursLargeurRecur(listNoeudsToVisit.getFirst(), listLiens, listSens, niveauMax,
					niveauSens + 1, niveauLastNoeud, niveauSaut, listNoeudsResul, listNoeudsToVisit);
		}

	}

	public static void parcoursManuel(Graph g, String nomNoeud) {
		int choix = -1;
		Noeud n = g.getNoeud(nomNoeud);
		while (choix != 0) {
			if (n != null) {
				ArrayList<Relation> listRelation = n.getFluxSortant();
				System.out.println(n.getName() + " relations sortantes : ");
				System.out.println("\t0 : quitter");
				int indRelation = 0;
				ArrayList<String> listeNomRelation = new ArrayList<String>();
				for (Relation r : listRelation) {
					if (!listeNomRelation.contains(r.getName())) {
						indRelation++;
						listeNomRelation.add(r.getName());
						System.out.println("\t" + indRelation + " : " + r.getName());
					}
				}
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				System.out.print("Relation choisie : ");
				try {
					choix = sc.nextInt();
				} catch (Exception e) {
					System.out.println("Erreur de saisie. Choix par defaut : 0");
					choix = 0;
				}
				if (choix < 0 || choix > listeNomRelation.size()) {
					System.out.println("Erreur de saisie. Choix par defaut : 0");
					choix = 0;
				}
				if (choix == 0) {
					break;
				}
				String relationChoisie = listeNomRelation.get(choix - 1);
				ArrayList<Noeud> listNoeud = new ArrayList<Noeud>();
				int indNoeud = 0;
				System.out.println("Noeuds destinations : ");
				System.out.println("\t0 : quitter");
				for (Relation r : listRelation) {
					if (r.getName().equalsIgnoreCase(relationChoisie)) {
						indNoeud++;
						listNoeud.add(r.getNoeudDestination());
						System.out.println("\t" + indNoeud + " : "
								+ r.getNoeudDestination().getName());
					}
				}
				indNoeud++;
				System.out.println("\t" + indNoeud + " : Retour " + n.getName());
				listNoeud.add(n);
				@SuppressWarnings("resource")
				Scanner sc2 = new Scanner(System.in);
				choix = listNoeud.size();
				System.out.print("Noeud choisi : ");
				try {
					choix = sc2.nextInt();
				} catch (Exception e) {
					System.out.println("Erreur de saisie. Choix par defaut : 0");
					choix = 0;
				}
				if (choix < 0 || choix > listNoeud.size()) {
					System.out.println("Erreur de saisie. Choix par defaut : 0");
					choix = 0;
				}
				if (choix == 0) {
					break;
				}
				n = listNoeud.get(choix - 1);
			} else {
				System.out.println("Désolé mais le noeud '" + nomNoeud + "' n'existe pas.");
				choix = 0;
			}
		}
	}
}
