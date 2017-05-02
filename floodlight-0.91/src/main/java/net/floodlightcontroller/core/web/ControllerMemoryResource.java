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

package net.floodlightcontroller.core.web;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.floodlightcontroller.accessPermissionMS.appInfoStorage.IAppInfoStorageService;
import net.floodlightcontroller.accessPermissionMS.appInfoStorage.PermissionType;
import net.floodlightcontroller.accessPermissionMS.xacmlCtrModule.IXacmlCtrService;

/**
 * Retrieve floodlight memory state
 * @author readams
 */
public class ControllerMemoryResource extends ServerResource {
	protected static Logger log=LoggerFactory.getLogger(ControllerMemoryResource.class);
	
    @Get("json")
    public Map<String, Object> retrieve() {
    	 IAppInfoStorageService accessCheck = 
         		(IAppInfoStorageService) getContext().getAttributes().
         		    get(IAppInfoStorageService.class.getCanonicalName()); 
         IXacmlCtrService xacmlCtr=(IXacmlCtrService)getContext().getAttributes().
        		    get(IXacmlCtrService.class.getCanonicalName());
    	 
        HashMap<String, Object> model = new HashMap<String, Object>();
        
        Form form1 = getRequest().getResourceRef().getQueryAsForm() ;    //获取查询参数  
        String appId=form1.getFirstValue("appid");
        String appkey=form1.getFirstValue("appkey");
       
        if(appId.equals("1")){
        	  Runtime runtime = Runtime.getRuntime();
              model.put("total", new Long(runtime.totalMemory()));
              model.put("free", new Long(runtime.freeMemory()));
              return model;
        }
       else{
            if(accessCheck.appAuthentication(appId, appkey)){
                if(accessCheck.appPermissionSetFinder(appId, PermissionType.read_controller_info)){
                	 if(xacmlCtr.getXacmlEvaluateResult(appId)){
                		    Runtime runtime = Runtime.getRuntime();
                	        model.put("total", new Long(runtime.totalMemory()));
                	        model.put("free", new Long(runtime.freeMemory()));
                	        return model;
                	   }
                	  else{
                           log.info("---------应用ID："+appId+"-------未通过基于属性的XACML访问控制");
                          }
                  }
                 else{
                          log.info("---------应用ID："+appId+"-------权限检查失败：无 read_controller_info 权限");
                 	 }
               }
              else{
                      log.info("---------应用ID："+appId+"-------身份认证失败");
                  }
            return model;
        }   
    }
}
