import java.util.HashMap;


public class Relation {
	private String name;
	private HashMap<String,String> mapAttribut;
	
	public Relation(String name){
		this.name = name;
		this.mapAttribut = new HashMap<String,String>();
	}
	
	public String getName() {
		return name;
	}

	public void addAttribut(String nameAttribut, String valueAttribut){
		this.mapAttribut.put(nameAttribut, valueAttribut);
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer(this.name);
		buf.append("[ ");
		for(String s: this.mapAttribut.keySet()){
			buf.append(s+"->"+this.mapAttribut.get(s)+" ");
		}
		buf.append("]");
		return buf.toString();
	}
}
