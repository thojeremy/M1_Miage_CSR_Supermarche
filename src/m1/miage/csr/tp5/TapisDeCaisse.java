package m1.miage.csr.tp5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TapisDeCaisse{
	private Map<Client, HashMap<Article, Integer>> tapis;
	private Map<Integer, Client> ordreClient;
	
	private List<Article> ordreArticles;
	
	private int tailleTapis;
	private int idClient;
	
	/**
	 * Constructeur du tapis de caisse
	 * 
	 * @param tailleTapis	La taille du tapis
	 */
	public TapisDeCaisse(int tailleTapis)
	{
		tapis 		= new HashMap<Client, HashMap<Article, Integer>>();
		ordreClient	= new HashMap<Integer, Client>();
		
		ordreArticles = new ArrayList<Article>();
		
		this.tailleTapis 	= tailleTapis;
		idClient 			= 0;
	}
	
	/**
	 * Permet de mettre l'article [article] du client
	 * 
	 * @param client	Le client qui veut poser un article
	 * @param article	L'article que le client veut poser
	 */
	public synchronized void mettreArticles(Client client, Article article){
		if(getNbArticlesTapis() + 1 > tailleTapis){
			try{wait();}catch(Exception e){}
		}
		
		// Si le client a d�j� des articles sur le tapis...
		if(tapis.containsKey(client)){
			// ... on ajoute � ses articles...
			boolean aArticle = tapis.get(client).containsKey(article);
			tapis.get(client).put(article, aArticle ? tapis.get(client).get(article) + 1 : 1);
		} else {
			// ... sinon on cr�e un nouveau client pour le tapis
			HashMap<Article, Integer> hm = new HashMap<Article, Integer>();
			hm.put(article, 1);
			
			tapis.put(client, hm);
			ordreClient.put(idClient++, client);
		}
		
		// On ajoute l'article
		ordreArticles.add(article);
		
		System.out.println("AJOUTER ARTICLE TAPIS> "+ordreArticles.size());
		
		notify();
	}
	
	/**
	 * Permet d'enlever un article du tapis
	 */
	public synchronized void enleverArticle(){
		if(!articlePresent()){
			try{wait();}catch(Exception e){}
		}
		
		// On prend le premier client
		Client client = prendrePremierClient();
		
		if(client != null){
			// On prend son premier article...
			Article article = ordreArticles.get(0);
			
			boolean aArticle = tapis.get(client).containsKey(article);
			
			// ... on l'enl�ve du tapis par quantit� de 1...
			tapis.get(client).put(article, aArticle ? tapis.get(client).get(article) - 1 : 0);
			
			// ... et si il y en a plus sur le tapis... 
			if(tapis.get(client).get(article) <= 0){
				// ... on l'enl�ve des articles pr�sents sur le tapis
				tapis.get(client).remove(article);
				ordreArticles.remove(0);
			}
			
			// Si le client n'a plus d'articles sur le tapis...
			if(tapis.get(client).isEmpty()){
				// ... �a veut dire qu'il a pay� et peut partir
				tapis.remove(client);
				supprimerPremierClient();
			}
			
			System.out.println("ENLEVER ARTICLE TAPIS> " + ordreArticles.size());
				
			notify();
		}
	}
	
	/**
	 * Permet de savoir s'il y a au moins un article pr�sent sur le tapis
	 * 
	 * @return	TRUE	S'il y a au moins un article sur le tapis
	 * 			FALSE	Sinon
	 */
	public boolean articlePresent(){
		return ordreArticles.size() > 0;
	}
	
	/**
	 * Permet de savoir le nombre d'articles sur le tapis
	 * 
	 * @return	Le nombre d'articles sur le tapis
	 */
	private int getNbArticlesTapis(){
		int nb = 0;
		
		for(Client c : tapis.keySet()){
			HashMap<Article, Integer> hm = tapis.get(c);
			
			for(Integer i : hm.values()){
				nb += i;
			}
		}
		
		return nb;
	}
	
	/**
	 * Permet de supprimer le premier client de la liste de clients pr�sents sur le tapis
	 */
	private void supprimerPremierClient(){
		Integer[] tab = (Integer[]) ordreClient.keySet().toArray(new Integer[ordreClient.size()]);
		rangerTabCroissant(tab);
		ordreClient.remove(tab[0]);
	}
	
	/**
	 * Permet de prendre le premier client courant qui a pos� un article sur le tapis
	 * 
	 * @return	Le premier client courant du tapis
	 */
	private Client prendrePremierClient(){
		Integer[] tab = (Integer[]) ordreClient.keySet().toArray(new Integer[ordreClient.size()]);
		rangerTabCroissant(tab);
		return tab.length > 0 ? ordreClient.get(tab[0]) : null;
	}
	
	/**
	 * Permet de ranger un tableau d'entiers par ordre croissant
	 * 
	 * @param tab	Le tableau � ranger
	 */
	private void rangerTabCroissant(Integer[] tab){
		for(int i = 0; i < tab.length; i++){
			for(int j = 0; j < tab.length; j++){
				if(tab[j] > tab[i]){
					int tmp = tab[j];
					tab[j] 	= tab[i];
					tab[i] 	= tmp;
				}
			}
		}
	}
}
