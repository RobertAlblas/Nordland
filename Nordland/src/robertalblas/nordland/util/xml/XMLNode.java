package robertalblas.nordland.util.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class XMLNode {
	private String elementName;
	private List<XMLNode> childNodes;
	private HashMap<String,String> attributes;
	
	public XMLNode(String elementName){
		this.elementName = elementName;
		this.attributes = new HashMap<String, String>();
		this.childNodes = new ArrayList<XMLNode>();
	}
	
	public void addAttribute(String attributeName, String value){
		attributes.put(attributeName, value);
	}
	
	public String getAttributeValue(String attributeName){
		return attributes.get(attributeName);
	}
	
	public Set<String> getAllAttributeNames(){
		return attributes.keySet();
	}
	
	public String getElementName(){
		return elementName;
	}
	
	public void addChildNode(XMLNode xmlNode){
		childNodes.add(xmlNode);
	}
	
	public List<XMLNode> getChildNodes(){
		return childNodes;
	}
	
	public void printNodeTree(XMLNode xmlNode) {
		if(xmlNode == null){
			xmlNode = this;
		}
		
		System.out.println(xmlNode.getElementName());
		System.out.println("======================");
		for (String key : xmlNode.getAllAttributeNames()) {
			System.out
					.println(" " + key + "=" + xmlNode.getAttributeValue(key));
		}
		System.out.println("");
		for (XMLNode childNode : xmlNode.getChildNodes()) {
			printNodeTree(childNode);
		}
	}
}
