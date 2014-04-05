package robertalblas.nordland.resource.sound;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.exception.FiletypeNotSupportedException;
import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.ResourceSet;
import robertalblas.nordland.resource.sound.loader.SoundLoader;
import robertalblas.nordland.system.log.Logger;
import robertalblas.nordland.system.log.LoggerManager;
import robertalblas.nordland.system.xml.XMLImporter;
import robertalblas.nordland.system.xml.XMLNode;

public class SoundSet extends ResourceSet {

	public SoundSet(String file) {
		super(file);
	}

	@Override
	public void load() {
		XMLImporter xmlImporter = new XMLImporter();
		XMLNode rootNode = xmlImporter.importXMLFile("/sound/" + getFile() + ".xml",
				"soundset");

		SoundLoader soundLoader = new SoundLoader();

		for (XMLNode soundNode : rootNode.getChildNodes()) {
			try {
				this.getResources().add(soundLoader.loadSound("/sound/" + getFile() + "/"
						+ soundNode.getAttributeValue("filename")));
			} catch (FiletypeNotSupportedException e) {
				LoggerManager.getInstance().getDefaultLogger()
						.log(e.getMessage(), Logger.LOGTYPE_WARNING);
			}
		}

	}

	@Override
	public ResourceSet clone() {
		ResourceSet clone = new SoundSet(getFile());
		List<Resource> resources = new ArrayList<Resource>();
		for(Resource r: this.getResources()){
			resources.add(r.getClone());
		}
		clone.setResources(resources);
		return clone;
	}

}
