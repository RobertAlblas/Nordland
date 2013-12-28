package robertalblas.nordland.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.resource.world.WorldResourceManager;
import robertalblas.nordland.system.xml.XMLImporter;
import robertalblas.nordland.system.xml.XMLNode;

public class ResourceLoader {

	private HashMap<ResourceManager, String> resources;
	private List<ResourceManager> resourceManagers;

	public ResourceLoader() {
		this.resources = new HashMap<ResourceManager, String>();
		this.resourceManagers = new ArrayList<ResourceManager>();
	}

	public void addResourceManager(ResourceManager resourceManager) {
		resourceManagers.add(resourceManager);
	}

	public void loadResources(String file) throws NumberFormatException, XMLParseException, UnknownEntityTypeException {
		findResources(file);

		for (Entry<ResourceManager, String> entry : resources.entrySet()) {
			entry.getKey().loadResourceSet(entry.getValue());
		}
	}

	private void findResources(String file) throws XMLParseException, NumberFormatException, UnknownEntityTypeException {
		XMLImporter xmlImporter = new XMLImporter();
		XMLNode rootNode = xmlImporter.importXMLFile("/" + file + ".xml", "resources");

		for (XMLNode resource : rootNode.getChildNodes()) {
			parseXMLResource(resource);
		}
	}

	private void parseXMLResource(XMLNode resource) throws XMLParseException, NumberFormatException, UnknownEntityTypeException {
		if (resource.getElementName().equals("spriteset")) {
			for(ResourceManager m : resourceManagers){
				if(m instanceof SpriteManager){
					m.loadResourceSet(resource.getAttributeValue("name"));
				}
			}
		} else if (resource.getElementName().equals("soundset")) {
			for(ResourceManager m : resourceManagers){
				if(m instanceof SoundManager){
					m.loadResourceSet(resource.getAttributeValue("name"));
				}
			}
		} else if (resource.getElementName().equals("world")) {
			for(ResourceManager m : resourceManagers){
				if(m instanceof WorldResourceManager){
					m.loadResourceSet(resource.getAttributeValue("name"));
				}
			}
		}
		else{
			throw new XMLParseException("Unknown resource: " + resource.getElementName());
		}
	}
}
