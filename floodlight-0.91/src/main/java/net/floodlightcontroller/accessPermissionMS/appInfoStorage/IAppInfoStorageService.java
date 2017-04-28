package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.util.ArrayList;
import java.util.List;

import net.floodlightcontroller.core.module.IFloodlightService;

public interface IAppInfoStorageService  extends IFloodlightService{
	public void applicationRegistration(String appId,AppIdentityCertificate application);  //注册新应用
	public void applicationCancellation(String appId);                                                                     //注销应用
	public AppIdentityCertificate applicationFinder(String appId);                                              //查找指定应用
    public List<AppIdentityCertificate>  applicationFinderAll();                                                    //查找所有应用信息
    public  void applicationUpdate(String appId,AppIdentityCertificate application);       //修改应用信息
    public String  test();
    public void  appPermissionSetInitialization(String appId,PermissionSet permissionCatalog); //初始化应用权限   put
    public  void appPermissionSetAdd(String appId,PermissionType permission);   //权限增加  post
    public  void appPermissionSetDelete(String appId,PermissionType permission); //权限移除  delete
    public ArrayList<PermissionType> appPermissionSetFinderAll(String appId);    //所有权限查询 get
    public  void appPermissionSetClear(String appId);  //清除所有权限 delete
    public Boolean appPermissionSetFinder(String appId,PermissionType permission); // 是否拥有此权限
    public Boolean appAuthentication(String appId,String appKey);
    
    
    public String getRegistryByAppId(String appId);
}
