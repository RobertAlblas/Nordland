package robertalblas.nordland.resource.graphics.XMLImport;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.util.xml.XMLNode;

public class XMLToDrawableStrategyV1 implements XMLToDrawableStrategy{

	@Override
	public List<Drawable> convertXMLNodeToDrawable(XMLNode node) {
		List<Drawable> sprites = new ArrayList<Drawable>();
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
