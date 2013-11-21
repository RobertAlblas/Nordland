package robertalblas.nordland.util.xml.graphics;

import java.util.List;

import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.util.xml.XMLNode;

public interface XMLToResourcesStrategy {
	public List<Drawable> convertXMLNodeToResources(XMLNode node);
}
