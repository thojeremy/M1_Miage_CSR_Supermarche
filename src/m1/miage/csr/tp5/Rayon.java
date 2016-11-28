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
}
