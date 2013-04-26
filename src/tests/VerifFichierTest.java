package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import modele.Graph;
import modele.Parser;

import org.junit.Test;

public class VerifFichierTest {

	private Parser p = new Parser(new Graph());

	@Test
	public void testVerifFichier() {
		String file = "donnees.txt";
		try {
			assertTrue(p.verifFichier(file));
		} catch (IOException e) {
			fail();
		}
	}
}
