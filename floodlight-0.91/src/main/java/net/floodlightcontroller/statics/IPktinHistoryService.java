package net.floodlightcontroller.statics;

import net.floodlightcontroller.core.module.IFloodlightService;

public interface IPktinHistoryService extends IFloodlightService{
	/*  
     * 用于统计结果  
     */  
         public long getPackINCount();  
}
