package sources;

import java.util.ArrayList;
import java.util.HashMap;

public class Noeud {
	private String name;
	// Flux = < "in" --> [ "friends" -> [Relation1, Relation2]
	// ____________________"share" -> Relation ] >
	// ____ = < "out" --> [ "friends" -> [Relation1, Relation2] >
	// _____= < "inout"> [ "friends" -> [Relation2] ] >
	private HashMap<String, ArrayList<HashMap<String, ArrayList<Relation>>>> flux;

	public Noeud(String name) {
		this.name = name;
		this.flux = new HashMap<String, ArrayList<HashMap<String, ArrayList<Relation>>>>();
		this.flux.put("IN",
				new ArrayList<HashMap<String, ArrayList<Relation>>>());
		this.flux.put("OUT",
				new ArrayList<HashMap<String, ArrayList<Relation>>>());
		this.flux.put("INOUT",
				new ArrayList<HashMap<String, ArrayList<Relation>>>());
	}

	public String getName() {
		return name;
	}

	public void addFlux(Relation r) {
		boolean add = false;
		for (HashMap<String, ArrayList<Relation>> aList : this.flux.get(r
				.getSensString())) {
			if (aList.containsKey(r.getName())) {
				aList.get(r.getName()).add(r);
				add = true;
				break;
			}
			ArrayList<Relation> l = new ArrayList<Relation>();
			l.add(r);
			aList.put(r.getName(), l);
			add = true;
			break;
		}
		if (!add) {
			// Quand la hashmap In, Out ou Inout est vide
			ArrayList<Relation> l = new ArrayList<Relation>();
			l.add(r);
			HashMap<String, ArrayList<Relation>> hm = new HashMap<String, ArrayList<Relation>>();
			hm.put(r.getName(), l);
			this.flux.get(r.getSensString()).add(hm);
		}
	}

	public boolean verifDoublon(Noeud n, Relation r) {
		for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(r
				.getSens().toString())) {
			for (String titleRelation : hm.keySet()) {
				if (titleRelation.equalsIgnoreCase(r.getName())) {
					// for() dans l'arraylist avant
					if (hm.get(titleRelation).equals(r)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean equals(Noeud n) {
		return this.name.equals(n.getName());
	}

	public String toString() {
		StringBuffer buff = new StringBuffer(this.name + "\n");
		for (String s : flux.keySet()) {
			buff.append("\t " + s + "\n");
			for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(s)) {
				for (String titleRelation : hm.keySet()) {
					buff.append("\t\t " + titleRelation + "\n");
					for (Relation r : hm.get(titleRelation)) {
						buff.append("\t\t\t " + r + "\n");
					}
				}
			}
		}
		return buff.toString();
	}
}
