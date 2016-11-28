package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;

public class Chariot {
	private static int ID_CHARIOT = 0;
	private int idChariot;
	private Map<Article, Integer> articles;
	
	public Chariot()
	{
		idChariot = ID_CHARIOT++;
		articles = new HashMap<Article, Integer>();
	}
}
