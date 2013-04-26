package tests;

import junit.framework.TestCase;
import modele.Graph;
import modele.Noeud;
import modele.Parser;
import modele.Relation;
import modele.Sens;

public class StructureTest extends TestCase {

	Graph g = new Graph();
	Parser p = new Parser(g);

	public StructureTest() {

	}

	public void setUp() throws Exception {

	}

	public void tearDown() throws Exception {

	}

	/*
	 * Test des méthodes de la classe Noeud
	 */
	public void testAddFlux() {
		Noeud n = new Noeud("NoeudTestOrig");
		Noeud n1 = new Noeud("NoeudTestDest");
		Relation r = new Relation("RelationTest", n, n1, modele.Sens.IN);
		n.addFlux(r);
		assertEquals(n.getFluxEntrant().get(0), r);
	}

	public void testVerifDoublon() {
		Noeud n = new Noeud("NoeudTestOrig");
		Noeud n1 = new Noeud("NoeudTestDest");
		Relation r = new Relation("RelationTest", n, n1, modele.Sens.IN);
		Relation r1 = new Relation("RelationTest", n, n1, modele.Sens.IN);
		n.addFlux(r);
		assertEquals(n.verifDoublon(r1), true);
	}

	public void testVerifDoublon2() {
		Noeud n1 = g.addOrGetNoeud("Barbara");
		Noeud n2 = g.addOrGetNoeud("Anna");
		Sens sens = Sens.INOUT;
		g.addRelation(n1, new Relation("friend", n1, n2, sens), n2);
		assertEquals(true, n1.verifDoublon(new Relation("friend", n1, n2, sens)));
	}

	public static void main(String[] args) {
		// Invoke JUnit on the class:
		junit.textui.TestRunner.run(RechercheTest.class);
	}

}
