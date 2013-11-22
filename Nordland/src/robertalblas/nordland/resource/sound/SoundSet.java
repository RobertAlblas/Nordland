package robertalblas.nordland.resource.sound;

import robertalblas.nordland.exception.FiletypeNotSupportedException;
import robertalblas.nordland.resource.ResourceSet;
import robertalblas.nordland.resource.sound.loader.SoundLoader;
import robertalblas.nordland.util.log.Logger;
import robertalblas.nordland.util.log.LoggerManager;
import robertalblas.nordland.util.xml.XMLImporter;
import robertalblas.nordland.util.xml.XMLNode;

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

}
