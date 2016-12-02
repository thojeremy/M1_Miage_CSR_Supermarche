package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;

import m1.miage.csr.tp5.etat.chariot.EtatChariot;

public class Chariot {
	private static int ID_CHARIOT;
	
	private int idChariot;
	private Map<Article, Integer> articles;
	private EtatChariot etat;
	
	public Chariot()
	{
		idChariot = ID_CHARIOT++;
		articles = new HashMap<Article, Integer>();
		etat = new EtatChariot();
	}
	
	public String getEtat()
	{
		return etat.getEtat();
	}
	
	public int getId(){
		return idChariot;
	}
	
	public void changerEtat(String etat){
		this.etat.changerEtat(etat);
	}
	
	public void ajouterArticle(Article article, int quantite){
		articles.put(article, (articles.containsKey(article) ? articles.get(article) + quantite : quantite));
	}
	
	public Map<Article, Integer> prendreArticle(Article article){
		HashMap<Article, Integer> h = new HashMap<Article, Integer>();
		
		if(articles.containsKey(article)){
			h.put(article, articles.get(article));
			articles.remove(article);
		}

		return h;
	}
	
	public boolean contientArticle(Article a){
		return articles.containsKey(a);
	}
	
	public Map<Article, Integer> prendreArticle()
	{
		return articles;
	}
}
