package robertalblas.nordland.util.xml.graphics;

import java.util.HashMap;
import java.util.List;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.util.xml.XMLNode;


public class XMLToResourcesConverter {


	private static final HashMap<String, XMLToResourcesStrategy> STRATEGY_MAPPING;
	static {
		STRATEGY_MAPPING = new HashMap<String, XMLToResourcesStrategy>();

		STRATEGY_MAPPING.put(null, new XMLToResourcesStrategyV1());
		STRATEGY_MAPPING.put("1", new XMLToResourcesStrategyV1());
		STRATEGY_MAPPING.put("2", new XMLToResourcesStrategyV2());
	}

	public List<Sprite> convertXMLToSprites(XMLNode node,
			String spriteSheetVersie) {
		return STRATEGY_MAPPING.get(spriteSheetVersie)
				.convertXMLNodeToResources(node);
	}

}
