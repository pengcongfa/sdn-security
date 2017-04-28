package net.floodlightcontroller.accessPermissionMS.xacmlCtrModule;

import net.floodlightcontroller.core.module.IFloodlightService;

public interface IXacmlCtrService extends IFloodlightService {
	public void creatXacmlPolicy(String startTime,String endTime,String registrar);
	public boolean getXacmlEvaluateResult(String appId);

}
