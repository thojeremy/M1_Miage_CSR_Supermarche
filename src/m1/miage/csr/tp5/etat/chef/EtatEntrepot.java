package m1.miage.csr.tp5.etat.chef;

import m1.miage.csr.tp5.etat.Etat;

public class EtatEntrepot implements Etat{
	String nom;

	public EtatEntrepot(){
		nom = "EtatEntrepot";
	}

	@Override
	public void changerEtat(String etat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estEtat(String etat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getEtat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(){
		return nom;
	}
}
