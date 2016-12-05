package m1.miage.csr.tp5;

import m1.miage.csr.tp5.etat.chariot.EtatChariot;

public class FileChariots {
	private int nbChariot;
	
	private Chariot[] chariots;
	
	/**
	 * Constructeur de la file de chariots
	 * 
	 * @param nbChariot	Le nombre de chariots à générer
	 */
	public FileChariots(int nbChariot)
	{
		this.nbChariot = nbChariot;
		
		chariots = new Chariot[nbChariot];
		for(int i = 0; i < nbChariot; i++){
			chariots[i] = new Chariot();
		}
	}
	
	/**
	 * Permet de prendre un chariot dans la file de chariots
	 * 
	 * @return	Le chariot pris
	 */
	public synchronized Chariot prendreChariot()
	{
		while(nbChariot <= 0){
			try{wait();}catch(Exception e){}
		}
		
		nbChariot--;
		
		System.out.println("PRENDRE CHARIOT> " + nbChariot);
		
		return prendreChariotEnAttente();
	}
	
	/**
	 * Permet de remettre un chariot dans la file de chariots
	 * 
	 * @param chariot	Le chariot à remettre
	 */
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
	
	/**
	 * Permet de savoir quel est le premier chariot de la file qui est en attente
	 * 
	 * @return	Le premier chariot de la file qui est en attente
	 */
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
