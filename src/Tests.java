
public class Tests {

	public static void main(String[] args){
		Graph g = new Graph();

		//Barbara--[friend : since=201]-->Anna
		Noeud n1 = g.addNoeud("Barbara");
		Noeud n2 = g.addNoeud("Anna");
		Relation r = new Relation("friend", n1, n2, Sens.OUT);
		r.addAttribut("since", "2011");
		g.addRelation(n1, r, n2);
		
		//Barbara--[employee_of : role=architect, hired=Oct 04]-->BigCo 
		Noeud n3 = g.addNoeud("Barbara");
		Noeud n4 = g.addNoeud("BigCo");
		Relation r2 = new Relation("employee_of", n3, n4, Sens.OUT);
		r2.addAttribut("role", "architect");
		r2.addAttribut("hired", "Oct 04");
		g.addRelation(n3, r2, n4);
		
		System.out.println(g);
	}
	
}
