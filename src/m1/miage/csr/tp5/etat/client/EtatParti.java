package m1.miage.csr.tp5.etat.client;

import m1.miage.csr.tp5.etat.Etat;

public class EtatParti implements Etat{
	String nom;
	
	public EtatParti()
	{
		nom = EtatClient.PARTI;
	}
	
	public String toString(){
		return nom;
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
		return nom;
	}
}
