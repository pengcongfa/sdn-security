package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class PermissionListManage{
private HashMap<String,PermissionSet> permissionList=new HashMap<String,PermissionSet> ();

public void addAllPermissionsToSet(String appId,PermissionSet permissionCatalog){
	permissionList.put(appId, permissionCatalog);
}

public void addPermissionToSet(String appId,PermissionType permission){
	if(permissionList.get(appId)==null){
		PermissionSet permissionCatalog=new PermissionSet();
		permissionCatalog.addPermissionToSet(permission);
		permissionList.put(appId, permissionCatalog);
	}
	else{
		permissionList.get(appId).addPermissionToSet(permission);
	}
}  

public void deletePermissionFromSet(String appId,PermissionType permission){
	permissionList.get(appId).removePermissionInSet(permission);
}

public Boolean isPermitted(String appId,PermissionType permission){
	return permissionList.get(appId).permissionIsHave(permission);
}

public ArrayList<PermissionType> appPermissionSetFinderAll(String appId){
	return permissionList.get(appId).appPermissionSetFinder();
}

public   void deleteALLPermissionFromSet(String appId){
	permissionList.get(appId).ClearPermissionSet();
}

public String toString(){
	String l="";
	Iterator iter=permissionList.entrySet().iterator();
	while(iter.hasNext()){
		Map.Entry entry=(Map.Entry) iter.next();
		Object key=entry.getKey();
		Object val=entry.getValue();
		String keyy=(String)key;
		PermissionSet permissionset=(PermissionSet)val;
		l=l+"appid:"+keyy+"\r\npermissions:"+permissionset.toString()+"\r\n";
	}
	return l;
}
}

