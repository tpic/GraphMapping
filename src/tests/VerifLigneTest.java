package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import modele.Graph;
import modele.Parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class VerifLigneTest {

	private String inputString;
	private Boolean expectedResult;
	private Parser p = new Parser(new Graph());

	public VerifLigneTest(String inputString, Boolean expectedResult) {
		this.inputString = inputString;
		this.expectedResult = expectedResult;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{ "Barbara--friend[since=1999]-->Carol", true },
						{ "Barbara -- friend [ since = 1999 ] --> Carol", true },
						{ "Barbara--friend[share=[books,film,music]]-->Carol",
								true },
						{ "Barbara--friend[since=1999,share=books]-->Carol",
								true },
						{
								"Barbara--friend[share=[books,film,music],borrow=[cd,books],friends=[lisa,lola,anna]]-->Carol",
								true },
						{
								"Barbara--friend[share=[books,film,music],since=1999,friends=[lisa,lola,anna],met=high_school]-->Carol",
								true },
						{ "Barbara<--friend[since=1999]--Carol", true },
						{ "Barbara<--friend[since=1999]-->Carol", true },
						{ "Barbara--friend[since=1999]--Carol", true },
						{ "Rémi--frère-->Jérôme", true },
						{ "under_score--best_friend-->back_slash", true },
						{
								"Anthony--likes[version=[films VF, films VO, films VOSTFr], type:[action, comedy]]-->cinema",
								false },
						{
								"Marianne<--friend[since=2011, share[musik, books, ]-->Thomas",
								false },
						{
								"Marianne<--friend[since=2011, share[musik, books], -->Thomas",
								false },
						{ "TopChef--category[]-->TV", false },
						{ "Florence--friend[since=2011, share=]-->Marianne",
								false },
						{ "Kristina--likes->Allemagne", false },
						{
								"Anthony--likes[version=[films VF, films VO, films VOSTFr], type:[action, comedy]]-->cinema",
								false },
						{
								"Anthony<- -friend[since=2011, share=[games, fb, serie] ]-->Marianne",
								false },
						{ "Barbara --  friend --> Anna", false },
						{ "Barbara--friend[since=[1999]]-->Anna", false },
						{ "Thomas<--friend[since=2009, share=[]]-->Anthony",
								false } });
	}

	@Test
	public void testVerifLigne() {
		System.out.println("Test de la ligne : " + inputString);
		assertEquals(expectedResult, p.verifLigne(inputString));
	}
}
