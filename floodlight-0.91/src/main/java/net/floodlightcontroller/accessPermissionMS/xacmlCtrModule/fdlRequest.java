package net.floodlightcontroller.accessPermissionMS.xacmlCtrModule;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Indenter;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.IntegerAttribute;
import com.sun.xacml.attr.RFC822NameAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.Subject;

public class fdlRequest {

	public static Set<Subject> setupSubjects(String registrarValue,int accessTime) throws URISyntaxException {
		  HashSet<Attribute> attributes = new HashSet<Attribute>();
		  // Set up the ID and value for the requesting subject
		  URI subjectId = new
		  URI("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
		  RFC822NameAttribute value = new RFC822NameAttribute("mverma@secf.com");
		  // Create the subject section with two attributes, the first with
		  // the subject's identity...
		  attributes.add(new Attribute(subjectId, null, null, value));
		 
		  URI registrarsId=new URI("registrar");
		  StringAttribute registrarsValue=new StringAttribute(registrarValue);
		  attributes.add(new Attribute(registrarsId,null,null,registrarsValue));
		  
		  URI timeId=new URI("accessTime");
		  IntegerAttribute timeValue=new IntegerAttribute(accessTime);
		  attributes.add(new Attribute(timeId,null,null,timeValue));
		  
		  // Bundle the attributes in a subject with the default category
		  HashSet<Subject> subjects = new HashSet<Subject>();
		  subjects.add(new Subject(attributes));
		  return subjects;
		}
	
	public static Set<Attribute> setupResource() throws URISyntaxException {
		  HashSet<Attribute> resource = new HashSet<Attribute>();
		  // The resource being requested
		  AnyURIAttribute value =
		      new AnyURIAttribute(new URI("www.floodlight.com"));
		  // Create the resource using a standard, required identifier for
		  // the resource being requested
		  resource.add(
		      new Attribute(
		          new URI(EvaluationCtx.RESOURCE_ID),
		          null,
		          null,
		          value));
		  return resource;
		}
	
	public static Set<Attribute> setupAction() throws URISyntaxException {
		  HashSet<Attribute> action = new HashSet<Attribute>();
		  // This is a standard URI that can optionally be used to specify
		  // the action being requested
		  URI actionId = new URI("urn:oasis:names:tc:xacml:1.0:action:action-id");
		  // Create the action
		  action.add(
		      new Attribute(actionId, null, null, new StringAttribute("access")));
		  return action;
		}
		
	public static RequestCtx createRequest(String registrarValue,int accessTime) throws URISyntaxException, FileNotFoundException{
		RequestCtx request =
			      new RequestCtx(
			          setupSubjects(registrarValue,accessTime),
			          setupResource(),
			          setupAction(),
			          new HashSet<Object>());
	 //  String outPath = "/home/pengcong/workspace/fdlXACML/src/fdl/oneRequest.xml";
	 
	  String outPath =System.getProperty("user.dir")+"/src/main/java/net/floodlightcontroller/accessPermissionMS/xacmlCtrModule/oneRequest.xml";
	  // System.out.println(filepath);
	   //System.out.println(outPath);
		request.encode(new FileOutputStream(outPath), new Indenter());
		return request;
	}
}
