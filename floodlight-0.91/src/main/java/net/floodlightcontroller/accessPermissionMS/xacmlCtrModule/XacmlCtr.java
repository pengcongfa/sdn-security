package net.floodlightcontroller.accessPermissionMS.xacmlCtrModule;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.python.antlr.PythonParser.if_stmt_return;

import com.google.common.util.concurrent.CycleDetectingLockFactory.Policy;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.cond.FunctionTypeException;

import net.floodlightcontroller.accessPermissionMS.appInfoStorage.AppWebRoutable;
import net.floodlightcontroller.accessPermissionMS.appInfoStorage.IAppInfoStorageService;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.restserver.IRestApiService;

public class XacmlCtr implements IFloodlightModule,IXacmlCtrService{
	  protected IRestApiService restApi ;  
	  protected IAppInfoStorageService appInfoStorage ;  
	  private fdlPolicy PAP=new fdlPolicy();
	  private fdlRequest PEP=new fdlRequest();
	  private fdlEvaluate PDP=new fdlEvaluate();
	  
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		 Collection<Class<? extends IFloodlightService>> l =   
	                new ArrayList<Class<? extends IFloodlightService>>();  
	        l.add(IXacmlCtrService.class);  
	        return l;  
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		Map<Class<? extends IFloodlightService>,IFloodlightService> m =   
                new  HashMap<Class<? extends IFloodlightService>,IFloodlightService>();  
        m.put(IXacmlCtrService.class,this);  
        return m;  
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		// TODO Auto-generated method stub
		 Collection<Class<? extends IFloodlightService>> l =   
	                new ArrayList<Class<? extends IFloodlightService>>() ;  
	        l.add(IRestApiService.class);  
	        l.add(IAppInfoStorageService.class);  
	        return l ;
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
	    restApi = context.getServiceImpl(IRestApiService.class) ;  
	    appInfoStorage=context.getServiceImpl(IAppInfoStorageService.class);
	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
		 restApi.addRestletRoutable(new PolicyCreatRoutable());
	}

	@Override
	public void creatXacmlPolicy(String startTime, String endTime, String registrar) {
		// TODO Auto-generated method stub
		String timeS[]=startTime.split(":");
		String timeE[]=endTime.split(":");
		int timeStart=Integer.parseInt(timeS[0])*60+Integer.parseInt(timeS[1]);
		int timeEnd=Integer.parseInt(timeE[0])*60+Integer.parseInt(timeE[1]);
		try {
			PAP.createPolicy(registrar, timeStart, timeEnd);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownIdentifierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FunctionTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean getXacmlEvaluateResult(String appId) {
		// TODO Auto-generated method stub
		String result = null;
		String  registrarValue=appInfoStorage.getRegistryByAppId(appId);
		Date date=new Date();
		int accessTime=date.getHours()*60+date.getMinutes();
		try {
			PEP.createRequest(registrarValue, accessTime);
			try {
				result=PDP.getResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.equals("Permit")){
			return true;
		}
		else{
			return false;
		}
	}

	
}
