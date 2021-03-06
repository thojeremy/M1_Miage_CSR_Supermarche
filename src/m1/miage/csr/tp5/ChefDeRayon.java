package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import m1.miage.csr.tp5.etat.chef.EtatChef;

public class ChefDeRayon extends Thread{
	private String nom;
	private EtatChef etat;
	private Map<Article, Integer> articles;
	
	/**
	 * Le constructeur de la classe ChefDeRayon
	 * 
	 * @param nom	Le nom du chef de rayon
	 */
	public ChefDeRayon(String nom)
	{
		this.nom = nom;
		articles = new HashMap<Article, Integer>();
		
		etat = new EtatChef();
	}
	
	@Override
	public void run() {
		// On le fait travailler non-stop. Pas tr�s cool pour le respect du temps de pause pour les salari�s.
		// Il commence � l'entrep�t
		while(true){
			// On commence par prendre les articles dans l'entrep�t
			System.out.println("CHEF DE RAYON> Dans l'entrepot");
			for(int i = 0; i < Supermarche.articles.length; i++){
				// Il prend un nombre al�atoire (entre 1 et 5) de l'article avec lui
				articles.put(Supermarche.articles[i], (new Random().nextInt(4) + 1));
			}

			System.out.println("CHEF DE RAYON> Dans les rayons");
			// On marche vers les rayons pour les remplir
			etat.changerEtat(EtatChef.EN_RAYON);
			for(int i = 0; i < Supermarche.rayons.length; i++){
				marcherEntreLesRayons();

				System.out.println("CHEF DE RAYON> Dans le rayon des " + Supermarche.rayons[i].getArticle().getNom());
				
				// On met les articles en rayon...
				int articlesNonMis = Supermarche.rayons[i].mettreArticle(articles.get(Supermarche.rayons[i].getArticle()));
				
				// ... et on met le � jour le nombre d'articles que le chef de rayon a sur lui
				articles.put(Supermarche.rayons[i].getArticle(), articlesNonMis);
			}
			
			// On retourne dans l'entrepot
			etat.changerEtat(EtatChef.EN_ENTREPOT);
			marcherVersEntrepot();
		}
	}
	
	/**
	 * Permet d'attendre pendant que le chef de rayon marche entre les rayons
	 */
	private void marcherEntreLesRayons(){
		try{sleep(Supermarche.TPS[Supermarche.TPS_DEPLACEMENT_RAYON_CHEF]);}catch(Exception e){}
	}
	
	/**
	 * Permet d'attendre pendant que le chef de rayon attend en entrepot
	 */
	private void marcherVersEntrepot(){
		try{sleep(Supermarche.TPS[Supermarche.TPS_ENTREPOT_CHEF]);}catch(Exception e){}
	}

	/**
	 * Permet de prendre le nom du chef de rayon
	 * 
	 * @return	Le nom du chef de rayon
	 */
	public String getNom() {
		return nom;
	}
}
