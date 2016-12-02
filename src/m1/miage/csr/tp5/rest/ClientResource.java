package m1.miage.csr.tp5.rest;

import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.service.StatusService;

import m1.miage.csr.tp5.Article;
import m1.miage.csr.tp5.Client;
import m1.miage.csr.tp5.Supermarche;

public class ClientResource extends ServerResource {
	
	private final Status STT = new Status(Status.CLIENT_ERROR_NOT_FOUND, "Client non trouv�");
	
	public ClientResource(){
		super();
	}

    @Get("html")
    public Representation getClientsHtml() {
        String id = (String) getRequest().getAttributes().get("id");
        int idClient = Integer.valueOf(id);
        
        // Si l'id n'est pas bon...
        if(idClient >= Supermarche.clients.size() || idClient < 0){
    		return new StatusService().getRepresentation(STT, getRequest(), getResponse());
    	} else {
    		return new FileRepresentation("templates/client.html", MediaType.TEXT_HTML);
    	}
    }

	@Get("json")
	public Representation getClient() throws Exception{
        String id = (String) getRequest().getAttributes().get("id");
        int idClient = Integer.valueOf(id);
        
        // Si l'id n'est pas bon...
        if(idClient >= Supermarche.clients.size() || idClient < 0){
        	// ... On produit une erreur 404
        	setStatus(STT);
        	//throw new ResourceException(STT);
        	return new StatusService().getRepresentation(STT, getRequest(), getResponse());
        } else {
        	// ... Sinon on cherche ses infos
    		JSONObject json = new JSONObject();

        	Client client = Supermarche.clients.get(idClient);
    		
    		json.put("id"	, client.getId());
    		json.put("nom"	, client.getNom());
    		json.put("etat"	, client.getEtat());
    		json.put("nbArticlesDifferents", client.prendreNombreArticlesDifferents());
    		
    		// La liste de courses
    		JSONObject liste = new JSONObject();
    		
    		int i = 0;
    		for(Article a : client.getListe().keySet()){
    			JSONObject article = new JSONObject();
    			
    			article.put("nom"		, a.getNom());
    			article.put("quantite"	, client.getListe().get(a));
    			
    			liste.put(Integer.toString(i), article);
    			i++;
    		}
    		
    		json.put("liste", liste);
    		
    		// La liste de courses � prendre
    		liste = new JSONObject();
    		
    		for(Article a : client.getListeAPrendre().keySet()){
    			JSONObject article = new JSONObject();
    			
    			article.put("nom",	a.getNom());
    			article.put("quantite"	, client.getListe().get(a));
    			
    			liste.put("article", article);
    		}
    		
    		json.put("listeAPrendre", liste);
    		
    		// Construction de la repr�sentation

            JsonRepresentation result = new JsonRepresentation(json);
            result.setIndenting(true);
            
            return result;
        }
	}
}
