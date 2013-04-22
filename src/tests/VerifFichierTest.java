package tests;

import static org.junit.Assert.assertTrue;
import modele.Graph;
import modele.Parser;

import org.junit.Test;

public class VerifFichierTest {

	private Parser p = new Parser(new Graph());

	@Test
	public void testVerifFichier() {
		String file = "donnees.txt";
		assertTrue(p.verifFichier(file));
	}

}
