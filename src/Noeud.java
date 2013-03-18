import java.util.ArrayList;
import java.util.HashMap;


public class Noeud {
	private String name;
	private HashMap<String, HashMap<Relation, ArrayList<Noeud>>> fluxEntrant;
	private HashMap<String, HashMap<Relation, ArrayList<Noeud>>> fluxSortant;
	/*
	 *      String---->Relation---->Noeud
	 * ex : employe--->Employe_of(avec obj since, hired...)---->Carol
	 */
	
	public Noeud(String name){
		this.name = name;
		this.fluxEntrant = new HashMap<String,HashMap<Relation, ArrayList<Noeud>>>();
		this.fluxSortant = new HashMap<String,HashMap<Relation, ArrayList<Noeud>>>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
