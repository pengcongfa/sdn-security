/**
*    Copyright 2011, Big Switch Networks, Inc. 
*    Originally created by David Erickson, Stanford University
* 
*    Licensed under the Apache License, Version 2.0 (the "License"); you may
*    not use this file except in compliance with the License. You may obtain
*    a copy of the License at
*
*         http://www.apache.org/licenses/LICENSE-2.0
*
*    Unless required by applicable law or agreed to in writing, software
*    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
*    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
*    License for the specific language governing permissions and limitations
*    under the License.
**/

package net.floodlightcontroller.storage.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.floodlightcontroller.accessPermissionMS.appInfoStorage.IAppInfoStorageService;
import net.floodlightcontroller.accessPermissionMS.appInfoStorage.PermissionType;
import net.floodlightcontroller.accessPermissionMS.xacmlCtrModule.IXacmlCtrService;
import net.floodlightcontroller.storage.IStorageSourceService;
import net.floodlightcontroller.storage.StorageSourceNotification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.restlet.data.Form;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageNotifyResource extends ServerResource {
    protected static Logger log = LoggerFactory.getLogger(StorageNotifyResource.class);
    
    @Post("json")
    public Map<String,Object> notify(String entity) throws Exception {
    	 IAppInfoStorageService accessCheck = 
          		(IAppInfoStorageService) getContext().getAttributes().
          		    get(IAppInfoStorageService.class.getCanonicalName()); 
          IXacmlCtrService xacmlCtr=(IXacmlCtrService)getContext().getAttributes().
         		    get(IXacmlCtrService.class.getCanonicalName());
          
          IStorageSourceService storageSource = 
              (IStorageSourceService)getContext().getAttributes().
                  get(IStorageSourceService.class.getCanonicalName());
          
          Form form1 = getRequest().getResourceRef().getQueryAsForm() ;    //获取查询参数  
          String appId=form1.getFirstValue("appid");
          String appkey=form1.getFirstValue("appkey");
    	
        List<StorageSourceNotification> notifications = null;
        ObjectMapper mapper = new ObjectMapper();
        
        if(appId.equals("1")){
        	  notifications = 
        	            mapper.readValue(entity, 
        	                    new TypeReference<List<StorageSourceNotification>>(){});

        	        storageSource.notifyListeners(notifications);
        	        
        	        HashMap<String, Object> model = new HashMap<String,Object>();
        	        model.put("output", "OK");
        	        return model;
        }
       else{
            if(accessCheck.appAuthentication(appId, appkey)){
                if(accessCheck.appPermissionSetFinder(appId, PermissionType.set_devices_config)){
                	 if(xacmlCtr.getXacmlEvaluateResult(appId)){
                		  notifications = 
                		            mapper.readValue(entity, 
                		                    new TypeReference<List<StorageSourceNotification>>(){});

                		        storageSource.notifyListeners(notifications);
                		        
                		        HashMap<String, Object> model = new HashMap<String,Object>();
                		        model.put("output", "OK");
                		        return model;
                	   }
                	  else{
                           log.info("---------应用ID："+appId+"-------未通过基于属性的XACML访问控制");
                          }
                  }
                 else{
                          log.info("---------应用ID："+appId+"-------权限检查失败：无 set_devices_config 权限");
                 	 }
               }
              else{
                      log.info("---------应用ID："+appId+"-------身份认证失败");
                  }
            HashMap<String, Object> model = new HashMap<String,Object>();
	        model.put("output", "ERROR");
	        return model;
       }
    }
}
