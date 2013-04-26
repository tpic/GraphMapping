package modele;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Noeud> graph;

	public ArrayList<Noeud> getGraph() {
		return graph;
	}

	public Graph() {
		this.graph = new ArrayList<Noeud>();
	}

	public void addRelation(Noeud nFrom, Relation r, Noeud nTo) {
		nFrom.addFlux(r);
		Relation reverse = r.reverse();
		nTo.addFlux(reverse);
	}

	public Noeud addOrGetNoeud(String name) {
		if (noeudPresent(name)) {
			return getNoeud(name);
		}
		Noeud n = new Noeud(name);
		graph.add(n);
		return n;
	}

	public Noeud getNoeud(String name) {
		for (Noeud n : graph) {
			if (n.getName().equalsIgnoreCase((name))) {
				return n;
			}
		}
		return null;
	}

	public boolean noeudPresent(Noeud n) {
		return graph.contains(n);
	}

	public boolean noeudPresent(String name) {
		for (Noeud n : graph) {
			if (n.getName().equalsIgnoreCase((name))) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getRelationsGraph(){
		ArrayList<String> liste = new ArrayList<String>();
		for (Noeud n : graph){
			for (Relation r : n.getAllFlux()){
				if (!liste.contains(r.getName()))
					liste.add(r.getName());
			}
		}
		return liste;
		
	}

	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (Noeud n : graph) {
			buff.append(n + " ");
		}
		return buff.toString();
	}
}
