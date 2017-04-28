package net.floodlightcontroller.accessPermissionMS.xacmlCtrModule;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import net.floodlightcontroller.accessPermissionMS.appInfoStorage.IAppInfoStorageService;

public class PolicyCreatResource extends ServerResource{

	@Put
	public Representation put(Representation entity ){
		IXacmlCtrService xacmlCtr=(IXacmlCtrService)getContext().getAttributes().get(IXacmlCtrService.class.getCanonicalName());
		Form form = new Form(entity);
		String startTime=form.getFirstValue("starttime");
		String endTime=form.getFirstValue("endtime");
		String registrarValue=form.getFirstValue("registry");
		xacmlCtr.creatXacmlPolicy(startTime, endTime, registrarValue);
		return new StringRepresentation("策略创建成功："+"\n"
																				+"时间段: "+startTime+"-"+endTime+"\n"
																				+"注册商: "+registrarValue);
	}
}
