package m1.miage.csr.tp5;

import java.util.Random;

public class Supermarche {
	private final static int TPS_ENTREPOT_CHEF 				= 0;
	private final static int TPS_DEPLACEMENT_RAYON_CHEF 	= 1;
	private final static int TPS_DEPLACEMENT_RAYON_CLIENT 	= 2;
	private final static int TPS_ARTICLE_TAPIS 				= 3;
	
	private final static int RAYON_STOCK_INIT 	= 5;
	private final static int RAYON_STOCK_MAX 	= 15;
	private final static int NB_RAYONS			= 4;
	private final static int[] TPS 				= {500, 200, 300, 20};
	private final static int TAILLE_TAPIS 		= 10;
	private final static int NB_CHARIOTS 		= 5;
	
	private final static String[] NOM_ARTICLE	= {"Pain au chocolat au chocolat blanc de Budapest, saupoudrés de petites paillettes de sucre", "Mikados gout banane de Martinique, flambée", "Chocolatine au chocolat noir de Cuba", "Pain aux raisins verts cultivés au bon soleil de Bordeaux"};
	
	private static Client[] 	clients;
	private static Article[] 	articles;
	private static Rayon[]		rayons;
	private static Chariot[]	chariots;
	
	private static ChefDeRayon 		chefDeRayon;
	private static EmployeDeCaisse	employeDeCaisse;
	private static TapisDeCaisse	tapisDeCaisse;
	
	private static void genererArticles()
	{
	}
	
	private static void genererRayons()
	{
	}
	
	private static void genererChariots()
	{
	}
	
	private static void genererClients()
	{
		int nb = new Random().nextInt(24) + 18;
		for (int i=0;i<=nb; i++)
		{
			String nom = "";
			int nbLettres = new Random().nextInt(7) + 1;
			for(int j = 0; j < nbLettres; j++){
				nom += (nom.length() == 0 ? ( (char) (new Random().nextInt(25) + 65)) : ((char) (new Random().nextInt(25) + 97)) );
			}
			System.out.println(nom);
			//clients[i] = new Client(nom);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		genererClients();		
	}
}
