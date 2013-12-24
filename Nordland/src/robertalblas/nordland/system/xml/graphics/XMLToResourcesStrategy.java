package robertalblas.nordland.system.xml.graphics;

import java.util.List;

import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.system.xml.XMLNode;

public interface XMLToResourcesStrategy {
	public List<Drawable> convertXMLNodeToResources(XMLNode node);
}
