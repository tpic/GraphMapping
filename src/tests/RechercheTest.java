package tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import modele.Graph;
import modele.Parser;
import modele.Recherche;

public class RechercheTest extends TestCase {

	ArrayList<String> parcoursProfondeur = new ArrayList<String>();
	ArrayList<String> parcoursLargeur = new ArrayList<String>();

	Graph g = new Graph();
	Parser p = new Parser(g);

	public RechercheTest() {

	}

	public void setUp() throws Exception {

	}

	public void tearDown() throws Exception {

	}

	public void testParcoursProfondeur() {

		p.verifFichier("donnees.txt");
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		assertEquals(parcoursProfondeur.size(), 0);
		parcoursProfondeur = Recherche.parcours(g, "NoSQL Distilled",
				"longeur", "", liens, 3);
		assertEquals(parcoursProfondeur.size(), 7);
		assertEquals(parcoursProfondeur.get(parcoursProfondeur.size() - 1),
				"Bernard");

	}

	public void testParcoursLargeur() {

	}

	public static void main(String[] args) {
		// Invoke JUnit on the class:
		junit.textui.TestRunner.run(RechercheTest.class);
	}

}
