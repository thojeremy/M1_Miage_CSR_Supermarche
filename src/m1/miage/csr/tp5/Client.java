package m1.miage.csr.tp5;

import java.util.Map;

import m1.miage.csr.tp5.etat.client.EtatClient;

public class Client {
	private static int ID_CLIENT = 0;
	private int idClient;
	private String nom;
	
	private Chariot chariot;
	private EtatClient etat;
	
	private Map<Article, Integer> liste;
	
	public Client(String nom){
		this.nom = nom;
		idClient = ID_CLIENT++;
		chariot = null;
		
		etat = new EtatClient();
	}
}
