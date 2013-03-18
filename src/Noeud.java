import java.util.HashMap;

public class Noeud {
	private String name;
	private HashMap<String, HashMap<Relation, Noeud>> fluxEntrant;
	private HashMap<String, HashMap<Relation, Noeud>> fluxSortant;

	/*
	 * String---->Relation---->Noeud ex : employe--->Employe_of(avec obj since,
	 * hired...)---->Carol
	 */

	public Noeud(String name) {
		this.name = name;
		this.fluxEntrant = new HashMap<String, HashMap<Relation, Noeud>>();
		this.fluxSortant = new HashMap<String, HashMap<Relation, Noeud>>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addFluxEntrant(Relation r, Noeud noeudDes) {
		if (this.fluxEntrant.containsKey(r.getName())) {
			this.fluxEntrant.get(r.getName()).put(r, noeudDes);
		} else {
			HashMap<Relation, Noeud> map = new HashMap<Relation, Noeud>();
			map.put(r, noeudDes);
			this.fluxEntrant.put(r.getName(), map);
		}
	}

	public void addFluxSortant(Relation r, Noeud noeudDes) {
		if (this.fluxEntrant.containsKey(r.getName())) {
			this.fluxEntrant.get(r.getName()).put(r, noeudDes);
		} else {
			HashMap<Relation, Noeud> map = new HashMap<Relation, Noeud>();
			map.put(r, noeudDes);
			this.fluxEntrant.put(r.getName(), map);
		}
	}

	public String toString() {
		StringBuffer flux = new StringBuffer("Flux entrant :\n");
		for (String titleRelation : this.fluxEntrant.keySet()) {
			for (Relation r : this.fluxEntrant.get(titleRelation).keySet()) {
				flux.append("\t"+this.name + " a comme " + r + " avec "
						+ this.fluxEntrant.get(titleRelation).get(r).getName()+"\n");
			}
		}
		flux.append("Flux sortant :\n");
		for (String titleRelation : this.fluxSortant.keySet()) {
			for (Relation r : this.fluxSortant.get(titleRelation).keySet()) {
				flux.append("\t"+this.name + " est " + r + " avec "
						+ this.fluxSortant.get(titleRelation).get(r).getName()+"\n");
			}
		}
		return flux.toString();
	}

	public static void main(String[] args){
		Noeud bigCo = new Noeud("BigCo");
		Noeud anna = new Noeud("Anna");
		Noeud carol = new Noeud("Carol");
		Noeud barbara = new Noeud("Barbara");
		Noeud elizabeth = new Noeud("Elizabeth");
		Noeud dawn = new Noeud("Dawn");
		
		Relation emp_ofANN = new Relation("employee_of");
		emp_ofANN.addAttribut("Role", "Developer");
		emp_ofANN.addAttribut("Hired", "Mar 06");
		Relation emp_ofBAR = new Relation("employee_of");
		emp_ofBAR.addAttribut("Role", "Architect");
		emp_ofBAR.addAttribut("Hired", "Feb 04");
		Relation emp_ofCAR = new Relation("employee_of");
		emp_ofCAR.addAttribut("Role", "Research");
		emp_ofCAR.addAttribut("hired", "Oct 98");
		
		bigCo.addFluxEntrant(emp_ofANN, anna);
		bigCo.addFluxEntrant(emp_ofBAR, barbara);
		bigCo.addFluxEntrant(emp_ofCAR, carol);
		
		System.out.println(bigCo);
	}
}
