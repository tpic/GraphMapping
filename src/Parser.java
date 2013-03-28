import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Parser {

	private Graph g = new Graph();

	public Parser(Graph g) {
		this.g = g;
	}

	/**
	 * Lecture du fichier en paramètre et appel de la méthode verifLigne sur
	 * chaque ligne
	 * 
	 * @param file
	 *            : le fichier à tester
	 */
	public void verifFichier(String nomFichier) {

		String ligne = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(nomFichier)));
			while ((ligne = br.readLine()) != null) {
				if (verifLigne(ligne)) {
					splitLines(ligne);
				} else {
					System.out.println(ligne);
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Teste la validité de la ligne avec une regex, les lignes valides sont
	 * ajoutéés à l'ArrayList lines et les autres dans l'ArrayList wrongLines
	 * 
	 * @param ligne
	 */
	public boolean verifLigne(String ligne) {
		// Test validité de la ligne
		return Pattern
				.matches(
						"(\\w+[éèêàôïü]*\\s?)+<?--\\s?\\w+[éèêàôïü]*\\s?(\\[\\s?(\\w+[éèêàôïü]*\\s?=\\s?((\\w+[éèêàôïü]*\\s?)+|(\\[\\s?(\\w+[éèêàôïü]*\\s?,\\s?)+\\w+[éèêàôïü]*\\s?\\])))(,\\s?(\\w+[éèêàôïü]*\\s?=\\s?((\\w+[éèêàôïü]*\\s?)+|(\\[\\s?(\\w+[éèêàôïü]*\\s?,\\s?)+\\w+[éèêàôïü]*\\s?\\]))))*\\s?\\])?\\s?-->?\\s?(\\w+[éèêàôïü]*\\s?)+",
						ligne);
	}

	/**
	 * Découpe chaque ligne et ajoute chaque élément dans une ArrayList : 1.
	 * Type de relation (in, out, inout) 2. Noeud de départ 3. Noeud d'arrivé 4.
	 * Relation 5. Attribut de la relation (*) 6. Valeur de l'attribut (*) 7.
	 * etc..
	 * 
	 * @param l
	 *            la ligne traitée
	 * @return La ligne découpée dans une ArrayList
	 */
	public void splitLines(String l) {

		ArrayList<String> splitedLine = new ArrayList<String>();
		String attributs = "";

		/*
		 * typeRelation = in -> Relation entrante : Noeud1<--relation--Noeud2
		 * typeRelation = out -> Relation sortante : Noeud1--relation-->Noeud2
		 * typeRelation = inout -> Relation reciproque :
		 * Noeud1<--relation-->Noeud2 ou Noeud1--relation--Noeud2
		 */
		String typeRelation = "inout";

		// Premier split selon les tirets (--)
		String[] tab = l.split("--");

		// On recupère le premier noeud et on verifie le type de relation
		if (tab[0].charAt(tab[0].length() - 1) == '<') {
			typeRelation = "in";
			tab[0] = tab[0].substring(0, tab[0].length() - 1);
		}
		// On recupère le deuxième noeud et on verifie le type de relation
		if (tab[2].charAt(0) == '>') {
			if (typeRelation.compareTo("in") == 0)
				typeRelation = "inout";
			else
				typeRelation = "out";
			tab[2] = tab[2].substring(1);
		}

		// Ajout du type de relation et des deux noeuds concernés dans
		// l'ArrayList
		splitedLine.add(typeRelation);
		splitedLine.add(tab[0]);
		splitedLine.add(tab[2]);

		// Récupération du type de relation
		int nb = tab[1].indexOf("[");
		if (nb == -1)
			splitedLine.add(tab[1]); // Si la relation est sans attributs
		else {
			splitedLine.add(tab[1].substring(0, nb));
			attributs = tab[1].substring(nb + 1, tab[1].indexOf("]", tab[1].length() - 1));
		}

		// Booleen permettant de tester si on a atteind la fin du parcours des
		// attributs
		Boolean b = false;

		// On parcourt la chaine de caractère contenant tout les attributs et
		// leurs valeurs
		while (attributs.length() != 0) {
			int n = attributs.indexOf("=");
			String name = attributs.substring(0, n);
			String value = "";
			int fin = 0;

			// Si il s'agit d'une liste de valeurs
			if (attributs.charAt(n + 1) == '[') {
				fin = attributs.indexOf("]");
				value = attributs.substring(n + 2, fin);
			} else {
				fin = attributs.indexOf(",", n);
				// Si il n'y a plus d'attributs, on passe b à true
				if (fin == -1) {
					value = attributs.substring(n + 1);
					b = true;
				} else
					value = attributs.substring(n + 1, fin);
			}

			// Suppression des espaces et virgule superflux
			int index = name.indexOf(",");
			name = name.substring(index + 1);
			name = name.trim();
			value = value.trim();

			splitedLine.add(name);
			splitedLine.add(value);

			// Si !b alors on supprime l'attribut traité de la chaine de
			// caractères
			if (!b)
				attributs = attributs.substring(fin + 1);
			else
				attributs = "";
		}
		addGraph(splitedLine);
	}

	public void addGraph(ArrayList<String> splitedLine) {
		Noeud n1 = g.addOrGetNoeud(splitedLine.get(1));
		Noeud n2 = g.addOrGetNoeud(splitedLine.get(2));
		Sens sens = Sens.INOUT;
		if (splitedLine.get(0).equalsIgnoreCase(Sens.IN.toString())) {
			sens = Sens.IN;
		} else if (splitedLine.get(0).equalsIgnoreCase(Sens.OUT.toString())) {
			sens = Sens.OUT;
		}
		Relation r = new Relation(splitedLine.get(3), n2, sens);
		for (int i = 4; i < splitedLine.size(); i += 2) {
			r.addAttribut(splitedLine.get(i), splitedLine.get(i + 1));
		}
		g.addRelation(n1, r, n2);

	}

}
