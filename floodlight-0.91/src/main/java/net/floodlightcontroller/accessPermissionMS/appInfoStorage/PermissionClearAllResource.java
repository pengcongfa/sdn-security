package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.ServerResource;

public class PermissionClearAllResource extends ServerResource{
   private String id;
   protected void  doInit() {
	   id=(String)getRequestAttributes().get("id");
   }
	
   @Delete
   public Representation delete(Representation entity){
	IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());
    appDataBase.appPermissionSetClear(id);
    System.out.println("app"+id+"的权限集:"+appDataBase.appPermissionSetFinderAll(id)); 
	return new StringRepresentation(id+" clearAll  success");
   }
}
