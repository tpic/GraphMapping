package modele;
import java.util.ArrayList;
import java.util.Scanner;

public class Recherche {

	public static void parcours(Graph g, String nomNoeudDep, String mode, String sens,
			ArrayList<String> liens, int niveauMax) {
		Noeud noeudDep = g.getNoeud(nomNoeudDep);
		if (noeudDep != null) {
			ArrayList<String> listNomNoeud = new ArrayList<>();
			ArrayList<Noeud> listNoeuds = new ArrayList<>();
			System.out.print("Noeud de depart : " + noeudDep.getName() + ", recherche : " + mode
					+ " sens : '" + sens + "' lien(s) : ");
			for (String lien : liens) {
				System.out.print(lien + ", ");
			}
			System.out.println("niveau max : " + niveauMax);
			int niveau = 0;
			ArrayList<Relation> listRelations = null;
			if (sens.equals("<")) {
				listRelations = noeudDep.getFluxEntrant();
			} else if (sens.equals(">")) {
				listRelations = noeudDep.getFluxSortant();
			} else {
				listRelations = noeudDep.getFluxEntrant();
				listRelations.addAll(noeudDep.getFluxSortant());
			}
			boolean donnee = false;
			if (mode.equalsIgnoreCase("largeur")) {
				// TODO Bancale
				for (Relation r : listRelations) {
					niveau = 0;
					if (r.getName().equalsIgnoreCase(liens.get(niveau))) {
						niveau = 1;
						donnee = true;
						parcoursLargeur(r.getNoeudDestination(), liens, niveauMax, niveau,
								listNoeuds);
					}
				}
				for (Noeud n : listNoeuds) {
					System.out.println(n.getName());
				}
			} else {
				for (Relation r : listRelations) {
					niveau = 0;
					if (r.getName().equalsIgnoreCase(liens.get(niveau))) {
						niveau = 1;
						donnee = true;
						parcoursProfondeur(r.getNoeudDestination(), liens, niveauMax, niveau,
								listNomNoeud);
					}
				}
				// Voir pour retourner une arrayList ou tab pour les tests
				// unitaire
				for (String s : listNomNoeud) {
					System.out.println(s);
				}
			}
			if (!donnee) {
				System.out.println("Aucun noeud ne correspond à votre recherche");
			}
		} else {
			System.out.println("Noeud " + nomNoeudDep + " inconnu");
		}
	}

	private static void parcoursProfondeur(Noeud noeud, ArrayList<String> liens, int niveauMax,
			int niveau, ArrayList<String> nomNoeuds) {
		if (niveau != 0) {
			if (!nomNoeuds.contains(noeud.getName())) {
				nomNoeuds.add(noeud.getName());
			}
		}
		if (niveau == niveauMax) {
			return;
		}
		ArrayList<Relation> relationEntrante = noeud.getFluxSortant();
		int nivChoix = 1;
		if (niveau < liens.size() - 1) {
			nivChoix++;
		}
		for (Relation r : relationEntrante) {
			if (r.getName().equalsIgnoreCase(liens.get(nivChoix))) {
				niveau++;
				parcoursProfondeur(r.getNoeudDestination(), liens, niveauMax, niveau, nomNoeuds);
			}
		}
	}

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
						// System.out.println(r.getNoeudDestination().getName());
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
				ArrayList<String> listeNomRelation = new ArrayList<>();
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
