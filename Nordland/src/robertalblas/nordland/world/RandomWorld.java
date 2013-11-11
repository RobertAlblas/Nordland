package robertalblas.nordland.world;

import java.util.Random;

import robertalblas.nordland.resource.graphics.SpriteManager;

public class RandomWorld extends World {

	private static final Random random = new Random();

	public RandomWorld(int width, int height, SpriteManager spriteManager) {
		super(width, height, spriteManager);
	}

	protected void generateLevel() {
		int[] values = { 
				0xff000000,
				0xffffffff,
				0xff00ff00};

		
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				int value = 0;
				if(random.nextInt(100) < 50){
					if(x > 0){
						value = tiles[x - 1 + y * width];
					}
					else{
						value = values[random.nextInt(3)];
					}
				}
				else if(random.nextInt(100) < 70){
					if(y > 0){
						value = tiles[x + (y - 1) * width];
					}
					else{
						value = values[random.nextInt(3)];
					}
				}
				else{
					value = values[random.nextInt(3)];
				}
				tiles[x + y * width] = value;
			}
		}
	}
}
