package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class BaseResource extends ServerResource{
	private String id;
	
	protected void doInit() throws ResourceException {
		     id = (String) getRequestAttributes().get("id");
		}
	
	@Get
	public Representation get() {
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());  
		AppIdentityCertificate app=appDataBase.applicationFinder(id);
		return new JacksonRepresentation<AppIdentityCertificate>(app);
	}
	
	@Delete
	public Representation delete() {
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());  
		appDataBase.applicationCancellation(id);
		return new StringRepresentation("delete  success******"+"id:"+id);
	}
}
