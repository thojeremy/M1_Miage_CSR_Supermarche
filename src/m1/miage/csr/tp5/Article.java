package m1.miage.csr.tp5;

public class Article {
	private static int ID_ARTICLE=0;
	
	private int idArticle;
	private String nom;
	
	/**
	 * Constructeur de la classe Article
	 * 
	 * @param nom	Le nom de l'article
	 */
	public Article(String nom){
		idArticle = ID_ARTICLE++;
		this.nom=nom;
	}
	
	/**
	 * Permet de prendre le nom de l'article
	 * 
	 * @return	Le nom de l'article
	 */
	public String getNom(){
		return nom;
	}

	/**
	 * Prend l'id de l'article
	 * 
	 * @return	L'id de l'article
	 */
	public int getIdArticle() {
		return idArticle;
	}
}
