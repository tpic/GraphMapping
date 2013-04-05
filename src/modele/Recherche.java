package modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Recherche {

	public static ArrayList<Noeud> parcoursProfondeur(Graph g, String nomNoeudDep,
			ArrayList<String> listLiens, ArrayList<Sens> listSens, int niveauMax) {
		if (listLiens.size() != listSens.size()) {
			System.out.println("Taille de la liste Sens et la liste Liens différentes");
			return null;
		}
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			System.out.print("Recherche en profondeur : \n\t Noeud de depart : "
					+ noeudDep.getName() + ", lien(s) : ");
			for (int i = 0; i < listLiens.size(); i++) {
				System.out.print(listLiens.get(i) + " (" + listSens.get(i) + ") ");
			}
			System.out.println("Niveau max : " + niveauMax);
			int niveau = 0;
			int niveauLastNoeud = 0;
			String lienInitial = listLiens.get(0);
			// Suppression du premier lien
			listLiens.remove(0);
			ArrayList<Noeud> listNoeuds = new ArrayList<Noeud>();
			ArrayList<Relation> listRelations = noeudDep.getFlux(listSens.get(niveau));
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(lienInitial)) {
					parcoursProfondeurRecur(r.getNoeudDestination(), listLiens, listSens,
							niveauMax, niveau + 1, niveauLastNoeud + 1, listNoeuds);
				}
			}
			for (Noeud n : listNoeuds) {
				System.out.println(n.getName());
			}
			return listNoeuds;
		}
		System.out.println("Recherche impossible. Noeud '" + nomNoeudDep + "' est inconnu");
		return null;
	}

	private static void parcoursProfondeurRecur(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveau, int niveauLastNoeud,
			ArrayList<Noeud> listNoeudsResul) {
		if (niveau != 0 && !listNoeudsResul.contains(noeud)) {
			// on est au niveau feuille interessant
			listNoeudsResul.add(noeud);
		}
		if (niveauLastNoeud == niveauMax) {
			return;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveau));
		for (Relation r : listRelations) {
			if (r.estDuTypes(listLiens)) {
				System.out.println(noeud);
				parcoursProfondeurRecur(r.getNoeudDestination(), listLiens, listSens, niveauMax,
						niveau + 1, niveauLastNoeud + 1, listNoeudsResul);
			}
		}
	}

	public static LinkedList<Noeud> parcoursLargeur(Graph g, String nomNoeudDep,
			ArrayList<String> listLiens, ArrayList<Sens> listSens, int niveauMax) {
		if (listLiens.size() != listSens.size()) {
			System.out.println("Taille de la liste Sens et la liste Liens différentes");
			return null;
		}
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			System.out.print("Recherche en largeur : \n\t Noeud de depart : " + noeudDep.getName()
					+ ", lien(s) : ");
			for (int i = 0; i < listLiens.size(); i++) {
				System.out.print(listLiens.get(i) + " (" + listSens.get(i) + ") ");
			}
			System.out.println("Niveau max : " + niveauMax);
			int niveau = 0;
			int niveauLastNoeud = 0;
			String lienInitial = listLiens.get(0);
			// Suppression du premier lien
			listLiens.remove(0);
			LinkedList<Noeud> listNoeuds = new LinkedList<Noeud>();
			ArrayList<Relation> listRelations = noeudDep.getFlux(listSens.get(niveau));
			for (Relation r : listRelations) {
				if (r.getName().equalsIgnoreCase(lienInitial)) {
					parcoursLargeurRecur(r.getNoeudDestination(), listLiens, listSens, niveauMax,
							niveau + 1, niveauLastNoeud + 1, listNoeuds);
				}
			}
			for (Noeud n : listNoeuds) {
				System.out.println(n.getName());
			}
			return listNoeuds;
		}
		System.out.println("Recherche impossible. Noeud '" + nomNoeudDep + "' est inconnu");
		return null;
	}

	private static void parcoursLargeurRecur(Noeud noeud, ArrayList<String> listLiens,
			ArrayList<Sens> listSens, int niveauMax, int niveau, int niveauLastNoeud,
			ArrayList<Noeud> listNoeudsResul) {
		if (niveau != 0 && !listNoeudsResul.contains(noeud)) {
			// on est au niveau feuille interessant
			listNoeudsResul.add(noeud);
		}
		if (niveauLastNoeud == niveauMax) {
			return;
		}
		ArrayList<Relation> listRelations = noeud.getFlux(listSens.get(niveau));
		for (Relation r : listRelations) {
			if (r.estDuTypes(listLiens)) {
				System.out.println(noeud);
				parcoursProfondeurRecur(r.getNoeudDestination(), listLiens, listSens, niveauMax,
						niveau + 1, niveauLastNoeud + 1, listNoeudsResul);
			}
		}
	}

	private static/*
						private static void parcoursLargeur(Noeud noeud, ArrayList<String> liens, int niveauMax,
								int niveau, ArrayList<Noeud> nomNoeuds) {
							// TODO A revoir
							if (niveau == niveauMax) {
								return;
							}
							ArrayList<Relation> relationEntrante = noeud.getFluxSortant();
							for (Relation r : relationEntrante) {
								if (r.getName().equalsIgnoreCase(liens.get(niveau))) {
									if (niveau != 0) {
										if (!nomNoeuds.contains(r.getNoeudDestination())) {
											nomNoeuds.add(r.getNoeudDestination());
											System.out.println(r.getNoeudDestination().getName());
										}
									}
								}
							}
							if (niveau < liens.size() - 1) {
								niveau++;
							}
							int i = 0;
							while (i < nomNoeuds.size()) {
								parcoursLargeur(nomNoeuds.get(i), liens, niveauMax, niveau, nomNoeuds);
								i++;
								niveau++;
							}
						}
					*/
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
