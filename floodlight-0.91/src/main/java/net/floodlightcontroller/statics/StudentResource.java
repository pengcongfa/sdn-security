package net.floodlightcontroller.statics;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.data.Form;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.ObjectRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class StudentResource extends ServerResource{

private int id=2013211289;
private int i=0;


@Override

protected void doInit() throws ResourceException {

id = Integer.valueOf((String) getRequestAttributes().get("studentId"));
}

@Get

public Representation get(Representation entity) throws IOException {
	  Form form = getRequest().getResourceRef().getQueryAsForm() ;    //获取查询参数  
	  String movie = form.getFirstValue("movie");     //获取key=movie的参数值
	  String action = form.getFirstValue("action");     //获取key=movie的参数值
	  System.out.println("get携带的参数movie:"+movie);
	 System.out.println("get携带的参数movie:"+action);
	Student student =new  Student();
	student.setId(2013211001);
	student.setAge(18);
	student.setSex(0);
	student.setClsId(13305);
	student.setName("张华");
	Student student1=new  Student();
	student1.setId(999999999);
	student1.setAge(18);
	student1.setSex(0);
	student1.setClsId(13305);
	student1.setName(" 彭聪");
	 List<Student> stuList = new ArrayList<Student>();
	 stuList.add(student);
	 stuList.add(student1);
	// i++;
	//return new JacksonRepresentation<Student>(student);
return new JacksonRepresentation< List<Student> >(stuList);
	//return new StringRepresentation(student.toString());
}

@Delete

public Representation delete() {

	Student student =new  Student();
	student.setId(999999999);
	student.setAge(18);
	student.setSex(0);
	student.setClsId(13305);
	student.setName(" 彭聪");
	
	//return new JacksonRepresentation(new JSONObject(student, false));
return new JacksonRepresentation<Student>(student);

}

@Put

public Representation put(Representation entity) throws ResourceException {
	/*JacksonRepresentation<List> studentrp = new JacksonRepresentation< List>(entity , List.class);  
//	 List<Student> student;
	JacksonRepresentation<Student> studentrp = new JacksonRepresentation<Student>(entity , Student.class);  
	Student student;
	
		try {
			student = studentrp.getObject();
			System.out.println(student);
			return new JacksonRepresentation<Student>(student);
			//return new JacksonRepresentation< List<Student> >(student);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	*/
	

Form form = new Form(entity);

Student student =new  Student();
int  id =Integer.parseInt(form.getFirstValue("id"));
String name = form.getFirstValue("name");

int clsId = Integer.parseInt(form.getFirstValue("clsId"));

int sex = Integer.parseInt(form.getFirstValue("sex"));

int age = Integer.parseInt(form.getFirstValue("age"));


	
student.setClsId(clsId);

student.setName(name);

student.setSex(sex);

student.setAge(age);

student.setId(id);

System.out.println(student);

return new JacksonRepresentation<Student>(student);

}

}