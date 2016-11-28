package m1.miage.csr.tp5.etat.chariot;

import m1.miage.csr.tp5.etat.Etat;

public class EtatEnAttente  implements Etat{
	String nom;
	
	public EtatEnAttente(){
		nom = EtatChariot.EN_ATTENTE;
	}

	@Override
	public void changerEtat(String etat) {
	}

	@Override
	public boolean estEtat(String etat) {
		return false;
	}

	@Override
	public String getEtat() {
		return nom;
	}

}