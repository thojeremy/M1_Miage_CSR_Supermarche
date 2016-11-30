package m1.miage.csr.tp5;

public class EmployeDeCaisse extends Thread{
	private String nom;
	private TapisDeCaisse tapisDeCaisse;
	
	public EmployeDeCaisse(String nom, TapisDeCaisse tapisDeCaisse){
		this.nom = nom;
		this.tapisDeCaisse = tapisDeCaisse;
	}
	
	@Override
	public void run() {
		while(true){
			attendreArticleSurTapis();
			tapisDeCaisse.enleverArticle();
		}
	}

	public String getNom() {
		return nom;
	}
	
	public void attendreArticleSurTapis()
	{
		try{sleep(Supermarche.TPS[Supermarche.TPS_ARTICLE_TAPIS]);}catch(Exception e){}
	}
}
