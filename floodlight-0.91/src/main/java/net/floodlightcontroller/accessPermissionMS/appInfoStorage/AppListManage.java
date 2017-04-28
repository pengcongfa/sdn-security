package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AppListManage {
	
private HashMap<String,AppIdentityCertificate>  appList=new HashMap<String,AppIdentityCertificate>();

public  void addToAppList(String appId,AppIdentityCertificate appInfo){
	appList.put(appId, appInfo);
}


public  void deleteFromAppList(String appId){
	appList.remove(appId);
}

public AppIdentityCertificate selectFromAppList(String appId){
	return appList.get(appId);
}


public List<AppIdentityCertificate>  selectAllFromAppList(){
	List<AppIdentityCertificate> apps= new ArrayList<AppIdentityCertificate>();
         Iterator iter = appList.entrySet().iterator();
         while (iter.hasNext()) {
	         Map.Entry entry = (Map.Entry) iter.next();
	         //Object key = entry.getKey();
	         Object val = entry.getValue();
	         apps.add( (AppIdentityCertificate) val );
         }
	return apps;
}

public void updateAppList(String appId,AppIdentityCertificate appInfo){
	appList.remove(appId);
	appList.put(appId, appInfo);
}
}
