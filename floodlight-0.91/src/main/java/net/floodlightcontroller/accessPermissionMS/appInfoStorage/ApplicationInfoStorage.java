package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.restserver.IRestApiService;

public class ApplicationInfoStorage implements IFloodlightModule,IAppInfoStorageService{
	   private AppListManage appList=new AppListManage();
	   private PermissionListManage permissionList=new PermissionListManage();
	   protected IRestApiService restApi ;  
	   
	@Override
	   public String  test(){
		   return "模块调用";
	   }
	@Override
	public void applicationRegistration(String appId, AppIdentityCertificate application) {
		// TODO Auto-generated method stub
		appList.addToAppList(appId, application);
		System.out.println("applicationRegistration");
	}

	@Override
	public void applicationCancellation(String appId) {
		// TODO Auto-generated method stub
		System.out.println("applicationCancellation");
		appList.deleteFromAppList(appId);
	}

	@Override
	public AppIdentityCertificate applicationFinder(String appId) {
		// TODO Auto-generated method stub
		System.out.println("applicationFinder");
		return appList.selectFromAppList(appId);
	}

	@Override
	public List<AppIdentityCertificate> applicationFinderAll() {
		// TODO Auto-generated method stub
		System.out.println("applicationFinderAll");
		return appList.selectAllFromAppList();
	}

	@Override
	public void applicationUpdate(String appId, AppIdentityCertificate application) {
		// TODO Auto-generated method stub
		System.out.println("applicationUpdate");
		appList.updateAppList(appId, application);
	}

	
	@Override
	public void appPermissionSetInitialization(String appId, PermissionSet permissionCatalog) {
		// TODO Auto-generated method stub
		System.out.println("PermissionSetInitialization");
		permissionList.addAllPermissionsToSet(appId, permissionCatalog);
	}
	
	@Override
	public void appPermissionSetAdd(String appId, PermissionType permission) {
		// TODO Auto-generated method stub
		System.out.println("PermissionSetAdd");
		permissionList.addPermissionToSet(appId, permission);
	}
	
	@Override
	public void appPermissionSetDelete(String appId, PermissionType permission) {
		// TODO Auto-generated method stub
		System.out.println("PermissionSetDelete");
		permissionList.deletePermissionFromSet(appId, permission);
	}
	
	@Override
	public Boolean appPermissionSetFinder(String appId,PermissionType permission){
		// TODO Auto-generated method stub
		System.out.println("PermissionSetFinder");
		boolean l=permissionList.isPermitted(appId, permission);
		return l;
	}
	
	@Override
	public ArrayList<PermissionType> appPermissionSetFinderAll(String appId) {
		// TODO Auto-generated method stub
		System.out.println("PermissionSetFinderAll");
		ArrayList<PermissionType> m=permissionList.appPermissionSetFinderAll(appId);
		return m;
	}
	
	@Override
	public void appPermissionSetClear(String appId) {
		// TODO Auto-generated method stub
		System.out.println("PermissionSetClear");
		permissionList.deleteALLPermissionFromSet(appId);
	}
	
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		 Collection<Class<? extends IFloodlightService>> l =   
	                new ArrayList<Class<? extends IFloodlightService>>();  
	        l.add(IAppInfoStorageService.class);  
	        return l;  
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		Map<Class<? extends IFloodlightService>,IFloodlightService> m =   
                new  HashMap<Class<? extends IFloodlightService>,IFloodlightService>();  
        m.put(IAppInfoStorageService.class,this);  
        return m;  
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		// TODO Auto-generated method stub
		 Collection<Class<? extends IFloodlightService>> l =   
	                new ArrayList<Class<? extends IFloodlightService>>() ;  
	        l.add(IRestApiService.class);  
	        return l ;
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
	        restApi = context.getServiceImpl(IRestApiService.class) ;  
	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
		 restApi.addRestletRoutable(new AppWebRoutable());
			AppIdentityCertificate app1=new  AppIdentityCertificate();
			AppIdentityCertificate app2=new  AppIdentityCertificate();
			app1.set("1","default","1","admin","2017-3-1","2020-3-16",1);
			app2.set("2"," NetworkSwitchCheck","pcf199502","chinaMobile","2017-3-16","2018-6-20",2);
			appList.addToAppList("1", app1);
			appList.addToAppList("circuitpusher", app2);
			PermissionSet permissionCatalog1=new PermissionSet();
			PermissionSet permissionCatalog2=new PermissionSet();
			permissionCatalog1.addPermissionToSet(PermissionType.read_topology);
			permissionCatalog1.addPermissionToSet(PermissionType.read_all_flow);
			permissionCatalog2.addPermissionToSet(PermissionType.read_statistics);
			permissionCatalog2.addPermissionToSet(PermissionType.read_all_flow);		
			permissionCatalog2.addPermissionToSet(PermissionType.read_controller_info);
		    permissionList.addAllPermissionsToSet(app1.getAppId(), permissionCatalog1);   //方法1
		    permissionList.addAllPermissionsToSet(app2.getAppId(), permissionCatalog2);
		   // System.out.println(appAuthentication("1", "1"));
		 //   System.out.println(appAuthentication("1", "2"));
		//    System.out.println(appAuthentication("100", "2"));
	//		System.out.println(permissionList.toString());
	//		permissionList.addPermissionToSet(app1.getAppId(), PermissionType.read_all_flow); //方法2
			//permissionList.deletePermissionFromSet(app2.getAppId(), permissionType.read_all_flow);//方法3
	//		System.out.println(permissionList.toString());
	//		System.out.println("查找结果："+permissionList.isPermitted(app1.getAppId(), PermissionType.read_all_flow)); //方法4
	//		System.out.println("app1的权限集:"+permissionList.appPermissionSetFinderAll(app1.getAppId()));                   //方法5
			//permissionList.deleteALLPermissionFromSet(app2.getAppId());           //方法6
	//		System.out.println(permissionList.toString());
	}
	@Override
	public Boolean appAuthentication(String appId, String appKey) {
		// TODO Auto-generated method stub
	
		if(appList.selectFromAppList(appId)!=null){
			AppIdentityCertificate application=appList.selectFromAppList(appId);
			if(application.getAppKey().equals(appKey)){
				return true;
				}	
			}
		return false;
		}
	@Overrideappkey
	public String getRegistryByAppId(String appId) {
		// TODO Auto-generated method stub
		String registry=appList.selectFromAppList(appId).getRegistry();
		return registry;
	}
}
