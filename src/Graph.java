import java.util.ArrayList;


public class Graph {
	private ArrayList<Noeud> graph;
	
	public Graph(){
		this.graph = new ArrayList<Noeud>();
	}
	
	public void addRelation(Noeud nTo, Relation r, String sens, Noeud nFrom){
		nTo.addFlux(r, sens);
		if(sens.equalsIgnoreCase(Sens.IN.toString())){
			sens = Sens.OUT.toString();
		} else if(sens.equalsIgnoreCase(Sens.OUT.toString())){
			sens = Sens.IN.toString();
		}
		nFrom.addFlux(r, sens);
	}
	
	public Noeud addNoeud(String name){
		if(noeudPresent(name)){
			return getNoeud(name);
		}
		Noeud n = new Noeud(name);
		graph.add(n);
		return n;
	}

	public Noeud getNoeud(String name){
		for(Noeud n : graph){
			if(n.getName().equalsIgnoreCase((name))){
				return n;
			}
		}
		return null;
	}
	
	public boolean noeudPresent(Noeud n){
		return graph.contains(n);
	}
	
	public boolean noeudPresent(String name){
		for(Noeud n : graph){
			if(n.getName().equalsIgnoreCase((name))){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		StringBuffer buff = new StringBuffer();
		for(Noeud n : graph){
			buff.append(n);
		}
		return buff.toString();
	}
}
