package tests;

import static org.junit.Assert.assertEquals;

import modele.Graph;
import modele.Parser;

import org.junit.Test;


public class ParserTest {

	Parser p = new Parser(new Graph());

	/*
	 * tests de la méthode verifLigne(String)
	 */

	@Test
	public void testVerifLigne1() {
		System.out.println("OK : verifLigne1 -> relation sortante");
		assertEquals(true, p.verifLigne("Barbara--friend[since=1999]-->Carol"));
	}

	@Test
	public void testVerifLigne2() {
		System.out
				.println("OK : verifLigne2 -> un espace entre chaque element");
		assertEquals(true,
				p.verifLigne("Barbara -- friend [ since = 1999 ] --> Carol"));
	}

	@Test
	public void testVerifLigne3() {
		System.out
				.println("OK : verifLigne3 -> plusieurs valeurs à l'argument");
		assertEquals(
				true,
				p.verifLigne("Barbara--friend[share=[books,film,music]]-->Carol"));
	}

	@Test
	public void testVerifLigne4() {
		System.out.println("OK : verifLigne4 -> plusieurs arguments");
		assertEquals(true,
				p.verifLigne("Barbara--friend[since=1999,share=books]-->Carol"));
	}

	@Test
	public void testVerifLigne5() {
		System.out
				.println("OK : verifLigne5 -> plusieurs arguments avec plusieurs valeurs");
		assertEquals(
				true,
				p.verifLigne("Barbara--friend[share=[books,film,music],borrow=[cd,books],friends=[lisa,lola,anna]]-->Carol"));
	}

	@Test
	public void testVerifLigne6() {
		System.out
				.println("OK : verifLigne6 -> plusieurs arguments avec une ou plusieurs valeurs");
		assertEquals(
				true,
				p.verifLigne("Barbara--friend[share=[books,film,music],since=1999,friends=[lisa,lola,anna],met=high_school]-->Carol"));
	}

	@Test
	public void testVerifLigne7() {
		System.out.println("OK : verifLigne7 -> relation entrante");
		assertEquals(true, p.verifLigne("Barbara<--friend[since=1999]--Carol"));
	}

	@Test
	public void testVerifLigne8() {
		System.out.println("OK : verifLigne8 -> relation reciproque v1");
		assertEquals(true, p.verifLigne("Barbara<--friend[since=1999]-->Carol"));
	}

	@Test
	public void testVerifLigne9() {
		System.out.println("OK : verifLigne9 -> relation reciproque v2");
		assertEquals(true, p.verifLigne("Barbara--friend[since=1999]--Carol"));
	}

}
