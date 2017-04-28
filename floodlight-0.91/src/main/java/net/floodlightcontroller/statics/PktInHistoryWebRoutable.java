package net.floodlightcontroller.statics;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

import net.floodlightcontroller.restserver.RestletRoutable;

public class PktInHistoryWebRoutable implements RestletRoutable {
	public Restlet getRestlet(Context context) {
		// TODO Auto-generated method stub
	      Router router =new Router(context) ;  
	        router.attach("/pktinhistory/json", PktInHistoryResource.class) ;  
	        router.attach("/student/{studentId}", StudentResource.class);
	        return router ;  
	}  
      
    public String basePath(){  
        return "/vm/statics" ;  
    }	
}
