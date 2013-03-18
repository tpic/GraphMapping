<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;

public class Noeud {
	private String name;
	// Flux = < "in" --> [ Relation1, Relation2 ] >
	// _____= < "out"--> [ Relation ] >
	// _____= < "inout"> [ Relation ] >
	private HashMap<String, ArrayList<Relation>> flux;

	public Noeud(String name) {
		this.name = name;
		this.flux = new HashMap<String, ArrayList<Relation>>();
		this.flux.put("IN", new ArrayList<Relation>());
		this.flux.put("OUT", new ArrayList<Relation>());
		this.flux.put("INOUT", new ArrayList<Relation>());
	}

	public String getName() {
		return name;
	}

	public void addFlux(Relation r) {
		this.flux.get(r.getSensString()).add(r);				
	}
	
	public Relation getRelation(Relation r){
		for(Relation relation : this.flux.get(r.getSens())){
			if(relation.equals(r)){
				return relation;
			}
		}
		return null;
	}
	
	public boolean equals(Noeud n){
		return this.name.equals(n.getName());
	}
	
	public String toString(){
		StringBuffer buff = new StringBuffer(this.name);		
		for(String s : flux.keySet()){
			buff.append("\t "+s+"\n");
			for(Relation r : flux.get(s)){
				buff.append("\t\t"+r);
			}
		}
		return buff.toString();
	}
=======

public class Noeud {

>>>>>>> Premier commit
}
