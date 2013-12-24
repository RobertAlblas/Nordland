package robertalblas.nordland.resource.graphics.loader;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.resource.graphics.Animation;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.system.xml.XMLNode;

public class DrawableLoaderStrategyV2 implements DrawableLoaderStrategy{

	@Override
	public List<Drawable> loadDrawable(XMLNode node) {
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
				List<Sprite> animationSprites = new ArrayList<Sprite>();
				String name = xmlNode.getAttributeValue("name");
				int amountOfSpritesPerSecond = Integer.parseInt(xmlNode.getAttributeValue("sprites_per_second"));
				for (XMLNode childNode : xmlNode.getChildNodes()) {
					Sprite sprite = new Sprite(name,
							Integer.parseInt(childNode.getAttributeValue("width")),
							Integer.parseInt(childNode.getAttributeValue("height")),
							Integer.parseInt(childNode.getAttributeValue("x")),
							Integer.parseInt(childNode.getAttributeValue("y")));
					animationSprites.add(sprite);
				}
				drawables.add(new Animation(name, animationSprites, amountOfSpritesPerSecond));
			}
		}
		return drawables;
	}

}
