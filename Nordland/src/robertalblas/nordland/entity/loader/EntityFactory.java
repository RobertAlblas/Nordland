package robertalblas.nordland.entity.loader;

import java.util.Random;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.SpriteSet;

public abstract class EntityFactory {
	
	public abstract Entity createEntity(SpriteSet spriteSet, int x, int y);
	
	protected static String getRandomDrawable(SpriteSet spriteSet){
		Random random = new Random();
		Drawable drawable =  (Drawable) spriteSet.getResources().get(random.nextInt(spriteSet.getResources().size()));
		return drawable.getName();
	}

}
