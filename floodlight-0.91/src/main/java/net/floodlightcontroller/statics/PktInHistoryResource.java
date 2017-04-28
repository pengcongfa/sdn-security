package net.floodlightcontroller.statics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;



public class PktInHistoryResource extends ServerResource {
	/*
	  protected   class Student {  		      
		    private String name;  
		    private String sex;  
		    private int age; 
	    Student(String name,String sex,int age){  
	        this.name = name;  
	        this.sex = sex;  
	        this.age = age;  
	     }  
	   }
	   
	@Get("json")  
    private Representation getJsonData() {  
        List<Student> list = new ArrayList<Student>();  
        Student stu1 = new Student("曹操","男",50);  
        Student stu2 = new Student("小乔","女",23);  
        Student stu3 = new Student("周瑜","男",32);  
        list.add(stu1);  
        list.add(stu2);  
        list.add(stu3);  
        //把list集合转换为json对象  
        JSONArray arr = new JSONArray(list);  
        return new Representation(arr);    
    }  */
	   /*
	@Get("json")  
    public HashMap<String, Student> retrieve(){  
       IPktinHistoryService pihr = (IPktinHistoryService) getContext()  
               .getAttributes().get(IPktinHistoryService.class.getCanonicalName());  
        long count = pihr.getPackINCount();  
        HashMap<String, Student> resp = new HashMap<String, Student>() ;  
        //resp.put("Total", Long.toString(count)) ;  
        Student stu1 = new Student("曹操","男",50);  
        Student stu2 = new Student("小乔","女",23);  
        Student stu3 = new Student("周瑜","男",32);  
        resp.put("002", stu1) ;  
        resp.put("002", stu2) ;  
        resp.put("003", stu3) ;  
      //System.out.print(resp.toString());
            //    resp.put("中国","人");
              //  System.out.print("中国人");
        //Form form = getRequest().getResourceRef().getQueryAsForm() ;    //获取查询参数  
       // String movie = form.getFirstValue("movie");     //获取key=movie的参数值 
      //  String aciont = form.getFirstValue("aciont")   ; //获取key=movie的参数值 
       // System.out.print("movie:"+movie+"action:"+aciont);
        return resp;  
    }  */
	
	@Get("json")  
    public HashMap<String, String> retrieve(){  
        IPktinHistoryService pihr = (IPktinHistoryService) getContext()  
                .getAttributes().get(IPktinHistoryService.class.getCanonicalName());  
        long count = pihr.getPackINCount();  
        HashMap<String, String> resp = new HashMap<String, String>() ;  
      //  resp.put("Total", Long.toString(count)) ;  
        resp.put("001", "name") ;  
        resp.put("002", "age") ;  
        resp.put("003", "sex") ;  
        return resp;  
    }  
}


