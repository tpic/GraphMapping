package tests;

import java.util.ArrayList;

import junit.framework.TestCase;

import modele.Graph;
import modele.Parser;
import modele.Recherche;

public class RechercheTest extends TestCase{
	
	ArrayList<String> parcoursProfondeur = new ArrayList<String>();
	ArrayList<String> parcoursLargeur = new ArrayList<String>();
	//ArrayList<String> parcoursProfondeurTest = new ArrayList<String>();
	
	public RechercheTest() {
		
	}
	
	 public void setUp() throws Exception {
		 /*parcoursProfondeurTest.add("Barbara");
		 parcoursProfondeurTest.add("Carol");
		 parcoursProfondeurTest.add("Dawn");
		 parcoursProfondeurTest.add("Anna");
		 parcoursProfondeurTest.add("Elizabeth");
		 parcoursProfondeurTest.add("Jill");
		 parcoursProfondeurTest.add("Bernard");*/
	 }
	 
	 public void tearDown() throws Exception {
		 
	 }
	 
	 public void testParcoursProfondeur() {
		 Graph g = new Graph();
		 Parser p = new Parser(g);
		 p.verifFichier("donnees.txt");
		 ArrayList<String> liens = new ArrayList<String>();
		 liens.add("likes");
		 liens.add("friend");
		 assertEquals(parcoursProfondeur.size(),0);
		 parcoursProfondeur = Recherche.parcours(g, "NoSQL Distilled", "longeur", "", liens, 3);
		 assertEquals(parcoursProfondeur.size(),7);
		 assertEquals(parcoursProfondeur.get(parcoursProfondeur.size()-1),"Bernard");
		 /*Object[] tabParcours = parcoursProfondeur.toArray();
		 Object[] tabParcoursTest = parcoursProfondeurTest.toArray();
		 assertEquals(tabParcours, tabParcoursTest);*/
	 }
	 
	 public void testParcoursLargeur() {
		 
	 }
	 
	 public static void main(String[] args) {
		    // Invoke JUnit on the class:
		    junit.textui.TestRunner.run(RechercheTest.class);
	 }

}
