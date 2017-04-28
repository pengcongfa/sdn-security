package net.floodlightcontroller.accessPermissionMS.xacmlCtrModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.xacml.Indenter;
import com.sun.xacml.Policy;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.cond.FunctionTypeException;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

public class fdlEvaluate {

public static  String getResult() throws Exception {
        SimplePDP simplePDP = null;
     //   String policyFile="/home/pengcong/workspace/fdlXACML/src/fdl/onePolicy.xml";
    //   String requestFile = "/home/pengcong/workspace/fdlXACML/src/fdl/oneRequest.xml";
       String policyFile=System.getProperty("user.dir")+"/src/main/java/net/floodlightcontroller/accessPermissionMS/xacmlCtrModule/onePolicy.xml";
        String requestFile = System.getProperty("user.dir")+"/src/main/java/net/floodlightcontroller/accessPermissionMS/xacmlCtrModule/oneRequest.xml";
        simplePDP = new SimplePDP(policyFile);
        ResponseCtx response = simplePDP.evaluate(requestFile);
       
      // String outPath = "/home/pengcong/workspace/fdlXACML/src/fdl/oneResult.xml";
       String outPath= System.getProperty("user.dir")+"/src/main/java/net/floodlightcontroller/accessPermissionMS/xacmlCtrModule/oneResult.xml";
        response.encode(new FileOutputStream(outPath), new Indenter());
        // step 1:���DOM����������
        // �����������Ǵ�������Ľ�����
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // step 2����þ����dom������
        DocumentBuilder db = dbf.newDocumentBuilder();
        // step 3:����һ��xml�ĵ������Document���󣨸��ڵ㣩
        // ���ĵ�������ĿĿ¼�¼���
       // Document document = db.parse(new File("/home/pengcong/workspace/fdlXACML/src/fdl/oneResult.xml"));
        Document document =db.parse(new File(outPath));
        // ���ݱ�ǩ�����ʽڵ�
        NodeList list = document.getElementsByTagName("Result");
        //System.out.println("list length: " + list.getLength());
        Element element = (Element) list.item(0);
        String decision = element.getElementsByTagName("Decision").item(0)
                .getFirstChild().getNodeValue();
       
       return  decision;
    }
}
