import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.sun.org.apache.xpath.internal.operations.Bool;


public class Parser {
	
	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<String> wrongLines = new ArrayList<String>();
	
	/**
	 * Teste la validité du fichier avec une regex, les lignes valides sont ajoutés à l'ArrayList lines
	 * et les autres dans l'ArrayList wrongLines
	 * @param file : le fichier à tester
	 */
	public static void verifFichier(File file){
		
		String ligne = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((ligne = br.readLine()) != null){
				//Test validité de la ligne
				if(Pattern.matches("(\\w+\\s?)+<?--\\s?\\w+\\s?(\\[\\s?(\\w+\\s?=\\s?((\\w+\\s?)+|(\\[\\s?(\\w+\\s?,\\s?)+\\w+\\s?\\])))(,\\s?(\\w+\\s?=\\s?((\\w+\\s?)+|(\\[\\s?(\\w+\\s?,\\s?)+\\w+\\s?\\]))))*\\s?\\])?\\s?-->?\\s?(\\w+\\s?)+",ligne)){
					lines.add(ligne);
				}else{
					wrongLines.add(ligne);
				}
			}
			
			br.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Découpe chaque ligne et ajoute chaque élément dans une ArrayList :
	 * 1. Type de relation (none, in, out, both)
	 * 2. Noeud de départ
	 * 3. Noeud d'arrivé
	 * 4. Relation
	 * 5. Attribut de la relation (*)
	 * 6. Valeur de l'attribut (*)
	 * 7. etc..
	 * @param l la ligne traitée
	 * @return La ligne découpée dans une ArrayList
	 */
	public static ArrayList<String> splitLines(String l){
		
		ArrayList<String> splitedLine = new ArrayList<String>();
		String attributs = "";
		
		/*
		 * typeRelation = none    ->  Aucun sens dans la relation    Noeud1--relation--Noeud2
		 * typeRelation = in      ->  Relation entrante du type      Noeud1<--relation--Noeud2
		 * typeRelation = out     ->  Relation sortante du type      Noeud1--relation-->Noeud2
		 * typeRelation = both    ->  Relation reciproque du type    Noeud1<--relation-->Noeud2
		 */
		String typeRelation = "none";
		
		// Premier split selon les tirets (--)
		String[] tab = l.split("--");
		
		// On recupère le premier noeud et on verifie le type de relation
		if(tab[0].charAt(tab[0].length()-1) == '<'){
			typeRelation = "in";
			tab[0] = tab[0].substring(0, tab[0].length()-1);
		}
		// On recupère le deuxième noeud et on verifie le type de relation
		if(tab[2].charAt(0) == '>'){
			if(typeRelation.compareTo("in") == 0) typeRelation = "both";
			else typeRelation = "out";
			tab[2] = tab[2].substring(1);
		}

		// Ajout du type de relation et des deux noeuds concernés dans l'ArrayList
		splitedLine.add(typeRelation);
		splitedLine.add(tab[0]);
		splitedLine.add(tab[2]);
		
		// Récupération du type de relation
		int nb = tab[1].indexOf("[");
		if(nb == -1) splitedLine.add(tab[1]); // Si la relation est sans attributs
		else{
			splitedLine.add(tab[1].substring(0,nb));
			attributs = tab[1].substring(nb+1, tab[1].indexOf("]", tab[1].length()-1));
		}
		
		//Booleen permettant de tester si on a atteind la fin du parcourt des attributs
		Boolean b = false;
		
		// On parcourt la chaine de caractère contenant tout les attributs et leurs valeurs
		while(attributs.length() != 0){
			int n = attributs.indexOf("=");
			String name = attributs.substring(0, n);
			String value = "";
			int fin = 0;
			
			// Si il s'agit d'une liste de valeurs
			if(attributs.charAt(n+1) == '['){
				fin = attributs.indexOf("]");
				value = attributs.substring(n+2, fin);
			}else{
				fin = attributs.indexOf(",", n);
				// Si il n'y a plus d'attributs, on passe b à true
				if(fin == -1){ 
					value = attributs.substring(n+1);
					b = true;
				}else value = attributs.substring(n+1, fin);
			}
			
			// Suppression des espaces et virgule superflux
			int index = name.indexOf(",");
			name = name.substring(index+1);
			name = name.trim();
			value = value.trim();
			
			splitedLine.add(name);
			splitedLine.add(value);
			
			// Si !b alors on supprime l'attribut traité de la chaine de caracères
			if(!b) attributs = attributs.substring(fin+1);
			else attributs = "";
		}
		
		return splitedLine;
	}
 
	
	public static void main(String[] args) {
		
		File file = new File("donnees.txt");
		
		try {
			
			verifFichier(file);
			ArrayList<ArrayList<String>> s = new ArrayList<ArrayList<String>>();
			
			for (String l : lines) {
				s.add(splitLines(l));
			}
			
			for(ArrayList<String> test : s){
				for(String t : test){
					System.out.println(t);
				}
				System.out.println("**************");
			}
			
			System.out.println("--------------------------------");
			
			for(String test : wrongLines){
				System.out.println(test);
			}
						
			
		} catch (PatternSyntaxException e){
			e.printStackTrace();
		}
	}

}
