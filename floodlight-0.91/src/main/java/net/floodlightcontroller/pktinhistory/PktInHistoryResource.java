package net.floodlightcontroller.pktinhistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.floodlightcontroller.core.types.SwitchMessagePair;
import net.floodlightcontroller.statics.IPktinHistoryService;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PktInHistoryResource extends ServerResource {
/*@Get("json")
public List<SwitchMessagePair> retrieve() {
IPktinHistoryService pihr =(IPktinHistoryService)getContext().getAttributes().get(IPktinHistoryService.class.getCanonicalName());
List<SwitchMessagePair> l = new ArrayList<SwitchMessagePair>();
l.addAll(java.util.Arrays.asList(pihr.getBuffer().snapshot()));
System.out.print(l.toString());
l.addAll(1, c)
return l;
}*/
@Get("json")  
    
    public HashMap<String, String> retrieve(){  
   
        HashMap<String, String> resp = new HashMap<String, String>() ;  
     
       resp.put("001", "hhdhhuhuhhska") ;  
        resp.put("002", "nnsannsnkwsads") ;  
       resp.put("003", "mnmcnfirfiwjowjij") ;  
      System.out.print(resp.toString());
            //    resp.put("中国","人");
              //  System.out.print("中国人");
        return resp;  
    }  
}
