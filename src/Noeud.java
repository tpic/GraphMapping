import java.util.ArrayList;
import java.util.HashMap;

public class Noeud {
	private String name;
	// Flux = < "in" --> [ "Friend" --> Relation ] >
	// _____= ______ --> [ "Share" --> Relation ] >
	// _____= < "out"--> [ "Friend" --> Relation ] >
	// _____= < "inout"> [ "Friend" --> Relation ] >
	private HashMap<String, HashMap<String, ArrayList<Relation>>> flux;

	public Noeud(String name) {
		this.name = name;
		this.flux = new HashMap<String, HashMap<String, ArrayList<Relation>>>();
		this.flux.put("IN", new HashMap<String, ArrayList<Relation>>());
		this.flux.put("OUT", new HashMap<String, ArrayList<Relation>>());
		this.flux.put("INOUT", new HashMap<String, ArrayList<Relation>>());
	}

	public String getName() {
		return name;
	}

	public void addFlux(Relation r, String sens) {
		if(this.flux.get(sens).containsKey(r.getName())){
			this.flux.get(sens).get(r.getName()).add(r);
		} else {
			ArrayList<Relation> l = new ArrayList<Relation>();
			l.add(r);
			HashMap<String, ArrayList<Relation>> hm = new HashMap<String, ArrayList<Relation>>();
			hm.put(r.getName(), l);
			this.flux.put(sens, hm);				
		}
	}
	
	public boolean equals(Noeud n){
		return this.name.equals(n.getName());
	}
	
	public String toString(){
		StringBuffer buff = new StringBuffer(this.name+"\n");
		for(String s : flux.keySet()){
			buff.append("\t "+s+"\n");
			for(String title : flux.get(s).keySet()){
				buff.append("\t\t"+flux.get(s).get(title)+"\n");
			}
		}
		return buff.toString();
	}
}
