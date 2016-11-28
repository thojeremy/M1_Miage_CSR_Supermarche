package m1.miage.csr.tp5;

public class Article {
	private static int ID_ARTICLE=0;
	
	private int idArticle;
	private String nom;
	
	public Article(String nom)
	{
		idArticle=ID_ARTICLE++;
		this.nom=nom;
	}
}
