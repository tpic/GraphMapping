package tests;

import modele.Noeud;
import modele.Relation;
import junit.framework.TestCase;

public class StructureTest extends TestCase{
	
	public StructureTest(){
		
	}
	
	
	public void setUp() throws Exception {
		 
	}
	 
	public void tearDown() throws Exception {
		 
	}
	
	/*
	 * Test des méthodes de la classe Noeud
	 */
	public void testAddFlux(){
		Noeud n = new Noeud("NoeudTest");
		Relation r = new Relation("RelationTest", n, modele.Sens.IN);
		n.addFlux(r);
		assertEquals(n.getFluxEntrant().get(0), r);
	}
	
	public void testVerifDoublon(){
		Noeud n = new Noeud("NoeudTest");
		Relation r = new Relation("RelationTest", n, modele.Sens.IN);
		Relation r1 = new Relation("RelationTest", n, modele.Sens.IN);
		n.addFlux(r);
		assertEquals(n.verifDoublon(r1), true);
	}
	
	/*
	 * Test des méthodes de la classe Relation
	 */
	public void testAddAttribut(){
		
	}
	
	
	
	public static void main(String[] args) {
	    // Invoke JUnit on the class:
	    junit.textui.TestRunner.run(RechercheTest.class);
 }
	
}
