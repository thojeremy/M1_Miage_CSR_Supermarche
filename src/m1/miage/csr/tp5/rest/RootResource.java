package m1.miage.csr.tp5.rest;

import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class RootResource extends ServerResource{
	
	public RootResource(){
		super();
	}

    @Get("html")
    public Representation getRootHtml() {
    	return new FileRepresentation("templates/index.html", MediaType.TEXT_HTML);
    }

}
