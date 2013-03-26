import java.util.ArrayList;


public class Graph {
	private ArrayList<Noeud> graph;
	
	public Graph(){
		this.graph = new ArrayList<Noeud>();
	}
	
	public void addRelation(Noeud nFrom, Relation r, Noeud nTo){
		nFrom.addFlux(r);
		Sens sens = r.getSens();
		if(r.getSens().toString().equalsIgnoreCase(Sens.IN.toString())){
			sens = Sens.OUT;
		} else if(r.getSens().toString().equalsIgnoreCase(Sens.OUT.toString())){
			sens = Sens.IN;
		}
		Relation reverse = new Relation(r.getName(), nFrom, sens);
		reverse.addAttribut(r.getMapAttribut());
		nTo.addFlux(reverse);
	}
	
	public Noeud addOrGetNoeud(String name){
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
