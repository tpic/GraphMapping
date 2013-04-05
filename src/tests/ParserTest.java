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

	@Test
	public void testVerifLigne10() {
		System.out.println("OK : verifLigne10 -> accents");
		assertEquals(true, p.verifLigne("Rémi--frère-->Jérôme"));
	}

	@Test
	public void testVerifLigne11() {
		System.out
				.println("NOT OK : verifLigne11 -> deux points a la place de égal");
		assertEquals(
				false,
				p.verifLigne("Anthony--likes[version=[films VF, films VO, films VOSTFr], type:[action, comedy]]-->cinema"));
	}

	@Test
	public void testVerifLigne12() {
		System.out.println("NOT OK : verifLigne12 -> rien apres la virgule");
		assertEquals(
				false,
				p.verifLigne("Marianne<--friend[since=2011, share[musik, books, ]-->Thomas"));
	}

	@Test
	public void testVerifLigne13() {
		System.out.println("NOT OK : verifLigne13 -> guillemets");
		assertEquals(
				false,
				p.verifLigne("Thomas--employee_of[role=developer, hired=Mar 13\"]-->Bull"));
	}

	@Test
	public void testVerifLigne14() {
		System.out.println("NOT OK : verifLigne14 -> rien dans les crochets");
		assertEquals(false, p.verifLigne("TopChef--category[]-->TV"));
	}

	@Test
	public void testVerifLigne15() {
		System.out.println("NOT OK : verifLigne15 -> rien apres egal ");
		assertEquals(false,
				p.verifLigne("Florence--friend[since=2011, share=]-->Marianne"));
	}

	@Test
	public void testVerifLigne16() {
		System.out
				.println("NOT OK : verifLigne16 -> seulement un trait dans la flèche ");
		assertEquals(false, p.verifLigne("Kristina--likes->Allemagne"));
	}

	@Test
	public void testVerifLigne17() {
		System.out
				.println("NOT OK : verifLigne17 -> deux points a la place de egal ");
		assertEquals(
				false,
				p.verifLigne("Anthony--likes[version=[films VF, films VO, films VOSTFr], type:[action, comedy]]-->cinema"));
	}

	@Test
	public void testVerifLigne18() {
		System.out.println("NOT OK : verifLigne18 -> carac speciaux ");
		assertEquals(
				false,
				p.verifLigne("Anthony--employe_of[role=project manager*, hired=mar 13]-->Orange"));
	}

	@Test
	public void testVerifLigne19() {
		System.out
				.println("NOT OK : verifLigne19 -> espace entre les tirets du lien ");
		assertEquals(
				false,
				p.verifLigne("Anthony<- -friend[since=2011, share=[games, fb, serie] ]-->Marianne"));
	}

	@Test
	public void testVerifLigne20() {
		System.out.println("NOT OK : verifLigne20 -> rien dans les crochets ");
		assertEquals(false,
				p.verifLigne("Thomas<--friend[since=2009, share=[]]-->Anthony"));
	}

}
