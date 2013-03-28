<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;


public class Relation {
	private String name;
	private HashMap<String,ArrayList<String>> mapAttribut;	
	private Noeud noeudD;
	private Sens sens;
	
	public Relation(String name,Noeud noeudD, Sens sens){
		this.name = name.toLowerCase();
		this.mapAttribut = new HashMap<String,ArrayList<String>>();
		this.noeudD = noeudD;
		this.sens = sens;
	}
	
	public String getName() {
		return name;
	}
	
	public Noeud getNoeudDestination() {
		return noeudD;
	}

	public String getSensString(){
		return sens.toString();
	}
	
	public Sens getSens(){
		return sens;
	}
	
	public void setMapAttribut(HashMap<String,ArrayList<String>> mapAttribut){
		this.mapAttribut = mapAttribut;
	}

	public void addAttribut(String nameAttribut, String valueAttribut){
		String name = nameAttribut.toLowerCase();
		if(this.mapAttribut.containsKey(name)){
			//TODO voir fusion intelligente
			this.mapAttribut.get(name).add(valueAttribut);
		} else {
			ArrayList<String> arrAtt = new ArrayList<String>();
			arrAtt.add(valueAttribut);
			this.mapAttribut.put(name, arrAtt);
		}
	}
	
	public void addAttribut(HashMap<String,ArrayList<String>> map){
		for(String s : map.keySet()){
			if(this.mapAttribut.containsKey(s)){
				this.mapAttribut.get(s).addAll(map.get(s));
			} else {
				this.mapAttribut.put(s, map.get(s));
			}
		}
	}
	
	public HashMap<String,ArrayList<String>> getMapAttribut(){
		return this.mapAttribut;
	}
	
	public boolean equals(Relation r){
		return name.equalsIgnoreCase(r.getName()) && r.getNoeudDestination().equals(noeudD);
	}
	
	public String toString(){		
		StringBuffer buf = new StringBuffer();
		buf.append(this.name);
		buf.append(" : [");
		for(String s: this.mapAttribut.keySet()){
			buf.append(s+" -> '");
			for(String string: this.mapAttribut.get(s)){
				buf.append(string+" ");
			}
			buf.append("', ");
		}
		buf.append("] avec "+this.noeudD.getName()+"\n");
		return buf.toString();
	}
=======

public class Relation {

>>>>>>> Premier commit
}
