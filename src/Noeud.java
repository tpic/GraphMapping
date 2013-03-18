import java.util.ArrayList;
import java.util.HashMap;

public class Noeud {
	private String name;
	private HashMap<String, ArrayList<Relation>> fluxEntrant;
	private HashMap<String, ArrayList<Relation>> fluxSortant;

	public Noeud(String name) {
		this.name = name;
		this.fluxEntrant = new HashMap<String, ArrayList<Relation>>();
		this.fluxSortant = new HashMap<String, ArrayList<Relation>>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addFluxEntrant(Relation r) {
		if (this.fluxEntrant.containsKey(r.getName())) {
			this.fluxEntrant.get(r.getName()).add(r);
		} else {
			ArrayList<Relation> aList = new ArrayList<Relation>();
			aList.add(r);
			this.fluxEntrant.put(r.getName(), aList);
		}
	}

	public void addFluxSortant(Relation r) {
		if (this.fluxSortant.containsKey(r.getName())) {
			this.fluxSortant.get(r.getName()).add(r);
		} else {
			ArrayList<Relation> aList = new ArrayList<Relation>();
			aList.add(r);
			this.fluxSortant.put(r.getName(), aList);
		}
	}

	public String toString() {
		System.out.println(this.name);
		StringBuffer flux = new StringBuffer("\tFlux entrant :\n");
		for (String titleRelation : this.fluxEntrant.keySet()) {
			for (Relation r : this.fluxEntrant.get(titleRelation)) {
				flux.append("\t\t" + this.name + " a comme " + r);
			}
		}
		flux.append("\tFlux sortant :\n");
		for (String titleRelation : this.fluxSortant.keySet()) {
			for (Relation r : this.fluxSortant.get(titleRelation)) {
				flux.append("\t\t" + this.name + " est " + r);
			}
		}
		return flux.toString();
	}

	public static void main(String[] args) {
		Noeud bigCo = new Noeud("BigCo");
		Noeud anna = new Noeud("Anna");
		Noeud carol = new Noeud("Carol");
		Noeud barbara = new Noeud("Barbara");
		Noeud elizabeth = new Noeud("Elizabeth");
		Noeud dawn = new Noeud("Dawn");

		Relation emp_ofANN = new Relation("employee", anna);
		emp_ofANN.addAttribut("Role", "Developer");
		emp_ofANN.addAttribut("Hired", "Mar 06");
		Relation emp_ofCAR = new Relation("employee", carol);
		emp_ofCAR.addAttribut("Role", "Research");
		emp_ofCAR.addAttribut("hired", "Oct 98");
		Relation emp_ofBAR = new Relation("employee", barbara);
		emp_ofBAR.addAttribut("Role", "Architect");
		emp_ofBAR.addAttribut("Hired", "Feb 04");

		Relation friendBAR = new Relation("friend", anna);
		friendBAR.addAttribut("Since", "2011");
		Relation friendBAR2 = new Relation("friend", elizabeth);
		friendBAR2.addAttribut("Since", "1989");
		friendBAR2.addAttribut("Share", "books, movies, tweets");
		Relation friendBAR3 = new Relation("friend", carol);
		friendBAR3.addAttribut("Since", "1999");
		Relation emp_BAR = new Relation("employee", bigCo);
		emp_BAR.setMapAttribut(emp_ofBAR.getAttributs());

		bigCo.addFluxEntrant(emp_ofANN);
		bigCo.addFluxEntrant(emp_ofBAR);
		bigCo.addFluxEntrant(emp_ofCAR);

		barbara.addFluxSortant(friendBAR);
		barbara.addFluxSortant(friendBAR2);
		barbara.addFluxSortant(friendBAR3);
		barbara.addFluxSortant(emp_BAR);

		try {
			anna.addFluxEntrant(new Relation("friend", barbara, "Since", "2002"));
			anna.addFluxSortant(new Relation("employee", bigCo, "Role",
					"Developer", "hired", "Mar 06"));
			elizabeth.addFluxEntrant(new Relation("friend", barbara, "since",
					"1989", "share", "Books, movies, tweets"));
			carol.addFluxEntrant(new Relation("friend", barbara,"since","1999"));
			carol.addFluxSortant(new Relation("employee", bigCo,"role","Research","hired","Oct 08"));
			dawn.addFluxEntrant(new Relation("friend", carol, "since","2005"));
		} catch (Exception e) {
			System.out.println("Erreur création lien");
			e.printStackTrace();
		}

		System.out.println(bigCo);
		System.out.println(barbara);
		System.out.println(anna);
		System.out.println(carol);
		System.out.println(elizabeth);
		System.out.println(dawn);
	}
}
