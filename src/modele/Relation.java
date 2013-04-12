package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Relation {
	private String name;
	private HashMap<String, ArrayList<String>> mapAttribut;
	private Noeud noeudS;
	private Noeud noeudD;
	private Sens sens;

	public Relation(String name, Noeud noeudS, Noeud noeudD, Sens sens) {
		this.name = name.toLowerCase();
		this.mapAttribut = new HashMap<String, ArrayList<String>>();
		this.noeudS = noeudS;
		this.noeudD = noeudD;
		this.sens = sens;
	}

	public String getName() {
		return name;
	}

	public Noeud getNoeudSource() {
		return noeudS;
	}

	public Noeud getNoeudDestination() {
		return noeudD;
	}

	public String getSensString() {
		return sens.toString();
	}

	public Sens getSens() {
		return sens;
	}

	public void setMapAttribut(HashMap<String, ArrayList<String>> mapAttribut) {
		this.mapAttribut = mapAttribut;
	}

	public HashMap<String, ArrayList<String>> getMapAttribut() {
		return this.mapAttribut;
	}

	public void addAttribut(String nameAttribut, String valueAttribut) {
		String name = nameAttribut.toLowerCase();
		if (this.mapAttribut.containsKey(name)) {
			// TODO voir fusion intelligente
			this.mapAttribut.get(name).add(valueAttribut);
		} else {
			ArrayList<String> arrAtt = new ArrayList<String>();
			arrAtt.add(valueAttribut);
			this.mapAttribut.put(name, arrAtt);
		}
	}

	public void addAttribut(HashMap<String, ArrayList<String>> map) {
		for (String s : map.keySet()) {
			if (this.mapAttribut.containsKey(s)) {
				this.mapAttribut.get(s).addAll(map.get(s));
			} else {
				this.mapAttribut.put(s, map.get(s));
			}
		}
	}

	public Relation reverse() {
		Sens reverseSens = sens;
		if (sens.equals(Sens.IN)) {
			reverseSens = Sens.OUT;
		} else if (sens.equals(Sens.OUT)) {
			reverseSens = Sens.IN;
		}
		Relation reverse = new Relation(name, noeudD, noeudS, reverseSens);
		reverse.addAttribut(mapAttribut);
		return reverse;
	}

	public boolean estDuTypes(ArrayList<String> listLiens) {
		for (String s : listLiens) {
			if (this.name.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	public boolean equals(Relation r) {
		if (r == null) {
			return false;
		}
		return name.equalsIgnoreCase(r.getName()) && r.getNoeudDestination().equals(noeudD)
				&& r.getNoeudSource().equals(noeudS) && r.getSensString().equals(sens.toString());
	}

	public boolean equalsReverse(Relation r) {
		if (r == null) {
			return false;
		}
		return name.equalsIgnoreCase(r.getName()) && r.getNoeudDestination().equals(noeudS)
				&& r.getNoeudSource().equals(noeudD);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(this.noeudS + " : " + this.name);
		buf.append(" : [");
		for (String s : this.mapAttribut.keySet()) {
			buf.append(s + " -> '");
			for (String string : this.mapAttribut.get(s)) {
				buf.append(string + " ");
			}
			buf.append("', ");
		}
		buf.append("] avec " + this.noeudD.getName() + "\n");
		return buf.toString();
	}
}
