package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.python.constantine.platform.windows.LastError;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class PermissionListResource extends ServerResource{
	private String id;
	
	protected void  doInit() throws ResourceException{
		id=(String)getRequestAttributes().get("id");
		System.out.println("appid:    "+id);
	}
	
	@Get
	public Representation get(){
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());  
		ArrayList<PermissionType> l=appDataBase.appPermissionSetFinderAll(id);
		ArrayList<String> ss=new ArrayList<String>();
		for(int i=0;i<l.size();i++){
	         ss.add(l.get(i).toString());
	}
		System.out.println(ss.toString());
		return new JacksonRepresentation<ArrayList<String> >(ss);
		//return new StringRepresentation(id+"  read_topology");
	}
	
	@Put
	public Representation put(Representation entity){
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());  
		JacksonRepresentation<ArrayList> p=new  JacksonRepresentation< ArrayList>(entity,ArrayList.class);
		try {
		    ArrayList<String> l=p.getObject();
			PermissionSet permissionCatalog=new PermissionSet(); 
			for(int i=0;i<l.size();i++){
				PermissionType mPermissionType=PermissionType.valueOf(l.get(i));
				permissionCatalog.addPermissionToSet(mPermissionType);
	     	}
			System.out.println(l.toString());
			System.out.println(permissionCatalog.toString());
			//System.out.println(permissionCatalog.toString());
			appDataBase.appPermissionSetInitialization(id, permissionCatalog);
			ArrayList<PermissionType> s=appDataBase.appPermissionSetFinderAll(id);
			ArrayList<String> ss=new ArrayList<String>();
			for(int i=0;i<s.size();i++){
			         ss.add(s.get(i).toString());
			}
			//System.out.println("app"+id+"的权限集:"+s); 
			//System.out.println("app"+id+"是否拥有权限read_statistics："+appDataBase.appPermissionSetFinder(id, PermissionType.read_statistics)); 
			//System.out.println("app"+id+"是否拥有权限pkt_in_event："+appDataBase.appPermissionSetFinder(id, PermissionType.pkt_in_event)); 
			return new JacksonRepresentation<ArrayList<String> >(ss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  return new StringRepresentation(id+"  put  error");
		}
	}
	
	@Post
	public Representation post(Representation entity){
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());  
		JacksonRepresentation<ArrayList> p=new  JacksonRepresentation< ArrayList>(entity,ArrayList.class);
		  try {
			ArrayList<String> l=p.getObject();
			for(int i=0;i<l.size();i++){
				PermissionType mPermissionType=PermissionType.valueOf(l.get(i));
				appDataBase.appPermissionSetAdd(id, mPermissionType);
	     	}
			ArrayList<PermissionType> s=appDataBase.appPermissionSetFinderAll(id);
			ArrayList<String> ss=new ArrayList<String>();
			for(int i=0;i<s.size();i++){
			         ss.add(s.get(i).toString());
			}
			return new JacksonRepresentation<ArrayList<String> >(ss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  return new StringRepresentation(id+"  post  error");
		}
	}
	
	@Delete
	public Representation delete(Representation entity) throws IOException {
		IAppInfoStorageService appDataBase = (IAppInfoStorageService) getContext().getAttributes().get(IAppInfoStorageService.class.getCanonicalName());  
		JacksonRepresentation<ArrayList> p=new  JacksonRepresentation< ArrayList>(entity,ArrayList.class);
		  try {
			ArrayList<String> l=p.getObject();
			for(int i=0;i<l.size();i++){
				PermissionType mPermissionType=PermissionType.valueOf(l.get(i));
				appDataBase.appPermissionSetDelete(id, mPermissionType);
	     	}
			ArrayList<PermissionType> s=appDataBase.appPermissionSetFinderAll(id);
			ArrayList<String> ss=new ArrayList<String>();
			for(int i=0;i<s.size();i++){
			         ss.add(s.get(i).toString());
			}
			return new JacksonRepresentation<ArrayList<String> >(ss);
		}
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  return new StringRepresentation(id+"  delete  error");
		}
	}
}
