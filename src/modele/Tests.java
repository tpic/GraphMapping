package modele;


public class Tests {

	public static void main(String[] args) {
		Graph g = new Graph();

		Parser p = new Parser(g);
		p.verifFichier("donnees.txt");

		System.out.println(g);
	}
}