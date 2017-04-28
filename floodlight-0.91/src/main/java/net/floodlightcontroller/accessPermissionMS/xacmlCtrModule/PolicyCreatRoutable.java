package net.floodlightcontroller.accessPermissionMS.xacmlCtrModule;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import net.floodlightcontroller.accessPermissionMS.appInfoStorage.AppListResource;
import net.floodlightcontroller.restserver.RestletRoutable;

public class PolicyCreatRoutable  implements RestletRoutable{

	@Override
	public Restlet getRestlet(Context context) {
		// TODO Auto-generated method stub
		  Router router =new Router(context) ;  
		  router.attach("/creatpolicy", PolicyCreatResource.class) ;  
	     return router;
	}

	@Override
	public String basePath() {
		// TODO Auto-generated method stub
		return "/vm/xacml";
	}

}
