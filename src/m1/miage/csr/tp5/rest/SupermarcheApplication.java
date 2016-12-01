package m1.miage.csr.tp5.rest;

import java.io.File;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class SupermarcheApplication extends Application{

    public SupermarcheApplication(Context context){
        super(context);
    }

    @Override
    public Restlet createInboundRoot(){
    	File staticDirectory = new File("static/");
    	Directory directory = new Directory(getContext(), "file:///" + staticDirectory.getAbsolutePath() + "/");
    	directory.isDeeplyAccessible();
    	directory.isListingAllowed();
    	    	
        Router router = new Router(getContext());
        router.attach("/static", directory);
        
        router.attach("/supermarche"	, RootResource.class);
        router.attach("/supermarche/"	, RootResource.class);
        
        router.attach("/supermarche/clients"	, ClientsResource.class);
        router.attach("/supermarche/clients/"	, ClientsResource.class);
        
        router.attach("/supermarche/clients/{id}"	, ClientResource.class);
        router.attach("/supermarche/clients/{id}/"	, ClientResource.class);
        
        router.attach("/supermarche/stock"	, StockResource.class);
        router.attach("/supermarche/stock/"	, StockResource.class);
        
        return router;
    }

}
