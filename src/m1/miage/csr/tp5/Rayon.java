package m1.miage.csr.tp5;

public class Rayon {
	private static int ID_RAYON = 0;
	private int idRayon;
	private Article article;
	private int stock;
	private int stockMax;
	
	public Rayon(Article article, int stock, int stockMax)
	{
		this.article = article;
		this.stock = stock;
		this.stockMax = stockMax;
		
		idRayon = ID_RAYON++;
	}
}
