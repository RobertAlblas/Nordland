package robertalblas.nordland.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import robertalblas.nordland.resource.graphics.SpriteManager;

public class SpawnWorld extends World{
	
	public SpawnWorld(String path, SpriteManager spriteManager) {
		super(path, spriteManager);
	}
	
	@Override
	protected void loadWorld(String path){
		try{
			BufferedImage image = ImageIO.read(SpawnWorld.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width*height];
			image.getRGB(0, 0,width,height,tiles,0,width);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
