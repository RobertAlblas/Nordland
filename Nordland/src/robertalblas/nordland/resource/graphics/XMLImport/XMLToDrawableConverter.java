package robertalblas.nordland.resource.graphics.XMLImport;

import java.util.HashMap;
import java.util.List;

import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.util.xml.XMLNode;

public class XMLToDrawableConverter {

	private static final HashMap<String, XMLToDrawableStrategy> STRATEGY_MAPPING;
	static {
		STRATEGY_MAPPING = new HashMap<String, XMLToDrawableStrategy>();

		STRATEGY_MAPPING.put(null, new XMLToDrawableStrategyV1());
		STRATEGY_MAPPING.put("2", new XMLToDrawableStrategyV2());
	}

	public List<Drawable> convertXMLNodeToDrawable(XMLNode node,
			String spriteSheetVersie) {
		return STRATEGY_MAPPING.get(spriteSheetVersie)
				.convertXMLNodeToDrawable(node);
	}

}
