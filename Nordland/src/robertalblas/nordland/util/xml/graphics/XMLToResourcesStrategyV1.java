package robertalblas.nordland.util.xml.graphics;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.util.xml.XMLNode;

public class XMLToResourcesStrategyV1 implements XMLToResourcesStrategy {

	@Override
	public List<Sprite> convertXMLNodeToResources(XMLNode node) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		for (XMLNode xmlNode : node.getChildNodes()) {
			Sprite sprite = new Sprite(xmlNode.getAttributeValue("name"), Integer
					.parseInt(xmlNode.getAttributeValue("width")), Integer
					.parseInt(xmlNode.getAttributeValue("height")), Integer
					.parseInt(xmlNode.getAttributeValue("x")), Integer
					.parseInt(xmlNode.getAttributeValue("y")));
			sprites.add(sprite);
		}
		return sprites;
	}
}
