package robertalblas.nordland.resource.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import robertalblas.nordland.resource.ResourceSet;
import robertalblas.nordland.util.log.Logger;
import robertalblas.nordland.util.log.LoggerManager;
import robertalblas.nordland.util.xml.XMLImporter;
import robertalblas.nordland.util.xml.XMLNode;

public class SpriteSheet extends ResourceSet {

	private int spriteSheetWidth;

	public SpriteSheet(String file) {
		super(file);
	}

	@Override
	public void load() {
		int[] pixels = getPixelsFromFile("/texture/" + file + ".png");
		
		XMLImporter xmlImporter = new XMLImporter();
		XMLNode rootNode = xmlImporter.importXMLFile("/texture/" + file + ".xml", "spritesheet");
		
		List<Sprite> resources = processXMLNodes(rootNode);

		for (Sprite s : resources) {
			int[] spritePixels = new int[s.getWidth() * s.getHeight()];
			for (int y = 0; y < s.getHeight(); ++y) {
				for (int x = 0; x < s.getWidth(); ++x) {
					spritePixels[x + y * s.getWidth()] = pixels[x + s.getX()
							+ (y + s.getY()) * spriteSheetWidth];
				}
			}
			s.setPixels(spritePixels);
			this.resources.add(s);
		}
	}

	private List<Sprite> processXMLNodes(XMLNode rootNode) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		for(XMLNode node: rootNode.getChildNodes()){
			sprites.add(new Sprite(
					node.getAttributeValue("name"),
					Integer.parseInt(node.getAttributeValue("width")),
					Integer.parseInt(node.getAttributeValue("height")),
					Integer.parseInt(node.getAttributeValue("x")),
					Integer.parseInt(node.getAttributeValue("y"))));
		}
		return sprites;
	}

	private int[] getPixelsFromFile(String filename) {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(filename));
			spriteSheetWidth = image.getWidth();
			int spriteSheetHeight = image.getHeight();
			int pixels[] = new int[spriteSheetWidth * spriteSheetHeight];
			image.getRGB(0, 0, spriteSheetWidth, spriteSheetHeight, pixels, 0,
					spriteSheetWidth);
			return pixels;

		} catch (IOException e) {
			LoggerManager.getInstance().getDefaultLogger().log("Error reading file: " + filename, Logger.LOGTYPE_ERROR);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void unload() {
		resources.clear();
		resources = null;
	}

}
