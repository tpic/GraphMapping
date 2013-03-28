import java.util.ArrayList;

public class Tests {

	public static void main(String[] args) {
		Graph g = new Graph();

		Parser p = new Parser(g);
		p.verifFichier("donnees.txt");

		System.out.println(g);
		// Recherche.parcoursManuel(g, "BarBara");
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("author");
		liens.add("friend");

		Recherche.parcours(g, "NoSQL Distilled", "profondeur", "", liens, 1);
	}
}
