import java.util.ArrayList;
import java.util.HashMap;


public class Relation {
	private String name;
	private HashMap<String,ArrayList<String>> mapAttribut;	
	private Noeud noeudS;
	private Noeud noeudD;
	//private Sens sens;
	
	public Relation(String name,Noeud noeudS, Noeud noeudD){
		this.name = name;
		this.mapAttribut = new HashMap<String,ArrayList<String>>();
		this.noeudS = noeudS;
		this.noeudD = noeudD;
		//this.sens = sens;
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

	/*public String getSens(){
		return sens.toString();
	}*/
	
	public void setMapAttribut(HashMap<String,ArrayList<String>> mapAttribut){
		this.mapAttribut = mapAttribut;
	}

	public void addAttribut(String nameAttribut, String valueAttribut){
		if(this.mapAttribut.containsValue(nameAttribut)){
			//TODO voir fusion intelligente
			this.mapAttribut.get(nameAttribut).add(valueAttribut);
		} else {
			ArrayList<String> arrAtt = new ArrayList<String>();
			arrAtt.add(valueAttribut);
			this.mapAttribut.put(nameAttribut, arrAtt);
		}
	}
	
	public HashMap<String,ArrayList<String>> getMapAttribut(){
		return this.mapAttribut;
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer(this.noeudS.getName());
		buf.append(" -- "); buf.append(this.name);
		buf.append("[ ");
		for(String s: this.mapAttribut.keySet()){
			buf.append(s+"->'");
			for(String string: this.mapAttribut.get(s)){
				buf.append(string);
			}
			buf.append(", ");
		}
		buf.append("] avec "+this.noeudD.getName());
		return buf.toString();
	}
}
