package m1.miage.csr.tp5;

import java.util.Random;

public class Supermarche {
	public final static int NB_ARTICLES			= 4;
	public final static int NB_RAYONS			= 4;
	public final static int NB_CHARIOTS 		= 5;
	public final static int NB_CLIENTS			= 42;
	
	public final static String[] NOM_ARTICLE	= {"Pain au chocolat au chocolat blanc de Budapest, saupoudr�s de petites paillettes de sucre", "Mikados gout banane de Martinique, flamb�e", "Chocolatine au chocolat noir de Cuba", "Pain aux raisins verts cultiv�s au bon soleil de Bordeaux"};
	
	public final static int TPS_ENTREPOT_CHEF 				= 0;
	public final static int TPS_DEPLACEMENT_RAYON_CHEF 		= 1;
	public final static int TPS_DEPLACEMENT_RAYON_CLIENT 	= 2;
	public final static int TPS_ARTICLE_TAPIS 				= 3;
	
	public final static int[] TPS 				= {500, 200, 300, 20};
	private final static int TAILLE_TAPIS 		= 10;
	
	public static Client[] 		clients;
	public static Article[] 	articles;
	public static Rayon[]		rayons;
	public static FileChariots	chariots;
	
	public static ChefDeRayon 		chefDeRayon;
	public static EmployeDeCaisse	employeDeCaisse;
	public static TapisDeCaisse	tapisDeCaisse;
	
	private static void genererArticles()
	{
		articles = new Article[NB_ARTICLES];
		
		// G�n�ration des articles
		for(int i = 0; i < NB_ARTICLES; i++){
			articles[i] = new Article(NOM_ARTICLE[i]);
		}
	}
	
	private static void genererRayons()
	{
		rayons = new Rayon[NB_RAYONS];
		// G�n�ration des rayons
		for(int i = 0; i < NB_RAYONS; i++){
			rayons[i] = new Rayon(articles[i]);
		}
	}
	
	private static void genererChariots()
	{
		// G�n�ration des chariots
		chariots = new FileChariots(NB_CHARIOTS);
	}
	
	private static void genererClients()
	{
		clients = new Client[NB_CLIENTS];
		
		// G�n�ration des clients
		for (int i = 0; i < NB_CLIENTS; i++)
		{
			// G�n�ration du nom al�atoire
			String nom = "";
			int nbLettres = new Random().nextInt(7) + 1;
			
			for(int j = 0; j < nbLettres; j++){
				nom += (nom.length() == 0 ? ( (char) (new Random().nextInt(25) + 65)) : ((char) (new Random().nextInt(25) + 97)) );
			}
			
			// G�n�ration du client
			clients[i] = new Client(nom);
		}
	}
	
	public static void main(String[] args) {
		genererArticles();
		genererRayons();
		genererChariots();
		genererClients();
		
		// On cr�e le chef de rayon...
		ChefDeRayon chefDeRayon = new ChefDeRayon("Ali Baba");
		// ... en lui disant qu'il arr�tera de travailler seulement quand il n'y aura plus de clients...
		//chefDeRayon.setDaemon(true);
		// ... et on lui dit de commencer
		chefDeRayon.start();
		
		// On cr�e le tapis de caisse
		tapisDeCaisse = new TapisDeCaisse(TAILLE_TAPIS);
		
		// On cr�e l'employ� de caisse...
		employeDeCaisse = new EmployeDeCaisse("Aladdin", tapisDeCaisse);
		// ... en le faisant s'arr�ter seulement lorsqu'il n'y aura plus de clients...
		//employeDeCaisse.setDaemon(true);
		// ... et on lui dit de commencer
		employeDeCaisse.start();
		
		// On dit aux clients qu'ils peuvent venir
		for(int i = 0; i < NB_CLIENTS; i++){
			clients[i].start();
		}
	}
}
