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
			tapisDeCaisse.enleverArticle();
		}
	}

	/**
	 * Permet de prendre le nom de l'employé de caisse
	 * 
	 * @return	Le nom du client
	 */
	public String getNom() {
		return nom;
	}
}
