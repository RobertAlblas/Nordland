package robertalblas.nordland.util.xml.graphics;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.resource.graphics.Animation;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.util.xml.XMLNode;

//Version 2 supports animations

public class XMLToResourcesStrategyV2 implements XMLToResourcesStrategy {

	@Override
	public List<Drawable> convertXMLNodeToResources(XMLNode node) {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for (XMLNode xmlNode : node.getChildNodes()) {
			if (xmlNode.getElementName().equals("sprite")) {
				Sprite sprite = new Sprite(xmlNode.getAttributeValue("name"),
						Integer.parseInt(xmlNode.getAttributeValue("width")),
						Integer.parseInt(xmlNode.getAttributeValue("height")),
						Integer.parseInt(xmlNode.getAttributeValue("x")),
						Integer.parseInt(xmlNode.getAttributeValue("y")));
				drawables.add(sprite);
			}
			else if(xmlNode.getElementName().equals("animation")){
				int spritePerSecond = Integer.parseInt(xmlNode.getAttributeValue("sprites_per_second"));
				String animationName = xmlNode.getAttributeValue("name");
				List<Sprite> animationSprites = new ArrayList<Sprite>();
				for (XMLNode childNode : xmlNode.getChildNodes()) {
					animationSprites.add(new Sprite(xmlNode.getAttributeValue("name"),
							Integer.parseInt(childNode.getAttributeValue("width")),
							Integer.parseInt(childNode.getAttributeValue("height")),
							Integer.parseInt(childNode.getAttributeValue("x")),
							Integer.parseInt(childNode.getAttributeValue("y"))));
				}
				drawables.add(new Animation(animationName, animationSprites, spritePerSecond));
			}
		}
		return drawables;
	}
}
