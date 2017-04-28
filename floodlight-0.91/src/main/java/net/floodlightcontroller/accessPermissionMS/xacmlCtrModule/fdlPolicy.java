package net.floodlightcontroller.accessPermissionMS.xacmlCtrModule;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.xacml.Indenter;
import com.sun.xacml.Policy;
import com.sun.xacml.Rule;
import com.sun.xacml.Target;
import com.sun.xacml.TargetMatch;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.IntegerAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.attr.TimeAttribute;
import com.sun.xacml.combine.CombiningAlgFactory;
import com.sun.xacml.combine.OrderedPermitOverridesRuleAlg;
import com.sun.xacml.combine.RuleCombiningAlgorithm;
import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.Function;
import com.sun.xacml.cond.FunctionFactory;
import com.sun.xacml.cond.FunctionTypeException;
import com.sun.xacml.ctx.Result;

public class fdlPolicy {

	public static Target createPolicyTarget() throws URISyntaxException, UnknownIdentifierException, FunctionTypeException {
		  List actions = new ArrayList();
		  List resources = new ArrayList();
		  // Attributes of action type
		  // Multiple subject attributes can be specified. In this
		  // case only one is being defined.
		  List action = new ArrayList();
		  URI actionDesignatorType =
		    new URI("http://www.w3.org/2001/XMLSchema#string");  //鐎规矮绠熺仦鐐达拷褏琚崹锟�
		  URI actionDesignatorId =
		    new URI("urn:oasis:names:tc:xacml:1.0:action:action-id");    //鐎规矮绠熺仦鐐达拷褍鎮�
		  // Match function for the subject-id attribute
		  String actionMatchId =
		    "urn:oasis:names:tc:xacml:1.0:function:string-equal";  //鐎规矮绠熷В鏃囩窛閸戣姤鏆�
		  
		  AttributeDesignator actionDesignator =                                             //濮ｆ棁绶濈拠閿嬬湴娑撳海鐡ラ悾銉ф窗閺嶅洣鑵戦惃鍕潣閹冿拷锟�
		    new AttributeDesignator(
		                  AttributeDesignator.ACTION_TARGET,
		                  actionDesignatorType,
		                  actionDesignatorId,
		                  false);
		  StringAttribute actionValue = new
		    StringAttribute("access");
		  // get the factory that handles Target functions
		  FunctionFactory factory =
		    FunctionFactory.getTargetInstance();
		  // get an instance of the right function for matching
		  // subject attributes
		  Function actionFunction =
		    factory.createFunction(actionMatchId);
		  TargetMatch actionMatch = new TargetMatch(
		                          TargetMatch.ACTION,
		                  actionFunction,
		                  actionDesignator,
		                  actionValue);
		  action.add(actionMatch);
		  // Attributes of resource type
		  // Multiple resource attributes can be specified. In this
		  // case only one is being defined.
		  
		  List resource = new ArrayList();
		  URI resourceDesignatorType =
		    new URI("http://www.w3.org/2001/XMLSchema#anyURI");
		  URI resourceDesignatorId =
		    new URI("urn:oasis:names:tc:xacml:1.0:resource:resource-id");
		  // Match function for the resource-id attribute
		  String resourceMatchId =
		    "urn:oasis:names:tc:xacml:1.0:function:anyURI-equal";
		  AttributeDesignator resourceDesignator =
		    new AttributeDesignator(
		                  AttributeDesignator.RESOURCE_TARGET,
		                  resourceDesignatorType,
		                  resourceDesignatorId,
		                  false);
		  AnyURIAttribute resourceValue =
		    new AnyURIAttribute(
		      new URI("www.floodlight.com"));
		  // Get an instance of the right function for matching 
		  // resource attribute
		  Function resourceFunction =
		    factory.createFunction(resourceMatchId);
		  TargetMatch resourceMatch = new TargetMatch(
		                               TargetMatch.RESOURCE,
		                         resourceFunction,
		                         resourceDesignator,
		                         resourceValue);
		  resource.add(resourceMatch);
		  // Put the resource and action sections into their lists
		  
		  resources.add(resource);
		  actions.add(action);
		  // Create and return the new target. No action type
		  // attributes have been specified in the target
		  //return new Target(subjects, resources, null);
		  return new Target(null, resources, actions);
		}
	
	
	
	
	public static Target createRuleTarget(int startTime,int endTime) throws URISyntaxException, UnknownIdentifierException, FunctionTypeException {
		 List subjects = new ArrayList();
		  // Attributes of Subject type
		  // Multiple subject attributes can be specified. In this
		  // case only one is being defined.
		  List subject = new ArrayList();
		  URI subjectDesignatorType =
		    new URI("http://www.w3.org/2001/XMLSchema#integer");  //鐎规矮绠熺仦鐐达拷褏琚崹锟�
		  URI subjectDesignatorId =
		    new URI("accessTime");    //鐎规矮绠熺仦鐐达拷褍鎮�
		  // Match function for the subject-id attribute
		  String subjectMatchId =
		    "urn:oasis:names:tc:xacml:1.0:function:integer-less-than";  //鐎规矮绠熷В鏃囩窛閸戣姤鏆�
		  
		  AttributeDesignator subjectDesignator =                                             //濮ｆ棁绶濈拠閿嬬湴娑撳海鐡ラ悾銉ф窗閺嶅洣鑵戦惃鍕潣閹冿拷锟�
		    new AttributeDesignator(
		                  AttributeDesignator.SUBJECT_TARGET,
		                  subjectDesignatorType,
		                  subjectDesignatorId,
		                  false);
		  IntegerAttribute startTimesubjectValue = new
				  IntegerAttribute(startTime);
		  // get the factory that handles Target functions
		  FunctionFactory factory =
		    FunctionFactory.getTargetInstance();
		  // get an instance of the right function for matching
		  // subject attributes
		  Function subjectFunction =
		    factory.createFunction(subjectMatchId);
		  TargetMatch subjectMatch = new TargetMatch(
		                          TargetMatch.SUBJECT,
		                  subjectFunction,
		                  subjectDesignator,
		                  startTimesubjectValue);
		  
		  URI subjectDesignatorType1 =
				    new URI("http://www.w3.org/2001/XMLSchema#integer");  //鐎规矮绠熺仦鐐达拷褏琚崹锟�
				  URI subjectDesignatorId1 =
				    new URI("accessTime");    //鐎规矮绠熺仦鐐达拷褍鎮�
				  // Match function for the subject-id attribute
				  String subjectMatchId1 =
				    "urn:oasis:names:tc:xacml:1.0:function:integer-greater-than";  //鐎规矮绠熷В鏃囩窛閸戣姤鏆�
				  
				  AttributeDesignator subjectDesignator1 =                                             //濮ｆ棁绶濈拠閿嬬湴娑撳海鐡ラ悾銉ф窗閺嶅洣鑵戦惃鍕潣閹冿拷锟�
				    new AttributeDesignator(
				                  AttributeDesignator.SUBJECT_TARGET,
				                  subjectDesignatorType1,
				                  subjectDesignatorId1,
				                  false);
				  IntegerAttribute endTimesubjectValue = new
						  IntegerAttribute(endTime);
				  // get the factory that handles Target functions
				  FunctionFactory factory1 =
				    FunctionFactory.getTargetInstance();
				  // get an instance of the right function for matching
				  // subject attributes
				  Function subjectFunction1 =
				    factory.createFunction(subjectMatchId1);
				  TargetMatch subjectMatch1 = new TargetMatch(
				                          TargetMatch.SUBJECT,
				                  subjectFunction1,
				                  subjectDesignator1,
				                  endTimesubjectValue);
		  
		  subject.add(subjectMatch);
		  subject.add(subjectMatch1);
		  subjects.add(subject);
		  return new Target(subjects, null, null);
		}
	
	public static Apply createRuleCondition(String registrarValue) throws URISyntaxException {
		List conditionArgs = new ArrayList();
		  // Define the name and type of the attribute
		  // to be used in the condition
		  URI designatorType = new URI("http://www.w3.org/2001/XMLSchema#string");
		  URI designatorId = new URI("registrar");
		  // Pick the function that the condition uses
		  FunctionFactory factory = FunctionFactory.getConditionInstance();
		  Function conditionFunction = null;
		  try {
		      conditionFunction =
		          factory.createFunction(
		              "urn:oasis:names:tc:xacml:1.0:function:" + "string-equal");
		  } catch (Exception e) {
		      return null;
		  }
		  // Choose the function to pick one of the
		  // multiple values returned by AttributetDesignator
		  List applyArgs = new ArrayList();
		  factory = FunctionFactory.getGeneralInstance();
		  Function applyFunction = null;
		  try {
		      applyFunction =
		          factory.createFunction(
		              "urn:oasis:names:tc:xacml:1.0:function:"
		                  + "string-one-and-only");
		  } catch (Exception e) {
		      return null;
		  }
		  // Create the AttributeDesignator
		  AttributeDesignator designator =
		      new AttributeDesignator(
		           AttributeDesignator.SUBJECT_TARGET,
		           designatorType,
		           designatorId,
		           false,
		           null);
		  applyArgs.add(designator);
		  // Create the Apply object and pass it the
		  // function and the AttributeDesignator. The function
		  // picks up one of the multiple values returned by the
		  // AttributeDesignator
		  Apply apply = new Apply(applyFunction, applyArgs, false);
		  // Add the new apply element to the list of inputs
		  // to the condition along with the AttributeValue
		  conditionArgs.add(apply);
		  StringAttribute value = new StringAttribute(registrarValue);
		  conditionArgs.add(value);
		  // Finally, create and return the condition
		  return new Apply(conditionFunction, conditionArgs, true);
		}
	
	public static List createRules(String registrarValue,int startTime,int endTime) throws URISyntaxException, UnknownIdentifierException, FunctionTypeException {
		  // Step 1: Define the identifier for the rule
		  URI ruleId = new URI("ProjectPlanAccessRule");
		  String ruleDescription = "rule for accessing floodlight";
		  // Step 2: Define the effect of the rule
		  int effect = Result.DECISION_PERMIT;
		  // Step 3: Get the target for the rule
		  Target target = createRuleTarget(startTime,endTime);
		  // Step 4: Get the condition for the rule
		  Apply condition = createRuleCondition(registrarValue);
		  // Step 5: Create the rule
		  Rule openRule = new Rule(ruleId, effect,ruleDescription, target, condition);
		  // Create a list for the rules and add the rule to it
		  
		  
		  URI ruleId1 = new URI("FinalRule");
		  int effect1=Result.DECISION_DENY;
		  Rule FinalRule=new Rule(ruleId1,effect1,null,null,null);
		  List ruleList = new ArrayList();
		  ruleList.add(openRule);
		  ruleList.add(FinalRule);
		  return ruleList;
		}
	
	
	public static Policy createPolicy(String registrarValue,int startTime,int endTime) throws URISyntaxException, UnknownIdentifierException, FunctionTypeException, FileNotFoundException{
		URI policyId = new URI("ProjectPlanAccessPolicy");
		String description =
		"This AccessPolicy applies to any account at secf.com "
		+ "accessing www.floodlight.com";
		// Rule combining algorithm for the Policy
		URI combiningAlgId = new URI(OrderedPermitOverridesRuleAlg.algId);
		CombiningAlgFactory factory = CombiningAlgFactory.getInstance();
		RuleCombiningAlgorithm combiningAlg =
		  (RuleCombiningAlgorithm) (factory.createAlgorithm(combiningAlgId));
		// Create the target for the policy
		Target policyTarget = createPolicyTarget();
		// Create the rules for the policy
		List ruleList = createRules(registrarValue,startTime,endTime);
		// Create the policy
		Policy policy =
		  new Policy(
		    policyId,
		    combiningAlg,
		    description,
		    policyTarget,
		    ruleList);
	//	String outPath = "/home/pengcong/workspace/fdlXACML/src/fdl/onePolicy.xml";
		String outPath=System.getProperty("user.dir")+"/src/main/java/net/floodlightcontroller/accessPermissionMS/xacmlCtrModule/onePolicy.xml";
		policy.encode(new FileOutputStream(outPath), new Indenter());
		return policy;	
	}
}