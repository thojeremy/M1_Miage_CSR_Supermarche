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
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import m1.miage.csr.tp5.Client;
import m1.miage.csr.tp5.Supermarche;

public class ClientsResource extends ServerResource{
	
	public ClientsResource(){
		super();
	}

    @Get("html")
    public Representation getClientsHtml() {
    	return new FileRepresentation("templates/list-clients.html", MediaType.TEXT_HTML);
    }
	
	@Get("json")
	public Representation getClients() throws JSONException{
		Collection<Client> clients = Supermarche.clients;
		Collection<JSONObject> jsonClients = new ArrayList<JSONObject>();
		
		for(Client c : clients){
			JSONObject json = new JSONObject();
			
			json.put("id"	, c.getId());
			json.put("nom"	, c.getNom());
			json.put("etat"	, c.getEtat());
			json.put("nbArticlesDifferents", c.prendreNombreArticlesDifferents());
			json.put("url"	, "/supermarche/clients/" + c.getId());
			
			jsonClients.add(json);
		}
		
		JSONArray jsonArray = new JSONArray(jsonClients);
		
        JsonRepresentation json = new JsonRepresentation(jsonArray);
        json.setIndenting(true);
        
        return json;
	}

	@Post("json")
	public Representation addClient(JsonRepresentation representation) throws Exception{
		JSONObject object = representation.getJsonObject();
        int nombre = object.getInt("nombre");

		Collection<JSONObject> jsonClients = new ArrayList<JSONObject>();
        
        // Création du nombre d'utilisateurs si > 0
        if(nombre > 0){
        	ArrayList<Client> clients = Supermarche.genererClients(nombre);
        	
        	for(Client c : clients){
    			JSONObject json = new JSONObject();
    			
    			// On stocke ses infos dans un json qui sera ajouté à un plus grand tableau
    			
    			json.put("id"	, c.getId());
    			json.put("nom"	, c.getNom());
    			json.put("etat"	, c.getEtat());
    			json.put("url"	, "/supermarche/clients/" + c.getId());
    			
    			// On le lance
    			c.start();
    			
    			jsonClients.add(json);
        	}
        }
		
		JSONArray jsonArray = new JSONArray(jsonClients);
		
        JsonRepresentation json = new JsonRepresentation(jsonArray);
        json.setIndenting(true);
        
        return json;
	}
}
