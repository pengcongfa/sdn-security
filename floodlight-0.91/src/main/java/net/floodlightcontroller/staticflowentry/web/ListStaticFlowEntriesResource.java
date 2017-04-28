/**
 *    Copyright 2013, Big Switch Networks, Inc.
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

package net.floodlightcontroller.staticflowentry.web;

import java.util.HashMap;
import java.util.Map;

import net.floodlightcontroller.core.web.ControllerSwitchesResource;
import net.floodlightcontroller.staticflowentry.IStaticFlowEntryPusherService;

import org.openflow.protocol.OFFlowMod;
import org.python.antlr.PythonParser.if_stmt_return;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListStaticFlowEntriesResource extends ServerResource {
    protected static Logger log = LoggerFactory.getLogger(ListStaticFlowEntriesResource.class);
    
    @Get
    public Map<String, Map<String, OFFlowMod>> ListStaticFlowEntries() {
        IStaticFlowEntryPusherService sfpService =
                (IStaticFlowEntryPusherService)getContext().getAttributes().
                    get(IStaticFlowEntryPusherService.class.getCanonicalName());
        
        String param = (String) getRequestAttributes().get("switch");
        
        Form form1 = getRequest().getResourceRef().getQueryAsForm() ;    //获取查询参数  
        String appId=form1.getFirstValue("appid");
        String appkey=form1.getFirstValue("appkey");

        
        if (log.isDebugEnabled())
            log.debug("Listing all static flow entires for switch: " + param);
        
        if (param.toLowerCase().equals("all")) {
        	if(sfpService.getFlows( appId,appkey)!=null){
            return sfpService.getFlows( appId,appkey);
        	}
        } else {
            try {
                Map<String, Map<String, OFFlowMod>> retMap = 
                        new HashMap<String, Map<String, OFFlowMod>>();
                if(sfpService.getFlows( appId,appkey,param)!=null){
                retMap.put(param, sfpService.getFlows( appId,appkey,param));
                return retMap;
                }
            } catch (NumberFormatException e){
                setStatus(Status.CLIENT_ERROR_BAD_REQUEST, 
                          ControllerSwitchesResource.DPID_ERROR);
            }
        }
        return null;
    }
}
