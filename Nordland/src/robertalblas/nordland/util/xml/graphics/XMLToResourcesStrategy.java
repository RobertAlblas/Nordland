package robertalblas.nordland.util.xml.graphics;

import java.util.List;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.util.xml.XMLNode;

public interface XMLToResourcesStrategy {
	public List<Sprite> convertXMLNodeToResources(XMLNode node);
}
