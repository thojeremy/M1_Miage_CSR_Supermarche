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
	
	/**
	 * Le constructeur de la classe Client s'il y a le nom qui est précisé
	 * 
	 * @param nom	Le nom du client
	 */
	public Client(String nom){
		this.nom = nom;
		idClient = ID_CLIENT++;
		
		etat = new EtatClient();
		chariot = null;
		
		genererListe();
	}
	
	/**
	 * Le constructeur de la classe Client si on veut un nom aléatoire
	 */
	public Client(){
		this.nom = genererNom();
		idClient = ID_CLIENT++;
		
		etat = new EtatClient();
		chariot = null;
		
		genererListe();
	}
	
	/**
	 * Permet de predre l'état courant du client
	 * 
	 * @return	L'état courant du client
	 */
	public String getEtat(){
		return etat.getEtat();
	}
	
	/** 
	 * Permet de prendre l'id du client
	 * 
	 * @return L'id du client
	 */
	public long getId(){
		return idClient;
	}
	
	/**
	 * Permet de prendre le nom du client
	 * 
	 * @return	Le nom du client
	 */
	public String getNom(){
		return nom;
	}
	
	/**
	 * Permet de prendre un chariot
	 */
	public void prendreChariot(){
		etat.changerEtat(EtatClient.EN_COURSE);
	}
	
	/**
	 * Permet de prendre le nombre d'articles qui ne sont pas les mêmes
	 * 
	 * @return	Le nombre d'articles qui sont différents
	 */
	public int prendreNombreArticlesDifferents(){
		return liste.size();
	}
	
	/**
	 * Permet de prendre la liste de courses du client
	 * 
	 * @return	La liste de courses du client
	 */
	public Map<Article, Integer> getListe(){
		return liste;
	}
	
	/**
	 * Permet de prendre la liste des courses qu'il reste à prendre dans les rayons
	 * 
	 * @return	La liste de courses qu'il reste à prendre dans les rayons
	 */
	public Map<Article, Integer> getListeAPrendre(){
		if(chariot == null){
			return liste;
		} else {
			Map<Article, Integer> aPrendre = new HashMap<Article, Integer>();
			
			for(Article a : liste.keySet()){
				if(! chariot.contientArticle(a)){
					aPrendre.put(a, liste.get(a));
				}
			}
			
			return aPrendre;
		}
	}
	
	/**
	 * Permet d'afficher la liste de courses du client
	 */
	public void afficherListe(){
		String res = "== Liste de " + nom + " ==\n";
		
		for(Article a : liste.keySet()){
			res += a.getNom() + " : " + liste.get(a) + "\n";
		}
		
		res += "== Liste de " + nom + " ==";
		
		System.out.println(res);
	}
	
	/**
	 * Permet de générer la liste aléatoirement
	 */
	private void genererListe(){
		int nbArticles = new Random().nextInt(Supermarche.NB_ARTICLES);
		// Contrôle pour voir si le nombre d'articles est égal à zéro
		nbArticles = nbArticles == 0? 1 : nbArticles;
		
		liste = new HashMap<Article, Integer>();
		
		for(int i = 0; i < nbArticles; i++){
			int nb = new Random().nextInt(5);
			int idArticle = new Random().nextInt(Supermarche.NB_ARTICLES - 1);
			
			// Contrôle pour voir si la quantité est égale à zéro
			if(nb > 0){
				liste.put(Supermarche.articles[idArticle], (liste.containsKey(Supermarche.articles[idArticle]) ? (liste.get(Supermarche.articles[idArticle]) + nb) : nb));
			}
		}
	}
	
	/**
	 * Permet de générer le nom du client
	 * 
	 * @return	Le nom généré
	 */
	private String genererNom(){
		// Génération du nom aléatoire
		String nom = "";
		int nbLettres = new Random().nextInt(7) + 1;
		
		for(int j = 0; j < nbLettres; j++){
			nom += (nom.length() == 0 ? ( (char) (new Random().nextInt(25) + 65)) : ((char) (new Random().nextInt(25) + 97)) );
		}
		
		return nom;
	}
	
	/**
	 * Permet d'attendre le temps que le client marche entre les rayons
	 */
	public void marcherEntreLesRayons(){
		try{sleep(Supermarche.TPS[Supermarche.TPS_DEPLACEMENT_RAYON_CLIENT]);}catch(Exception e){}
	}
	
	/**
	 * Permet d'attendre le temps que le client pose un article sur le tapis
	 */
	public void attendreArticleSurTapis(){
		try{sleep(Supermarche.TPS[Supermarche.TPS_ARTICLE_TAPIS]);}catch(Exception e){}
	}
	
	@Override
	public void run() {
		// On prend un chariot
		chariot = Supermarche.chariots.prendreChariot();
		System.out.println("CLIENT> " + nom + " a pris un chariot");
		
		// On fait les rayons
		etat.changerEtat(EtatClient.EN_COURSE);
		System.out.println("CLIENT> " + nom + " commence à faire ses courses");
		
		// On contrôle les rayons pour savoir vers où le client va marcher
		for(int i = 0; i < Supermarche.rayons.length; i++){
			Article article = Supermarche.rayons[i].getArticle();
			
			// On contrôle voir si le rayon a l'article voulu
			if(liste.containsKey(article)){
				// Si il a l'article
				// On marche jusqu'à lui
				marcherEntreLesRayons();
				System.out.println("CLIENT> " + nom + " marche vers le rayon souhaité");
				
				// On prend l'article
				etat.changerEtat(EtatClient.ATTENTE_PRODUIT);
				Supermarche.rayons[i].prendreArticle(liste.get(article));
				etat.changerEtat(EtatClient.EN_COURSE);
				System.out.println("CLIENT> " + nom + " prend l'article " + article.getNom());
				
				// Et on le met dans le chariot
				chariot.ajouterArticle(Supermarche.rayons[i].getArticle(), liste.get(article).intValue());
				System.out.println("CLIENT> " + nom + " met l'article " + article.getNom() + " dans son chariot");
			}
		}
		
		// On va à la caisse...
		etat.changerEtat(EtatClient.ATTENTE_CAISSE);
		System.out.println("CLIENT> " + nom + " attend à la caisse");
		
		// ... et on met les articles sur le tapis...
		for(Article a : chariot.prendreArticle().keySet()){
			// ... 1 par 1
			while(chariot.prendreArticle().get(a) > 0){
				attendreArticleSurTapis();
				Supermarche.tapisDeCaisse.mettreArticles(this, a);
				chariot.prendreArticle().put(a, chariot.prendreArticle().get(a) - 1);
				
				System.out.println("CLIENT> " + nom + " a posé son " + a.getNom() + " sur le tapis");
			}
		}
		
		// On passe à la caisse
		etat.changerEtat(EtatClient.A_LA_CAISSE);
		System.out.println("CLIENT> " + nom + " passe à la caisse (pour payer)");
		
		// On remet le chariot
		Supermarche.chariots.mettreChariot(chariot);
		System.out.println("CLIENT> " + nom + " remet son chariot");
		
		// On part
		etat.changerEtat(EtatClient.PARTI);
		System.out.println("CLIENT> " + nom + " part");
	}
}
