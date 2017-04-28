package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

public class AppIdentityCertificate {
private String  appId;                         //应用程序标识
private String  appName;                //应用程序名称
private String  appKey;                    //应用程序密钥
private String  registry;                    //应用注册厂商
private String  registrationDate;  //应用注册日期
private String  expDate;                 //应用有效期
private int       ATL;                          //应用程序信任级别

public void  set(String  appId, String  appName,String  appKey,String  registry,String  registrationDate,String  expDate,int   ATL){
	this.appId=appId;
	this.appName=appName;
	this.appKey=appKey;
	this.registry=registry;
	this.registrationDate=registrationDate;
	this.expDate=expDate;
	this.ATL=ATL;
}

public String getAppId(){
	return appId;
}

public void setAppId(String  appId){
	this.appId=appId;
}

public String getAppName(){
	return appName;
}

public void setAppName(String  appName){
	this.appName=appName;
}

public String getAppKey(){
	return appKey;
}

public void setAppKey(String  appKey){
	this.appKey=appKey;
}

public String getRegistry(){
	return registry;
}

public void setRegistry(String  registry){
	this.registry=registry;
}

public String getRegistrationDate(){
	return registrationDate;
}

public void setRegistrationDate(String  registrationDate){
	this.registrationDate=registrationDate;
}

public String getExpDate(){
	return expDate;
}

public void setExpDate(String  expDate){
	this.expDate=expDate;
}

public int getATL(){
	return ATL;
}

public void setATL(int ATL){
	this.ATL=ATL;
}

public String toString(){
	return "appId:"+this.appId+"\r\nappName:"+this.appName+"\r\nappKey:"+this.appKey+"\r\nregistry:"+this.registry+
			       "\r\nregistrationDate:"+this.registrationDate+"\r\nexpDate:"+this.expDate+
			       "\r\nATL:"+this.ATL;
}

}
