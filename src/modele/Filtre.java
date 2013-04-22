package modele;

import java.util.HashMap;

public class Filtre {
	private Sens sens = Sens.INOUT;
	private String nomFiltre;
	private HashMap<String, String> listProprietes;

	public Filtre(String nomFiltre) {
		this.nomFiltre = nomFiltre;
	}

	public Filtre(String nomFiltre, Sens sens) {
		this.nomFiltre = nomFiltre;
		this.sens = sens;
	}

	public void addProprietes(String cle, String valeur) {
		listProprietes.put(cle, valeur);
	}

	public Sens getSens() {
		return sens;
	}

	public String getNomFiltre() {
		return nomFiltre;
	}

	public HashMap<String, String> getListProprietes() {
		return listProprietes;
	}

	public String toString() {
		return nomFiltre + " " + sens;
	}

}
