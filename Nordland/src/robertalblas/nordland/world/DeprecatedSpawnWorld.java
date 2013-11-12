package robertalblas.nordland.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import robertalblas.nordland.resource.graphics.SpriteManager;

@Deprecated
public class DeprecatedSpawnWorld extends DeprecatedWorld{
	
	public DeprecatedSpawnWorld(String path, SpriteManager spriteManager) {
		super(path, spriteManager);
	}
	
	@Override
	protected void loadWorld(String path){
		try{
			BufferedImage image = ImageIO.read(DeprecatedSpawnWorld.class.getResource(path));
			setWidth(image.getWidth());
			setHeight(image.getHeight());
			setTiles(new int[getWidth()*getHeight()]);
			image.getRGB(0, 0,getWidth(),getHeight(),getTiles(),0,getWidth());
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}