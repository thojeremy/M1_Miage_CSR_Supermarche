package m1.miage.csr.tp5.etat.client;

import m1.miage.csr.tp5.etat.Etat;

public class EtatALaCaisse implements Etat{
	private String nom;
	
	public EtatALaCaisse()
	{
		nom = "EtatALaCaisse";
	}
	
	public String toString()
	{
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
		return null;
	}

}
