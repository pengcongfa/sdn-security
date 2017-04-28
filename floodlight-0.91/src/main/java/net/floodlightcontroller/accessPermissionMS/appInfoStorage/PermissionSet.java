package net.floodlightcontroller.accessPermissionMS.appInfoStorage;

import java.util.ArrayList;


public class PermissionSet {
private ArrayList<PermissionType> permissionSet=new ArrayList<PermissionType>();

public void  addPermissionToSet(PermissionType permission){
	permissionSet.add(permission);
}

public void removePermissionInSet(PermissionType permission){
	permissionSet.remove(permission);
}


public Boolean permissionIsHave(PermissionType permission){
	          return permissionSet.contains(permission);
}


public ArrayList<PermissionType> appPermissionSetFinder(){
	return permissionSet;
}

public void ClearPermissionSet(){
	permissionSet.clear();
}

public String toString(){
	String l="";
	for(int i=0;i<permissionSet.size();i++){
		l=l+" \r\n"+i+":"+permissionSet.get(i);
	}
	return l;
}
}
