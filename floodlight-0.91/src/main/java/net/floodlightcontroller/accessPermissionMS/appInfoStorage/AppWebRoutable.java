package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import net.floodlightcontroller.restserver.RestletRoutable;

public class AppWebRoutable implements RestletRoutable{
	public Restlet getRestlet(Context context) {
		// TODO Auto-generated method stub
	      Router router =new Router(context) ;  
	        router.attach("/apps/query", AppListResource.class) ;  
	        router.attach("/app/{id}", BaseResource.class);
	        router.attach("/permission/{id}", PermissionListResource.class);
	        router.attach("/permission/clear/{id}", PermissionClearAllResource.class);
	        return router ;  
	}  
      
    public String basePath(){  
        return "/vm/access" ;  
    }	
	
	
}
