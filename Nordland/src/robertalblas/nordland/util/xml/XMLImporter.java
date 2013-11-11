package robertalblas.nordland.util.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import robertalblas.nordland.util.log.Logger;
import robertalblas.nordland.util.log.LoggerManager;

public class XMLImporter {

	public XMLNode importXMLFile(String filename, String rootName) {
		try {
			InputStream xmlIS = XMLImporter.class.getResourceAsStream(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlIS);

			doc.getDocumentElement().normalize();

			String xrootName = doc.getDocumentElement().getNodeName();
			if (xrootName != rootName) {
				LoggerManager.getInstance().getDefaultLogger().log("Error reading file: " + filename, Logger.LOGTYPE_ERROR);
				throw new RuntimeException("incorrect XML file");
			}

			XMLNode rootNode = processNode(doc.getDocumentElement());
			return rootNode;

		} catch (Exception e) {
			LoggerManager.getInstance().getDefaultLogger().log("Error reading file: " + filename, Logger.LOGTYPE_ERROR);
			e.printStackTrace();
			return null;
		}
	}

	private XMLNode processNode(Node node) {
		XMLNode xmlNode = new XMLNode(node.getNodeName());

		NamedNodeMap attributes = node.getAttributes();
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); ++i) {
				Node attribute = attributes.item(i);
				xmlNode.addAttribute(attribute.getNodeName(),
						attribute.getNodeValue());
			}
		}

		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				xmlNode.addChildNode(processNode(childNode));
			}
		}
		return xmlNode;

	}
}
