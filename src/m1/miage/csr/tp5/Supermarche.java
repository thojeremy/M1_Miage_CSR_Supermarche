package m1.miage.csr.tp5;

import java.util.ArrayList;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

import m1.miage.csr.tp5.rest.SupermarcheApplication;

public class Supermarche {
	public final static int NB_ARTICLES			= 4;
	public final static int NB_RAYONS			= 4;
	public final static int NB_CHARIOTS 		= 5;
	public final static int NB_CLIENTS			= 42;
	
	public final static String[] NOM_ARTICLE	= {"Pain au chocolat au chocolat blanc de Budapest, saupoudrés de petites paillettes de sucre", "Mikados gout banane de Martinique, flambée", "Chocolatine au chocolat noir de Cuba", "Pain aux raisins verts cultivés au bon soleil de Bordeaux"};
	
	public final static int TPS_ENTREPOT_CHEF 				= 0;
	public final static int TPS_DEPLACEMENT_RAYON_CHEF 		= 1;
	public final static int TPS_DEPLACEMENT_RAYON_CLIENT 	= 2;
	public final static int TPS_ARTICLE_TAPIS 				= 3;
	
	public final static int[] TPS 				= {500, 200, 300, 20};
	private final static int TAILLE_TAPIS 		= 10;
	
	public static ArrayList<Client>	clients;
	public static Article[] 		articles;
	public static Rayon[]			rayons;
	public static FileChariots		chariots;
	
	public static ChefDeRayon 		chefDeRayon;
	public static EmployeDeCaisse	employeDeCaisse;
	public static TapisDeCaisse	tapisDeCaisse;
	
	/**
	 * Permet de générer des articles
	 */
	private static void genererArticles(){
		articles = new Article[NB_ARTICLES];
		
		// Génération des articles
		for(int i = 0; i < NB_ARTICLES; i++){
			articles[i] = new Article(NOM_ARTICLE[i]);
		}
	}
	
	/**
	 * Permet de générer des rayons
	 */
	private static void genererRayons(){
		rayons = new Rayon[NB_RAYONS];
		// Génération des rayons
		for(int i = 0; i < NB_RAYONS; i++){
			rayons[i] = new Rayon(articles[i]);
		}
	}
	
	/**
	 * Permet de générer des chariots
	 */
	private static void genererChariots(){
		// Génération des chariots
		chariots = new FileChariots(NB_CHARIOTS);
	}
	
	/**
	 * Permet de générer le nombre de clients jusqu'à NB_CLIENTS
	 */
	private static void genererClients(){
		clients = new ArrayList<Client>();
		
		// Génération des clients
		for (int i = 0; i < NB_CLIENTS; i++){			
			// Génération du client
			clients.add(new Client());
		}
	}
	
	/**
	 * Permet de générer [nb] clients et renvoie les clients fraichement générés
	 * 
	 * @param nb	Le nombre de clients
	 * 
	 * @return		Les clients fraichement générés
	 */
	public static ArrayList<Client> genererClients(int nb){
		ArrayList<Client> res = new ArrayList<Client>();
		
		for(int i = 0; i < nb; i++){
			Client c = new Client();
			
			res.add(c);
			clients.add(c);
		}
		
		return res;
	}
	
	/**
	 * Permet d'afficher les clients
	 */
	private static void afficherClients(){
		System.out.println("Nombre de clients qui vont initialement entrer dans le magasin : " + clients.size());
		
		for(Client c : clients){
			System.out.println("> " + c.getNom());
		}
	}
	
	public static void main(String[] args) {
		genererArticles();
		genererRayons();
		genererChariots();
		genererClients();
		
		afficherClients();
		
		// On crée le chef de rayon...
		ChefDeRayon chefDeRayon = new ChefDeRayon("Ali Baba");
		// ... en lui disant qu'il arrêtera de travailler seulement quand il n'y aura plus de clients...
		//chefDeRayon.setDaemon(true);
		// ... et on lui dit de commencer
		chefDeRayon.start();
		
		// On crée le tapis de caisse
		tapisDeCaisse = new TapisDeCaisse(TAILLE_TAPIS);
		
		// On crée l'employé de caisse...
		employeDeCaisse = new EmployeDeCaisse("Aladdin", tapisDeCaisse);
		// ... en le faisant s'arrêter seulement lorsqu'il n'y aura plus de clients...
		//employeDeCaisse.setDaemon(true);
		// ... et on lui dit de commencer
		employeDeCaisse.start();
		
		// ================= SERVICE REST =================
		// On initialise le service rest...
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 5000);
        component.getClients().add(Protocol.FILE);

        Application application = new SupermarcheApplication(context);

        component.getDefaultHost().attach(application);
       
        // ... et on le lance
        try {component.start();} catch (Exception e) {}
        // ================= SERVICE REST =================
		
		// On dit aux clients qu'ils peuvent venir
		for(Client c : clients){
			c.start();
		}
	}
}
