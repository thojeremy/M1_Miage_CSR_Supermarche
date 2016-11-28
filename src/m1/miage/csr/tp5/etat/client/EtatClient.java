package m1.miage.csr.tp5.etat.client;

import m1.miage.csr.tp5.etat.Etat;

public class EtatClient implements Etat{
	public static final String ATTENTE_CHARIOT 	= "Attente chariot";
	public static final String EN_COURSE 		= "En course";
	public static final String ATTENTE_PRODUIT 	= "Attente produit";
	public static final String ATTENTE_CAISSE 	= "Attente caisse";
	public static final String A_LA_CAISSE 		= "A la caisse";
	
	private Etat etatCourant;
	private Etat etatAttenteChariot;
	private Etat etatEnCourse;
	private Etat etatAttenteProduit;
	private Etat etatAttenteCaisse;
	private Etat etatALaCaisse;
	
	public EtatClient(){
		etatAttenteChariot 	= new EtatAttenteChariot();
		etatEnCourse 		= new EtatEnCourse();
		etatAttenteProduit 	= new EtatAttenteProduit();
		etatAttenteCaisse 	= new EtatAttenteCaisse();
		etatALaCaisse 		= new EtatALaCaisse();
		
		etatCourant = etatAttenteChariot;
	}

	@Override
	public void changerEtat(String etat) {
		switch(etat){
			case ATTENTE_CHARIOT:
				etatCourant = etatAttenteChariot;
				break;
				
			case EN_COURSE:
				etatCourant = etatEnCourse;
				break;
			
			case ATTENTE_PRODUIT:
				etatCourant = etatAttenteProduit;
				break;
				
			case ATTENTE_CAISSE:
				etatCourant = etatAttenteCaisse;
				break;
			
			case A_LA_CAISSE:
				etatCourant = etatALaCaisse;
				break;
				
			default:
				break;
		}
	}

	@Override
	public boolean estEtat(String etat) {
		switch(etat){
			case ATTENTE_CHARIOT:
				return etatCourant == etatAttenteChariot;
				
			case EN_COURSE:
				return etatCourant == etatEnCourse;
			
			case ATTENTE_PRODUIT:
				return etatCourant == etatAttenteProduit;
				
			case ATTENTE_CAISSE:
				return etatCourant == etatAttenteCaisse;
			
			case A_LA_CAISSE:
				return etatCourant == etatALaCaisse;
			
			default:
				return false;
		}
	}

	@Override
	public String getEtat() {
		return etatCourant + "";
	}

}
