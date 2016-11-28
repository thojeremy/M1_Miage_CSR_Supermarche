package m1.miage.csr.tp5.etat.chef;

import m1.miage.csr.tp5.etat.Etat;

public class EtatChef implements Etat{
	public static final String EN_RAYON 	= "En rayon";
	public static final String EN_ENTREPOT 	= "En entrepot";
	
	private Etat etatCourant;
	private Etat etatEntrepot;
	private Etat etatRayon;
	
	public EtatChef()
	{
		etatEntrepot 	= new EtatEntrepot();
		etatRayon 		= new EtatRayon();
		etatCourant 	= etatEntrepot;
	}
	
	@Override
	public void changerEtat(String etat)
	{
		switch(etat){
			case EN_RAYON:
				etatCourant = etatRayon;
				break;
				
			case EN_ENTREPOT:
				etatCourant = etatEntrepot;
				break;
				
			default:
				break;
		}
	}

	@Override
	public boolean estEtat(String etat) {
		switch(etat){
			case EN_RAYON:
				return etatCourant == etatRayon;
				
			case EN_ENTREPOT:
				return etatCourant == etatEntrepot;
			
			default:
				return true;
		}
	}

	@Override
	public String getEtat() {
		return etatCourant + "";
	}
}
