package m1.miage.csr.tp5.rest;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import m1.miage.csr.tp5.Client;

public class ClientResource extends ServerResource{
	
	@Get("json")
	public Representation toJson() throws JSONException{
		Client c = new Client("Jafar");
		
		JSONObject json = new JSONObject();
		json.put("id", c.getId());
		json.put("nom", c.getNom());
		json.put("etat", c.getEtat());
		json.put("timestamp", System.currentTimeMillis());
		
		return new JsonRepresentation(json);
	}
	
	@Get("xml")
	public Representation toHTML() throws IOException{
		Client c = new Client("Simba");
		
		DomRepresentation representation = new DomRepresentation();
		representation.setIndenting(true);
		
		Document doc = representation.getDocument();
		
		// On crée la balise client
		Node objet = doc.createElement("client");
		doc.appendChild(objet);
		
		// On ajoute l'id
		Node id = doc.createElement("id");
		id.setTextContent(c.getId() + "");
		objet.appendChild(id);
		
		// On ajoute le nom
		Node nom = doc.createElement("nom");
		nom.setTextContent(c.getNom());
		objet.appendChild(nom);
		
		// On ajoute l'état
		Node etat = doc.createElement("etat");
		nom.setTextContent(c.getEtat());
		objet.appendChild(etat);
		
		// On ajoute le timestamp
		Node timestamp = doc.createElement("timestamp");
		timestamp.setTextContent(System.currentTimeMillis() + "");
		objet.appendChild(timestamp);
		
		return representation;
	}
}
