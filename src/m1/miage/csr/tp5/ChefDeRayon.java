package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;

import m1.miage.csr.tp5.etat.chef.EtatChef;

public class ChefDeRayon {
	private String nom;
	private EtatChef etat;
	private Map<Article, Integer> articles;
	
	public ChefDeRayon(String nom)
	{
		this.nom = nom;
		articles = new HashMap<Article, Integer>();
		
		etat = new EtatChef();
	}
}
