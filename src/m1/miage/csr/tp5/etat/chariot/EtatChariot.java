package m1.miage.csr.tp5.etat.chariot;

import m1.miage.csr.tp5.etat.Etat;

public class EtatChariot implements Etat{
	public static final String EN_ATTENTE 		= "En attente";
	public static final String EN_UTILISATION 	= "En entrepot";
	
	private Etat etatCourant;
	private Etat etatEnAttente;
	private Etat etatEnUtilisation;
	
	public EtatChariot()
	{
		etatEnAttente 		= new EtatEnAttente();
		etatEnUtilisation 	= new EtatEnUtilisation();
		etatCourant 		= etatEnAttente;
	}
	
	@Override
	public void changerEtat(String etat)
	{
		switch(etat){
			case EN_ATTENTE:
				etatCourant = etatEnAttente;
				break;
				
			case EN_UTILISATION:
				etatCourant = etatEnUtilisation;
				break;
				
			default:
				break;
		}
	}

	@Override
	public boolean estEtat(String etat) {
		switch(etat){
			case EN_ATTENTE:
				return etatCourant == etatEnAttente;
				
			case EN_UTILISATION:
				return etatCourant == etatEnUtilisation;
			
			default:
				return true;
		}
	}

	@Override
	public String getEtat() {
		return etatCourant.getEtat();
	}
}
