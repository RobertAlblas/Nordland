package robertalblas.nordland.resource.graphics.XMLImport;

import java.util.HashMap;
import java.util.List;

import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.util.xml.XMLNode;

public class DrawableLoader {

	private static final HashMap<String, DrawableLoaderStrategy> STRATEGY_MAPPING;
	static {
		STRATEGY_MAPPING = new HashMap<String, DrawableLoaderStrategy>();

		STRATEGY_MAPPING.put(null, new DrawableLoaderStrategyV1());
		STRATEGY_MAPPING.put("2", new DrawableLoaderStrategyV2());
	}

	public List<Drawable> loadDrawable(XMLNode node,
			String spriteSheetVersie) {
		return STRATEGY_MAPPING.get(spriteSheetVersie)
				.loadDrawable(node);
	}

}
