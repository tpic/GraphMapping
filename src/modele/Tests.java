package modele;

public class Tests {

	public static void main(String[] args) {
		Graph g = new Graph();

		Parser p = new Parser(g);
		p.verifFichier("donnees.txt");

		System.out.println(g);

		// Recherche.parcoursManuel(g, "Barbara");
		new RechercheManuelle().executeSearch(g, "Barbara");
	}
}
