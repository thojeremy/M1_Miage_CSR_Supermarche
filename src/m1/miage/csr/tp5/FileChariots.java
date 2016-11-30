package m1.miage.csr.tp5;

import m1.miage.csr.tp5.etat.chariot.EtatChariot;

public class FileChariots {
	private int nbChariot;
	
	private Chariot[] chariots;
	
	public FileChariots(int nbChariot)
	{
		this.nbChariot = nbChariot;
		
		chariots = new Chariot[nbChariot];
		for(int i = 0; i < nbChariot; i++){
			chariots[i] = new Chariot();
		}
	}
	
	public synchronized Chariot prendreChariot()
	{
		while(nbChariot <= 0){
			try{wait();}catch(Exception e){}
		}
		
		nbChariot--;
		
		System.out.println("PRENDRE CHARIOT> " + nbChariot);
		
		return prendreChariotEnAttente();
	}
	
	public synchronized void mettreChariot(Chariot chariot)
	{
		nbChariot++;
		
		System.out.println("METTRE CHARIOT> " + nbChariot);
		
		boolean continuer = true;
		for(int i = 0; i < chariots.length && continuer; i++){
			if(chariots[i].getId() == chariot.getId()){
				chariots[i].changerEtat(EtatChariot.EN_ATTENTE);
				continuer = false;
			}
		}
		
		notifyAll();
	}
	
	private Chariot prendreChariotEnAttente(){
		for(int i = 0; i < chariots.length; i++){
			if(chariots[i].getEtat() == EtatChariot.EN_ATTENTE){
				chariots[i].changerEtat(EtatChariot.EN_UTILISATION);
				return chariots[i];
			}
		}
		
		return null;
	}
}
