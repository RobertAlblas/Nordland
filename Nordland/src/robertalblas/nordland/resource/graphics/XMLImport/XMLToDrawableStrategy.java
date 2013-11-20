package robertalblas.nordland.resource.graphics.XMLImport;

import java.util.List;

import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.util.xml.XMLNode;

public interface XMLToDrawableStrategy {
	public List<Drawable> convertXMLNodeToDrawable(XMLNode node);
}