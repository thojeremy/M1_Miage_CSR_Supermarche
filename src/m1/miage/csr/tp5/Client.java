package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import m1.miage.csr.tp5.etat.client.EtatClient;

public class Client extends Thread{
	private static int ID_CLIENT = 0;
	private int idClient;
	private String nom;
	
	private Chariot chariot;
	private EtatClient etat;
	
	private Map<Article, Integer> liste;
	
	public Client(String nom){
		this.nom = nom;
		idClient = ID_CLIENT++;
		
		etat = new EtatClient();
		chariot = null;
		
		genererListe();
	}
	
	public String getEtat()
	{
		return etat.getEtat();
	}
	
	public void prendreChariot()
	{
		etat.changerEtat(EtatClient.EN_COURSE);
	}
	
	public void afficherListe(){
		String res = "== Liste de " + nom + " ==\n";
		
		for(Article a : liste.keySet()){
			res += a.getNom() + " : " + liste.get(a) + "\n";
		}
		
		res += "== Liste de " + nom + " ==";
		
		System.out.println(res);
	}
	
	private void genererListe()
	{
		int nbArticles = new Random().nextInt(Supermarche.NB_ARTICLES);
		// Contr�le pour voir si le nombre d'articles est �gal � z�ro
		nbArticles = nbArticles == 0? 1 : nbArticles;
		
		liste = new HashMap<Article, Integer>();
		
		for(int i = 0; i < nbArticles; i++){
			int nb = new Random().nextInt(5);
			// Contr�le pour voir si la quantit� est �gale � z�ro
			nb = nb == 0 ? 1 : nb;
			
			liste.put(Supermarche.articles[i], (liste.containsKey(Supermarche.articles[i]) ? (liste.get(Supermarche.articles[i]) + nb) : nb));
		}
	}
	
	public void marcherEntreLesRayons(){
		try{sleep(Supermarche.TPS[Supermarche.TPS_DEPLACEMENT_RAYON_CLIENT]);}catch(Exception e){}
	}
	
	@Override
	public void run() {
		// On prend un chariot
		chariot = Supermarche.chariots.prendreChariot();
		
		// On fait les rayons
		etat.changerEtat(EtatClient.EN_COURSE);
		
		
		// On va � la caisse
		etat.changerEtat(EtatClient.ATTENTE_CAISSE);
		
		// On passe � la caisse
		etat.changerEtat(EtatClient.A_LA_CAISSE);
		
		// On remet le chariot
		Supermarche.chariots.mettreChariot(chariot);
		
		// On part
		etat.changerEtat(EtatClient.PARTI);
	}
}
