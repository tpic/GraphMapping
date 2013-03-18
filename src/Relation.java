import java.util.HashMap;


public class Relation {
	private String name;
	private HashMap<String,String> mapAttribut;
	private Noeud noeud;
	
	public Relation(String name,Noeud noeudRelation){
		this.name = name;
		this.mapAttribut = new HashMap<String,String>();
		this.noeud = noeudRelation;
	}
	
	public Relation(String name,Noeud noeudRelation, String... tabAtt) throws Exception{
		this.name = name;
		this.mapAttribut = new HashMap<String,String>();
		this.noeud = noeudRelation;
		if(tabAtt.length%2!=0){
			throw new Exception();
		}
		int i = 0;
		String key;
		String value;
		while(i<tabAtt.length){
			key = tabAtt[i];
			i++;
			value = tabAtt[i];
			i++;
			this.mapAttribut.put(key, value);
		}
	}
	
	public String getName() {
		return name;
	}

	public Noeud getNoeud() {
		return noeud;
	}

	public void setMapAttribut(HashMap<String,String> mapAttribut){
		this.mapAttribut = mapAttribut;
	}
	
	public void addAttribut(String nameAttribut, String valueAttribut){
		this.mapAttribut.put(nameAttribut, valueAttribut);
	}
	
	public HashMap<String,String> getAttributs(){
		return this.mapAttribut;
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer(this.name);
		buf.append("[ ");
		for(String s: this.mapAttribut.keySet()){
			buf.append(s+"->'"+this.mapAttribut.get(s)+"' ");
		}
		buf.append("] avec "+this.noeud.getName()+"\n");
		return buf.toString();
	}
}
