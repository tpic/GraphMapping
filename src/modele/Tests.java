package modele;

import java.util.ArrayList;

public class Tests {

	public static void main(String[] args) {
		Graph g = new Graph();

		Parser p = new Parser(g);
		p.verifFichier("donnees.txt");

		System.out.println(g);
		// Recherche.parcoursManuel(g, "BarBara");
		ArrayList<String> liens = new ArrayList<String>();
		/*liens.add("likes");
		liens.add("friend");*/
		liens.add("L");
		liens.add("F");
		liens.add("E");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		/*sens.add(Sens.IN);
		sens.add(Sens.OUT);*/
		sens.add(Sens.OUT);
		sens.add(Sens.OUT);
		sens.add(Sens.OUT);

		/*// Recherche.parcours(g, "NoSQL Distilled", "longueur", "", liens, 3);
		System.out.println("Recherche en profondeur :");
		for (Noeud n : Recherche.parcoursProfondeur(g, "1", liens, sens, 12)) {
			System.out.println(n.getName());
		}*/

		System.out.println("Recherche en largeur :");
		for (Noeud n : Recherche.parcoursLargeur(g, "1", liens, sens, 25)) {
			System.out.println(n.getName());
		}
	}
}