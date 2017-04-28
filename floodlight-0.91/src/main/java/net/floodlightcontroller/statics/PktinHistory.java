package net.floodlightcontroller.statics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.openflow.protocol.OFMessage;
import org.openflow.protocol.OFType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.floodlightcontroller.accessPermissionMS.appInfoStorage.IAppInfoStorageService;
import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IFloodlightProviderService;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.restserver.IRestApiService;
import permissionSystem.operationCheck;

public class PktinHistory implements 
													IOFMessageListener,IFloodlightModule,IPktinHistoryService{

	    protected static Logger log = LoggerFactory.getLogger(PktinHistory.class);  
	    protected IFloodlightProviderService FloodlightProvider ;  
	    protected IRestApiService restApi ;  
	    protected IAppInfoStorageService appDataBase;
	    private AtomicLong PACKET_IN_COUNT = new AtomicLong() ;  

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "PktinHistory" ;
	}

	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getPackINCount() {
		// TODO Auto-generated method stub
		return PACKET_IN_COUNT.get();
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		 Collection<Class<? extends IFloodlightService>> l =   
	                new ArrayList<Class<? extends IFloodlightService>>();  
	        l.add(IPktinHistoryService.class);  
	        return l;  
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		Map<Class<? extends IFloodlightService>,IFloodlightService> m =   
                new  HashMap<Class<? extends IFloodlightService>,IFloodlightService>();  
        m.put(IPktinHistoryService.class,this);  
        return m;  
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		// TODO Auto-generated method stub
		 Collection<Class<? extends IFloodlightService>> l =   
	                new ArrayList<Class<? extends IFloodlightService>>() ;  
	        l.add(IFloodlightProviderService.class);  
	        l.add(IRestApiService.class);  
	        l.add(IAppInfoStorageService.class);
	        return l ;  
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
		    FloodlightProvider = context.getServiceImpl(IFloodlightProviderService.class);  
	        restApi = context.getServiceImpl(IRestApiService.class) ;  
	        appDataBase=context.getServiceImpl(IAppInfoStorageService.class);
	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
		 FloodlightProvider.addOFMessageListener(OFType.PACKET_IN,this) ;  
		 restApi.addRestletRoutable(new PktInHistoryWebRoutable());
	}

	@Override
	public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg,
			FloodlightContext cntx) {
		// TODO Auto-generated method stub
        long count = PACKET_IN_COUNT.incrementAndGet() ;  
     	// System.out.println("PktinHistory:****The total count of packet-in Messages are" +count);
      //  log.info("PktinHistory*********The total count of packet-in Messages are" + count);  
   //     operationCheck a=new operationCheck();
      //  a.getMessager(count);
      //  System.out.println("ACCM \"CIRCULAR\" \" 2\":  "+appDataBase.appAuthentication("CIRCULAR", "2"));
 //       System.out.println("ACCM \"1\" \" 2\":  "+appDataBase.appAuthentication("1", "2"));
      //  System.out.println("ACCM \"1\" \" 1\":  "+appDataBase.appAuthentication("1", "1"));
        return Command.CONTINUE ;  
	}

}
