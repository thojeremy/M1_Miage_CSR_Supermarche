package m1.miage.csr.tp5;

public class Rayon {
	private final static int RAYON_STOCK_INIT 	= 5;
	private final static int RAYON_STOCK_MAX 	= 15;
	
	private static int ID_RAYON = 0;
	private int idRayon;
	private Article article;
	private int stock;
	
	public Rayon(Article article)
	{
		this.article = article;
		this.stock = RAYON_STOCK_INIT;
		
		idRayon = ID_RAYON++;
	}
	
	public synchronized void prendreArticle(int nbArticle){
		while(stock - nbArticle < 0){
			try{wait();}catch(Exception e){}
		}
		
		stock -= nbArticle;
		
		System.out.println("PRENDRE ARTICLE RAYON> " + nbArticle + " " + article.getNom() + " (" + stock + " en stock après avoir pris)");
	}
	
	public synchronized int mettreArticle(int nbArticle){
		int articlesNonMis = 0;
		
		// On ajoute les articles
		stock += nbArticle;
		
		// On enlève (ou pas) le surplus
		articlesNonMis = stock - RAYON_STOCK_MAX;
		stock -= articlesNonMis;
		
		System.out.println("METTRE ARTICLE RAYON> " + (nbArticle - articlesNonMis) + " " + article.getNom() + " (" + stock + " en stock après avoir mis)");
		
		// On dit à tout le monde qu'on a mis des articles dans un rayon
		notifyAll();
		
		// On renvoie le nombre d'articles qu'on n'a pas utilisé
		return articlesNonMis;
	}
	
	public Article getArticle(){
		return article;
	}

	public int getIdRayon() {
		return idRayon;
	}
	
	public int getStock(){
		return stock;
	}
}
