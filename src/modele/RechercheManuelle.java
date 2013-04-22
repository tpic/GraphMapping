package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class RechercheManuelle {

	public ArrayList<Noeud> executeSearch(Graph g, String nomNoeud) {
		int choix = -1;
		Noeud n = g.getNoeud(nomNoeud);
		while (choix != 0) {
			if (n != null) {
				ArrayList<Relation> listRelation = n.getFluxSortant();
				System.out.println("'" + n.getName() + "', relations sortantes : ");
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
				// choix = listNoeud.size();
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
		return null;
	}

	public static void parcoursManuel(Graph g, String nomNoeud) {

	}
}
