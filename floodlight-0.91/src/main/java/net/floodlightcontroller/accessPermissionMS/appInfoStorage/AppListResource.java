package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class AppListResource extends ServerResource{
	
	protected void doInit() throws ResourceException {
		  
		}
	
	@Get
	public Representation get() {
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());
		List<AppIdentityCertificate> l=appDataBase.applicationFinderAll();
		return new  JacksonRepresentation<List<AppIdentityCertificate>>(l);
	}
	
	@Put
	public Representation put(Representation entity)  throws ResourceException {
	Form form = new Form(entity);
	AppIdentityCertificate app1 =new  AppIdentityCertificate();
	String appId = form.getFirstValue("appId");
	String appName = form.getFirstValue("appName");
	String appKey = form.getFirstValue("appKey");
	String registry = form.getFirstValue("registry");
	String registrationDate = form.getFirstValue("registrationDate");
	String expDate = form.getFirstValue("expDate");
	int ATL = Integer.parseInt(form.getFirstValue("ATL"));
	app1.setAppId(appId);
	app1.setAppName(appName);
	app1.setAppKey(appKey);
	app1.setRegistry(registry);
	app1.setRegistrationDate(registrationDate);
	app1.setExpDate(expDate);
	app1.setATL(ATL);
	IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());
	appDataBase.applicationRegistration(appId, app1);
	System.out.println(appDataBase.applicationFinder(appId).toString());
	
	return new JacksonRepresentation<AppIdentityCertificate>(app1);
	
	}
	
	@Post
	public Representation post(Representation entity) throws ResourceException {

		Form form = new Form(entity);
		AppIdentityCertificate app =new  AppIdentityCertificate();
		String appId = form.getFirstValue("appId");
		String appName = form.getFirstValue("appName");
		String appKey = form.getFirstValue("appKey");
		String registry = form.getFirstValue("registry");
		String registrationDate = form.getFirstValue("registrationDate");
		String expDate = form.getFirstValue("expDate");
		int ATL = Integer.parseInt(form.getFirstValue("ATL"));
		app.setAppId(appId);
		app.setAppName(appName);
		app.setAppKey(appKey);
		app.setRegistry(registry);
		app.setRegistrationDate(registrationDate);
		app.setExpDate(expDate);
		app.setATL(ATL);
		System.out.println(app.toString());
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());
	    appDataBase.applicationUpdate(appId, app);
	    return new JacksonRepresentation<AppIdentityCertificate>(app);
	}
	
}
