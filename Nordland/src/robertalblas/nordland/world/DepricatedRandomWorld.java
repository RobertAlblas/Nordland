package robertalblas.nordland.world;

import java.util.Random;

import robertalblas.nordland.resource.graphics.SpriteManager;

@Deprecated
public class DepricatedRandomWorld extends DeprecatedWorld {

	private static final Random random = new Random();

	public DepricatedRandomWorld(int width, int height, SpriteManager spriteManager) {
		super(width, height, spriteManager);
	}

	protected void generateLevel() {
		int[] values = { 
				0xff000000,
				0xffffffff,
				0xff00ff00};

		
		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				int value = 0;
				if(random.nextInt(100) < 50){
					if(x > 0){
						value = getTiles()[x - 1 + y * getWidth()];
					}
					else{
						value = values[random.nextInt(3)];
					}
				}
				else if(random.nextInt(100) < 70){
					if(y > 0){
						value = getTiles()[x + (y - 1) * getWidth()];
					}
					else{
						value = values[random.nextInt(3)];
					}
				}
				else{
					value = values[random.nextInt(3)];
				}
				getTiles()[x + y * getWidth()] = value;
			}
		}
	}
}
