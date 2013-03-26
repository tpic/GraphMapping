
public class Tests {

	public static void main(String[] args){
		Graph g = new Graph();

		//Barbara--[friend : since=201]-->Anna
		Noeud n1 = g.addOrGetNoeud("Barbara");
		Noeud n2 = g.addOrGetNoeud("Anna");
		Relation r = new Relation("friend", n2, Sens.OUT);
		r.addAttribut("since", "2011");
		g.addRelation(n1, r, n2);
		
		//Barbara--[employee_of : role=architect, hired=Oct 04]-->BigCo 
		Noeud n3 = g.addOrGetNoeud("Barbara");
		Noeud n4 = g.addOrGetNoeud("BigCo");
		Relation r2 = new Relation("employee_of", n4, Sens.OUT);
		r2.addAttribut("rOle", "architect");
		r2.addAttribut("hired", "Oct 04");
		g.addRelation(n3, r2, n4);
		
		//Barabaea--[friend : since=1999]-- Carool
		Noeud n5 = g.addOrGetNoeud("BarbaRa");
		Noeud n6 = g.addOrGetNoeud("Carol");
		Relation r3 = new Relation("Friend", n6, Sens.OUT);
		r3.addAttribut("since", "1999");
		g.addRelation(n5, r3, n6);
		
		//Barbara--[friend : since=1989, share=[books, movies, tweets] --> Elizabeth
		Noeud n7 = g.addOrGetNoeud("Barbara");
		Noeud n8 = g.addOrGetNoeud("Elizabeth");
		Relation r4 = new Relation("friend", n8, Sens.OUT);
		r4.addAttribut("since","1989");
		r4.addAttribut("shAre","movies");
		r4.addAttribut("shaRe","books");
		r4.addAttribut("Share","tweets");
		g.addRelation(n7, r4, n8);
		
		//Anna--[employee_of: role=developper, hired="Mar 06"]--> BigCo
		Noeud n9 = g.addOrGetNoeud("Anna");
		Noeud n10 = g.addOrGetNoeud("bigco");
		Relation r5 = new Relation("EmPlOyEE_Of", n10, Sens.OUT);
		r5.addAttribut("role", "developper");
		r5.addAttribut("hired", "Mar 06");
		g.addRelation(n9, r5, n10);
		
		
		System.out.println(g);
	}
	
}
