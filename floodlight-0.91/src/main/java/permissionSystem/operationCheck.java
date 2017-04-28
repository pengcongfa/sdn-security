package permissionSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class operationCheck {
	  protected static Logger log = LoggerFactory.getLogger(operationCheck.class);  
         private String messager="operationCheck没有权限";
         
 public void getMessager( long count ){
	  log.info("operationCheck*********The total count of packet-in Messages are" + count);  
	// System.out.println(messager+count);;
 }
}
