package m1.miage.csr.tp5;

import java.util.HashMap;
import java.util.Map;

public class TapisDeCaisse {
	private Map<Client, Article> tapis;
	private int tailleTapis;
	
	public TapisDeCaisse(int tailleTapis)
	{
		tapis = new HashMap<Client, Article>();
		this.tailleTapis = tailleTapis;
	}
}
