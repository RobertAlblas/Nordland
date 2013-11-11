package robertalblas.nordland.util.xml.graphics;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.util.xml.XMLNode;

//Version 2 supports animations

public class XMLToResourcesStrategyV2 implements XMLToResourcesStrategy {

	@Override
	public List<Sprite> convertXMLNodeToResources(XMLNode node) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		for (XMLNode xmlNode : node.getChildNodes()) {
			if (xmlNode.getElementName().equals("sprite")) {
				Sprite sprite = new Sprite(xmlNode.getAttributeValue("name"),
						Integer.parseInt(xmlNode.getAttributeValue("width")),
						Integer.parseInt(xmlNode.getAttributeValue("height")),
						Integer.parseInt(xmlNode.getAttributeValue("x")),
						Integer.parseInt(xmlNode.getAttributeValue("y")));
				sprites.add(sprite);
			}
			else if(xmlNode.getElementName().equals("animation")){
				int spriteIndex = 0;
				int animationLength = xmlNode.getChildNodes().size();
				for (XMLNode childNode : xmlNode.getChildNodes()) {
//					Sprite sprite = new Sprite(xmlNode.getAttributeValue("name"),
//							Integer.parseInt(childNode.getAttributeValue("width")),
//							Integer.parseInt(childNode.getAttributeValue("height")),
//							Integer.parseInt(childNode.getAttributeValue("x")),
//							Integer.parseInt(childNode.getAttributeValue("y")),
//							spriteIndex, animationLength);
//					sprites.add(sprite);
//					++spriteIndex;
				}
			}
		}
		return sprites;
	}
}
