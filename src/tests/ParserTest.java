package tests;

import static org.junit.Assert.assertEquals;
import modele.Graph;
import modele.Parser;

import org.junit.Test;

public class ParserTest {

	Parser p = new Parser(new Graph());

	/*
	 * tests de la méthode verifFichier(String)
	 */
	/*
	 * @Test public void testVerifFichier() { String file = "donnees.txt";
	 * System.out.println("VerifFichier, param : nom du fichier");
	 * assertEquals(true, p.verifFichier(file)); }
	 */
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
				.println("NOT OK : verifLigne11 -> deux points à la place du signe égal");
		assertEquals(
				false,
				p.verifLigne("Anthony--likes[version=[films VF, films VO, films VOSTFr], type:[action, comedy]]-->cinema"));
	}

	@Test
	public void testVerifLigne12() {
		System.out
				.println("NOT OK : verifLigne12 -> Aucune valeur après une virgule (1)");
		assertEquals(
				false,
				p.verifLigne("Marianne<--friend[since=2011, share[musik, books, ]-->Thomas"));
	}

	@Test
	public void testVerifLigne13() {
		System.out
				.println("NOT OK : verifLigne13 -> Aucune valeur après une virgule (2)");
		assertEquals(
				false,
				p.verifLigne("Marianne<--friend[since=2011, share[musik, books], -->Thomas"));
	}

	@Test
	public void testVerifLigne14() {
		System.out.println("NOT OK : verifLigne14 -> Caractère special");
		assertEquals(
				false,
				p.verifLigne("Thomas--employee_of[role=developer, hired=Mar 13\\\"]-->Bull"));
	}

	@Test
	public void testVerifLigne15() {
		System.out
				.println("NOT OK : verifLigne15 -> Aucun attribut dans les crochets");
		assertEquals(false, p.verifLigne("TopChef--category[]-->TV"));
	}

	@Test
	public void testVerifLigne16() {
		System.out
				.println("NOT OK : verifLigne16 -> Aucune valeur dans l'attribut");
		assertEquals(false,
				p.verifLigne("Florence--friend[since=2011, share=]-->Marianne"));
	}

	@Test
	public void testVerifLigne17() {
		System.out
				.println("NOT OK : verifLigne17 -> seulement un trait dans la flèche ");
		assertEquals(false, p.verifLigne("Kristina--likes->Allemagne"));
	}

	@Test
	public void testVerifLigne18() {
		System.out
				.println("NOT OK : verifLigne18 -> deux points a la place du signe égal");
		assertEquals(
				false,
				p.verifLigne("Anthony--likes[version=[films VF, films VO, films VOSTFr], type:[action, comedy]]-->cinema"));
	}

	@Test
	public void testVerifLigne19() {
		System.out.println("NOT OK : verifLigne19 -> caractère special");
		assertEquals(
				false,
				p.verifLigne("Anthony--employe_of[role=project manager*, hired=mar 13]-->Orange"));
	}

	@Test
	public void testVerifLigne20() {
		System.out
				.println("NOT OK : verifLigne20 -> espace entre les tirets du lien ");
		assertEquals(
				false,
				p.verifLigne("Anthony<- -friend[since=2011, share=[games, fb, serie] ]-->Marianne"));
	}

	@Test
	public void testVerifLigne21() {
		System.out
				.println("OK : verifLigne21 -> om de noeud ou de relation avec des underscore");
		assertEquals(true,
				p.verifLigne("under_score--best_friend-->back_slash"));
	}

	@Test
	public void testVerifLigne22() {
		System.out
				.println("NOT OK : verifLigne22 -> mettre plus d'un espace avant, apres et dans les noms de noeud ou de relation ");
		assertEquals(false, p.verifLigne("Barbara  --friend --> Anna"));
	}

	@Test
	public void testVerifLigne23() {
		System.out
				.println("NOT OK : verifLigne23 -> crochets pour la valeur d'un attribut mais avec une seule valeur dedans ");
		assertEquals(false,
				p.verifLigne("Barbara--friend[since=[1999]]-->Anna"));
	}

	@Test
	public void testVerifLigne24() {
		System.out
				.println("NOT OK : verifLigne21 -> Aucune valeur dans les crochets ");
		assertEquals(false,
				p.verifLigne("Thomas<--friend[since=2009, share=[]]-->Anthony"));
	}
}
