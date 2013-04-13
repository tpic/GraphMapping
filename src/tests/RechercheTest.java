package tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import modele.Graph;
import modele.Noeud;
import modele.Parser;
import modele.Recherche;
import modele.Sens;

public class RechercheTest extends TestCase {

	private Graph g;
	private Parser p;

	public void setUp() throws Exception {
		g = new Graph();
		p = new Parser(g);
		p.verifFichier("donnees.txt");
	}

	public void tearDown() throws Exception {

	}

	public void testParcoursProfondeurNoeudGLobal0() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Carol");
		listNom.add("Dawn");
		listNom.add("Anna");
		listNom.add("Elizabeth");
		listNom.add("Jill");
		listNom.add("Bernard");

		ArrayList<Noeud> listNode = Recherche.parcoursProfondeur(g,
				"NoSQL Distilled", liens, sens, 0, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNom.size(); i++) {
			assertEquals(true, listNom.contains(listNode.get(i).getName()));
		}
	}

	public void testParcoursProfondeurNoeudGLobal1() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Bernard");
		listNom.add("Carol");
		listNom.add("Dawn");
		listNom.add("Elizabeth");

		ArrayList<Noeud> listNode = Recherche.parcoursProfondeur(g,
				"NoSQL Distilled", liens, sens, 1, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNode.size(); i++) {
			assertEquals(listNom.get(i), listNode.get(i).getName());
		}
	}

	public void testParcoursProfondeurNoeudGLobal2() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Carol");
		listNom.add("Anna");
		listNom.add("Elizabeth");
		listNom.add("Bernard");
		listNom.add("Dawn");
		listNom.add("Jill");

		ArrayList<Noeud> listNode = Recherche.parcoursProfondeur(g,
				"NoSQL Distilled", liens, sens, 2, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNode.size(); i++) {
			assertEquals(listNom.get(i), listNode.get(i).getName());

		}
	}

	public void testParcoursProfondeurNoeudGLobal3() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Carol");
		listNom.add("Dawn");
		listNom.add("Anna");
		listNom.add("Elizabeth");
		listNom.add("Jill");
		listNom.add("Bernard");

		ArrayList<Noeud> listNode = Recherche.parcoursProfondeur(g,
				"NoSQL Distilled", liens, sens, 3, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNom.size(); i++) {
			assertEquals(listNom.get(i), listNode.get(i).getName());
		}
	}

	public void testParcoursProfondeurRelationGlobal1() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("friend");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.INOUT);
		sens.add(Sens.INOUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Carol");
		listNom.add("Anna");
		listNom.add("Elizabeth");
		listNom.add("Jill");
		listNom.add("Dawn");

		ArrayList<Noeud> listNode = Recherche.parcoursProfondeur(g, "Carol",
				liens, sens, 0, true);

		for (Noeud n : listNode) {
			System.out.println(n);
		}
		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNom.size(); i++) {
			assertEquals(true, listNom.contains(listNode.get(i).getName()));
		}
	}

	public void testParcoursLargeurNoeudGLobal0() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Bernard");
		listNom.add("Carol");
		listNom.add("Dawn");
		listNom.add("Elizabeth");
		listNom.add("Anna");
		listNom.add("Jill");

		ArrayList<Noeud> listNode = Recherche.parcoursLargeur(g,
				"NoSQL Distilled", liens, sens, 0, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNode.size(); i++) {
			assertEquals(listNom.get(i), listNode.get(i).getName());
		}
	}

	public void testParcoursLargeurNoeudGLobal1() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Bernard");
		listNom.add("Carol");
		listNom.add("Dawn");
		listNom.add("Elizabeth");

		ArrayList<Noeud> listNode = Recherche.parcoursLargeur(g,
				"NoSQL Distilled", liens, sens, 1, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNode.size(); i++) {
			assertEquals(listNom.get(i), listNode.get(i).getName());
		}
	}

	public void testParcoursLargeurNoeudGLobal2() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Bernard");
		listNom.add("Carol");
		listNom.add("Dawn");
		listNom.add("Elizabeth");
		listNom.add("Anna");
		listNom.add("Jill");

		ArrayList<Noeud> listNode = Recherche.parcoursLargeur(g,
				"NoSQL Distilled", liens, sens, 2, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNode.size(); i++) {
			assertEquals(listNom.get(i), listNode.get(i).getName());
		}
	}

	public void testParcoursLargeurNoeudGLobal3() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("likes");
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.IN);
		sens.add(Sens.OUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Bernard");
		listNom.add("Carol");
		listNom.add("Dawn");
		listNom.add("Elizabeth");
		listNom.add("Anna");
		listNom.add("Jill");

		ArrayList<Noeud> listNode = Recherche.parcoursLargeur(g,
				"NoSQL Distilled", liens, sens, 9, false);

		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNode.size(); i++) {
			assertEquals(listNom.get(i), listNode.get(i).getName());
		}
	}

	public void testParcoursLargeurRelationGlobal0() {
		ArrayList<String> liens = new ArrayList<String>();
		liens.add("friend");
		ArrayList<Sens> sens = new ArrayList<Sens>();
		sens.add(Sens.INOUT);
		ArrayList<String> listNom = new ArrayList<String>();
		listNom.add("Barbara");
		listNom.add("Carol");
		listNom.add("Anna");
		listNom.add("Elizabeth");
		listNom.add("Jill");
		listNom.add("Dawn");

		ArrayList<Noeud> listNode = Recherche.parcoursProfondeur(g, "Carol",
				liens, sens, 0, true);

		for (Noeud n : listNode) {
			System.out.println(n);
		}
		assertEquals(listNom.size(), listNode.size());
		for (int i = 0; i < listNom.size(); i++) {
			assertEquals(true, listNom.contains(listNode.get(i).getName()));
		}

	}

}
