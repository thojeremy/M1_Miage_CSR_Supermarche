package m1.miage.csr.tp5.rest;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import m1.miage.csr.tp5.Supermarche;

public class StockResource extends ServerResource{
	
	public StockResource() {
		super();
	}

    @Get("html")
    public Representation getClientsHtml() {
    	return new FileRepresentation("templates/list-stock.html", MediaType.TEXT_HTML);
    }

	@Get("json")
	public Representation getClients() throws JSONException{
		Collection<JSONObject> jsonStock = new ArrayList<JSONObject>();
		
		for(int i = 0; i < Supermarche.rayons.length; i++){
			JSONObject json = new JSONObject();
			
			json.put("id"		, Supermarche.rayons[i].getIdRayon());
			json.put("article"	, Supermarche.rayons[i].getArticle().getNom());
			json.put("quantite"	, Supermarche.rayons[i].getStock());
			
			jsonStock.add(json);
		}
		
		JSONArray jsonArray = new JSONArray(jsonStock);
		
        JsonRepresentation json = new JsonRepresentation(jsonArray);
        json.setIndenting(true);
        
        return json;
	}
}
