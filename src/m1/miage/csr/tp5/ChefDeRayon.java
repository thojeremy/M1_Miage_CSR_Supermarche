package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import m1.miage.csr.tp5.etat.chef.EtatChef;

public class ChefDeRayon extends Thread{
	private String nom;
	private EtatChef etat;
	private Map<Article, Integer> articles;
	
	public ChefDeRayon(String nom)
	{
		this.nom = nom;
		articles = new HashMap<Article, Integer>();
		
		etat = new EtatChef();
	}
	
	public void prendreArticles(){
	}
	
	@Override
	public void run() {
		// On le fait travailler non-stop. Pas très cool pour le respect du temps de pause pour les salariés.
		// Il commence à l'entrepôt
		while(true){
			// On commence par prendre les articles dans l'entrepôt
			for(int i = 0; i < Supermarche.articles.length; i++){
				// Il prend un nombre aléatoire (entre 1 et 5) de l'article avec lui
				articles.put(Supermarche.articles[i], (new Random().nextInt(4) + 1));
			}
			
			// On marche vers les rayons pour les remplir
			etat.changerEtat(EtatChef.EN_RAYON);
			for(int i = 0; i < Supermarche.rayons.length; i++){
				marcherEntreLesRayons();
				
				// On met les articles en rayon...
				int articlesNonMis = Supermarche.rayons[i].mettreArticle(articles.get(Supermarche.rayons[i].getArticle()));
				
				// ... et on met le à jour le nombre d'articles que le chef de rayon a sur lui
				articles.put(Supermarche.rayons[i].getArticle(), articlesNonMis);
			}
			
			// On retourne dans l'entrepot
			etat.changerEtat(EtatChef.EN_ENTREPOT);
			marcherVersEntrepot();
		}
	}
	
	private void marcherEntreLesRayons()
	{
		try{sleep(Supermarche.TPS[Supermarche.TPS_DEPLACEMENT_RAYON_CHEF]);}catch(Exception e){}
	}
	
	private void marcherVersEntrepot()
	{
		try{sleep(Supermarche.TPS[Supermarche.TPS_ENTREPOT_CHEF]);}catch(Exception e){}
	}
}
