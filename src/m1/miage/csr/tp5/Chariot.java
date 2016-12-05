package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;

import m1.miage.csr.tp5.etat.chariot.EtatChariot;

public class Chariot {
	private static int ID_CHARIOT;
	
	private int idChariot;
	private Map<Article, Integer> articles;
	private EtatChariot etat;
	
	/**
	 * Le constructeur de la classe Chariot
	 */
	public Chariot(){
		idChariot = ID_CHARIOT++;
		articles = new HashMap<Article, Integer>();
		etat = new EtatChariot();
	}
	
	/**
	 * Permet de prendre l'état courant du chariot
	 * 
	 * @return	L'état courant du chariot
	 */
	public String getEtat(){
		return etat.getEtat();
	}
	
	/**
	 * Permet de prendre l'id du chariot
	 * 
	 * @return	L'id du chariot
	 */
	public int getId(){
		return idChariot;
	}
	
	/**
	 * Permet de changer l'état du chariot
	 * 
	 * @param etat	L'état du chariot
	 */
	public void changerEtat(String etat){
		this.etat.changerEtat(etat);
	}
	
	/**
	 * Permet d'ajouter [quantite] article [article]
	 * 
	 * @param article	L'article à ajouter au panier
	 * @param quantite	La quantité de l'article à ajouter
	 */
	public void ajouterArticle(Article article, int quantite){
		articles.put(article, (articles.containsKey(article) ? articles.get(article) + quantite : quantite));
	}
	
	/**
	 * Permet de prendre un article du chariot
	 * 
	 * @param article	L'article à prendre
	 * 
	 * @return	L'article et sa quantité
	 */
	public Map<Article, Integer> prendreArticle(Article article){
		HashMap<Article, Integer> h = new HashMap<Article, Integer>();
		
		if(articles.containsKey(article)){
			h.put(article, articles.get(article));
			articles.remove(article);
		}

		return h;
	}
	
	/**
	 * Permet de savoir s'il y a déjà l'article [a] dans le chariot
	 * 
	 * @param a	L'article dont on souhaite savoir s'il est déjà dans le panier
	 * 
	 * @return	TRUE	Si le chariot contient l'article
	 * 			FALSE	Sinon
	 */
	public boolean contientArticle(Article a){
		return articles.containsKey(a);
	}
	
	/**
	 * Prend la liste d'articles du chariot
	 * 
	 * @return	La liste d'articles du chariot
	 */
	public Map<Article, Integer> prendreArticle(){
		return articles;
	}
}
