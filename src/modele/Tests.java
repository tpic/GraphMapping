package modele;

import java.io.IOException;

public class Tests {

	public static void main(String[] args) {
		Graph g = new Graph();

		Parser p = new Parser(g);
		try {
			p.verifFichier("donnees.txt");
		} catch (IOException e) {
			System.out.println("Erreur lors du chargement du fichier.");
			System.out.println(e.getMessage());
			System.exit(0);
		}

		System.out.println(g);

		// Recherche.parcoursManuel(g, "Barbara");
		new RechercheManuelle().executeSearch(g, "Barbara");
	}
}
