package robertalblas.nordland.resource.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import robertalblas.nordland.resource.ResourceSet;
import robertalblas.nordland.resource.graphics.XMLImport.XMLToDrawableConverter;
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
		int[] pixels = getPixelsFromFile("/texture/" + getFile() + ".png");

		XMLImporter xmlImporter = new XMLImporter();
		XMLNode rootNode = xmlImporter.importXMLFile("/texture/" + getFile()
				+ ".xml", "spritesheet");

		List<Drawable> resources = processXMLNodes(rootNode);

		for (Drawable d : resources) {
			processDrawable(pixels, d);
		}
	}

	private void processDrawable(int[] pixels, Drawable d) {
		if (d instanceof Sprite) {
			processSprite(pixels, (Sprite)d);
		} else if (d instanceof Animation) {
			processAnimation(pixels, (Animation)d);
		} else {
			throw new UnsupportedOperationException();
		}
		this.getResources().add(d);
	}
	
	private void processAnimation(int[] pixels, Animation a) {
		for (Sprite s : a.getSprites()) {
			processSprite(pixels, s);
		}		
	}

	private void processSprite(int[] pixels, Sprite s){
		int[] spritePixels = new int[s.getWidth() * s.getHeight()];
		for (int y = 0; y < s.getHeight(); ++y) {
			for (int x = 0; x < s.getWidth(); ++x) {
				spritePixels[x + y * s.getWidth()] = pixels[x + s.getX()
						+ (y + s.getY()) * spriteSheetWidth];
			}
		}
		s.setPixels(spritePixels);
	}

	private List<Drawable> processXMLNodes(XMLNode rootNode) {
		String version = rootNode.getAttributeValue("version");

		XMLToDrawableConverter xmlNaarSpriteConverter = new XMLToDrawableConverter();
		return xmlNaarSpriteConverter.convertXMLNodeToDrawable(rootNode,
				version);
	}

	private int[] getPixelsFromFile(String filename) {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class
					.getResource(filename));
			spriteSheetWidth = image.getWidth();
			int spriteSheetHeight = image.getHeight();
			int pixels[] = new int[spriteSheetWidth * spriteSheetHeight];
			image.getRGB(0, 0, spriteSheetWidth, spriteSheetHeight, pixels, 0,
					spriteSheetWidth);
			return pixels;

		} catch (IOException e) {
			LoggerManager
					.getInstance()
					.getDefaultLogger()
					.log("Error reading file: " + filename,
							Logger.LOGTYPE_ERROR);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void unload() {
		getResources().clear();
		setResources(null);
	}

}
