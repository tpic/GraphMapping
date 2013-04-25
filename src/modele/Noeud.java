package modele;

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
		this.flux.put("IN", new ArrayList<HashMap<String, ArrayList<Relation>>>());
		this.flux.put("OUT", new ArrayList<HashMap<String, ArrayList<Relation>>>());
		this.flux.put("INOUT", new ArrayList<HashMap<String, ArrayList<Relation>>>());
	}

	public String getName() {
		return name;
	}

	public void addFlux(Relation r) {
		if (!verifDoublon(r)) {
			boolean add = false;
			for (HashMap<String, ArrayList<Relation>> aList : this.flux.get(r.getSensString())) {
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
	}

	public boolean verifDoublon(Relation r) {
		for (Relation relation : this.getFlux(r.getSens())) {
			if (relation.equals(r)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Relation> getFluxEntrant() {
		ArrayList<Relation> listRelation = new ArrayList<Relation>();
		for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(Sens.IN.toString())) {
			for (String titleRelation : hm.keySet()) {
				listRelation.addAll(hm.get(titleRelation));
			}
		}
		// Ajout des inouts
		listRelation.addAll(this.getFluxDouble());
		return listRelation;
	}

	public ArrayList<Relation> getFluxSortant() {
		ArrayList<Relation> listRelation = new ArrayList<Relation>();
		for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(Sens.OUT.toString())) {
			for (String titleRelation : hm.keySet()) {
				listRelation.addAll(hm.get(titleRelation));
			}
		}
		// Ajout des inouts
		listRelation.addAll(this.getFluxDouble());
		return listRelation;
	}

	public ArrayList<Relation> getFluxDouble() {
		ArrayList<Relation> listRelation = new ArrayList<Relation>();
		for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(Sens.INOUT.toString())) {
			for (String titleRelation : hm.keySet()) {
				listRelation.addAll(hm.get(titleRelation));
			}
		}
		return listRelation;
	}

	public ArrayList<Relation> getAllFlux() {
		ArrayList<Relation> listRelation = new ArrayList<Relation>();
		for (String s : this.flux.keySet()) {
			for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(s)) {
				for (String titleRelation : hm.keySet()) {
					listRelation.addAll(hm.get(titleRelation));
				}
			}
		}
		return listRelation;
	}

	public ArrayList<Relation> getFlux(Sens s) {
		ArrayList<Relation> listRelations = null;
		switch (s) {
		case IN:
			listRelations = this.getFluxEntrant();
			break;
		case INOUT:
			listRelations = this.getAllFlux();
			break;
		case OUT:
			listRelations = this.getFluxSortant();
			break;
		}
		return listRelations;
	}

	public ArrayList<Relation> getRelation(String sens, String nomRelation) {
		ArrayList<Relation> listRelation = new ArrayList<Relation>();
		for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(sens)) {
			for (String titleRelation : hm.keySet()) {
				if (titleRelation.equalsIgnoreCase(nomRelation)) {
					listRelation.addAll(hm.get(titleRelation));
					// Voir si on peut pas return tout de suite
				}
			}
		}
		return listRelation;
	}

	public boolean equals(Noeud n) {
		return this.name.equals(n.getName());
	}

	public String toString() {
		StringBuffer buff = new StringBuffer(this.name);// + "\n");
		/*for (String s : flux.keySet()) {
			buff.append("\t " + s + "\n");
			for (HashMap<String, ArrayList<Relation>> hm : this.flux.get(s)) {
				for (String titleRelation : hm.keySet()) {
					buff.append("\t\t " + titleRelation + "\n");
					for (Relation r : hm.get(titleRelation)) {
						buff.append("\t\t\t " + r + "\n");
					}
				}
			}
		}*/
		return buff.toString();
	}
}
