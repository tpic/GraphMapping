package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;
import modele.Filtre;
import modele.Graph;
import modele.Noeud;
import modele.Parser;
import modele.Recherche;
import modele.Sens;
import modele.TypeRecherche;
import modele.TypeUnicite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class RechercheTest extends TestCase {

	private Graph g = new Graph();
	private Parser p = new Parser(g);
	private String nomFichier = "donnees.txt";
	private String nomNoeud;
	private ArrayList<Filtre> listFiltres;
	private int profondeur;
	private TypeRecherche tRecherche;
	private TypeUnicite tUnicite;
	private ArrayList<String> listNomAttendu;

	@SuppressWarnings("serial")
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "donnees.txt", "NoSQL Distilled", new ArrayList<Filtre>() {
					{
						add(new Filtre("likes", Sens.IN));
						add(new Filtre("friend", Sens.OUT));
					}
				}, Recherche.ALL_GRAPH, TypeRecherche.PROFONDEUR, TypeUnicite.NOEUD_GLOBAL,
						new ArrayList<String>() {
							{
								add("Barbara");
								add("Carol");
								add("Dawn");
								add("Jill");
								add("Anna");
								add("Elizabeth");
								add("Bernard");
							}
						} },
				{ "donnees.txt", "NoSQL Distilled", new ArrayList<Filtre>() {
					{
						add(new Filtre("likes", Sens.IN));
						add(new Filtre("friend", Sens.OUT));
					}
				}, 1, TypeRecherche.PROFONDEUR, TypeUnicite.NOEUD_GLOBAL, new ArrayList<String>() {
					{
						add("Barbara");
						add("Bernard");
						add("Carol");
						add("Dawn");
						add("Elizabeth");
					}
				} },
				{ "donnees.txt", "NoSQL Distilled", new ArrayList<Filtre>() {
					{
						add(new Filtre("likes", Sens.IN));
						add(new Filtre("friend", Sens.OUT));
					}
				}, 2, TypeRecherche.PROFONDEUR, TypeUnicite.NOEUD_GLOBAL, new ArrayList<String>() {
					{
						add("Barbara");
						add("Carol");
						add("Anna");
						add("Elizabeth");
						add("Bernard");
						add("Dawn");
						add("Jill");
					}
				} },
				{ "donnees.txt", "NoSQL Distilled", new ArrayList<Filtre>() {
					{
						add(new Filtre("likes", Sens.IN));
						add(new Filtre("friend", Sens.OUT));
					}
				}, 3, TypeRecherche.PROFONDEUR, TypeUnicite.NOEUD_GLOBAL, new ArrayList<String>() {
					{
						add("Barbara");
						add("Carol");
						add("Dawn");
						add("Anna");
						add("Elizabeth");
						add("Jill");
						add("Bernard");
					}
				} },
				{ "donnees.txt", "Carol", new ArrayList<Filtre>() {
					{
						add(new Filtre("friend"));
					}
				}, Recherche.ALL_GRAPH, TypeRecherche.PROFONDEUR, TypeUnicite.RELATION_GLOBAL,
						new ArrayList<String>() {
							{
								add("Barbara");
								add("Carol");
								add("Dawn");
								add("Jill");
								add("Elizabeth");
								add("Anna");
							}
						} },
				{ "donnees.txt", "NoSQL Distilled", new ArrayList<Filtre>() {
					{
						add(new Filtre("likes", Sens.IN));
						add(new Filtre("friend", Sens.OUT));
					}
				}, Recherche.ALL_GRAPH, TypeRecherche.LARGEUR, TypeUnicite.NOEUD_GLOBAL,
						new ArrayList<String>() {
							{
								add("Barbara");
								add("Bernard");
								add("Carol");
								add("Dawn");
								add("Elizabeth");
								add("Anna");
								add("Jill");
							}
						} },
				{ "donnees.txt", "NoSQL Distilled", new ArrayList<Filtre>() {
					{
						add(new Filtre("likes", Sens.IN));
						add(new Filtre("friend", Sens.OUT));
					}
				}, 1, TypeRecherche.LARGEUR, TypeUnicite.NOEUD_GLOBAL, new ArrayList<String>() {
					{
						add("Barbara");
						add("Bernard");
						add("Carol");
						add("Dawn");
						add("Elizabeth");
					}
				} },
				{ "donnees.txt", "NoSQL Distilled", new ArrayList<Filtre>() {
					{
						add(new Filtre("likes", Sens.IN));
						add(new Filtre("friend", Sens.OUT));
					}
				}, 2, TypeRecherche.LARGEUR, TypeUnicite.NOEUD_GLOBAL, new ArrayList<String>() {
					{
						add("Barbara");
						add("Bernard");
						add("Carol");
						add("Dawn");
						add("Elizabeth");
						add("Anna");
						add("Jill");
					}
				} },
				{ "donnees.txt", "Carol", new ArrayList<Filtre>() {
					{
						add(new Filtre("friend"));
					}
				}, Recherche.ALL_GRAPH, TypeRecherche.LARGEUR, TypeUnicite.RELATION_GLOBAL,
						new ArrayList<String>() {
							{
								add("Barbara");
								add("Dawn");
								add("Anna");
								add("Elizabeth");
								add("Jill");
							}
						} },
				{ "donnees2.txt", "1", new ArrayList<Filtre>() {
					{
						add(new Filtre("L"));
						add(new Filtre("F"));
					}
				}, Recherche.ALL_GRAPH, TypeRecherche.LARGEUR, TypeUnicite.NOEUD_GLOBAL,
						new ArrayList<String>() {
							{
								add("2");
								add("3");
								add("4");
								add("7");
								add("8");
								add("13");
								add("12");
								add("9");
							}
						} },
				{ "donnees2.txt", "1", new ArrayList<Filtre>() {
					{
						add(new Filtre("L"));
						add(new Filtre("F"));
						add(new Filtre("E"));
					}
				}, 4, TypeRecherche.LARGEUR, TypeUnicite.RELATION_GLOBAL, new ArrayList<String>() {
					{
						add("2");
						add("3");
						add("4");
						add("7");
						add("6");
						add("8");
						add("9");
						add("13");
						add("11");
						add("12");
						add("10");
					}
				} },
				{ "donnees2.txt", "1", new ArrayList<Filtre>() {
					{
						add(new Filtre("L"));
						add(new Filtre("F"));
						add(new Filtre("E"));
					}
				}, 4, TypeRecherche.PROFONDEUR, TypeUnicite.NOEUD_GLOBAL, new ArrayList<String>() {
					{
						add("2");
						add("7");
						add("11");
						add("6");
						add("3");
						add("8");
						add("12");
						add("9");
						add("13");
						add("4");
					}
				} },
				{ "donnees2.txt", "1", new ArrayList<Filtre>() {
					{
						add(new Filtre("L"));
						add(new Filtre("F"));
						add(new Filtre("E"));
					}
				}, 5, TypeRecherche.PROFONDEUR, TypeUnicite.RELATION_GLOBAL,
						new ArrayList<String>() {
							{
								add("2");
								add("7");
								add("11");
								add("6");
								add("3");
								add("8");
								add("12");
								add("9");
								add("4");
								add("10");
								add("13");
							}
						} } };
		return Arrays.asList(data);
	}

	public RechercheTest(String nomFichier, String nomNoeud, ArrayList<Filtre> listFiltres,
			int profondeur, TypeRecherche tRecherche, TypeUnicite tUnicite,
			ArrayList<String> listNomAttendu) {
		p.verifFichier(nomFichier);
		this.nomNoeud = nomNoeud;
		this.listFiltres = listFiltres;
		this.profondeur = profondeur;
		this.tRecherche = tRecherche;
		this.tUnicite = tUnicite;
		this.listNomAttendu = listNomAttendu;
	}

	@Test
	public void testRecherche() {

		System.out.println("Noeud de depart : " + nomNoeud + "\n Filtres { " + listFiltres + " }"
				+ "\n Prodeur de recherche : " + profondeur + "\n Type de recherche : "
				+ tRecherche + "\n Unicite : " + tUnicite + "\n Resultat attendu   : { "
				+ listNomAttendu + " }");

		ArrayList<Noeud> listNoeud = Recherche.recherche(g, nomNoeud, listFiltres, profondeur,
				tRecherche, tUnicite);

		System.out.println(" Resultat recherche : { ");
		for(Noeud n : listNoeud){
			System.out.print(n.getName()+" ");
		}
		System.out.print(" }");

		assertEquals(listNomAttendu.size(), listNoeud.size());
		for (int i = 0; i < listNomAttendu.size(); i++) {
			assertTrue(listNomAttendu.contains(listNoeud.get(i).getName()));
		}
	}
}
