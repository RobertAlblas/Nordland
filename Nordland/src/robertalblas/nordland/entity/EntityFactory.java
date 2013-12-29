package robertalblas.nordland.entity;

import java.util.Random;

import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class EntityFactory {

	public static Entity createEntity(String type, SpriteSet spriteSet, int x, int y) throws UnknownEntityTypeException {
		Entity entity;
		
		if(type.equals("staticEntity")){
			entity = new StaticEntity(null, spriteSet, x, y);
		}
		else if(type.equals("animatedCollidableTerrain")){
			entity = new AnimatedCollidableTerrain(null, spriteSet, x, y);
		}
		else if(type.equals("terrain")){
			entity = new Terrain(null, spriteSet, x, y);
		}
		else if(type.equals("player")){
			entity = new Player(null, spriteSet, x, y);
		}
		else{
			throw new UnknownEntityTypeException("Entity type unknown: " + type);
		}
		
		entity.setCurrentDrawable(getRandomDrawable(spriteSet));
		return entity;
	}
	
	private static String getRandomDrawable(SpriteSet spriteSet){
		Random random = new Random();
		Drawable drawable =  (Drawable) spriteSet.getResources().get(random.nextInt(spriteSet.getResources().size()));
		return drawable.getName();
	}

}
