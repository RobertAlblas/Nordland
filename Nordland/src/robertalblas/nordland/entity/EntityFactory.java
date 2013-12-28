package robertalblas.nordland.entity;

import java.util.Random;

import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class EntityFactory {

	public static Entity createEntity(String type, SpriteSet spriteSet, int x, int y) throws UnknownEntityTypeException {
		Entity entity;
		
		if(type.equals("staticEntity")){
			entity = new StaticEntity(null, spriteSet, x, y);
		}
		else if(type.equals("terrainEntity")){
			entity = new TerrainEntity(null, spriteSet, x, y);
			Random random = new Random();
			Sprite sprite =  (Sprite) spriteSet.getResources().get(random.nextInt(spriteSet.getResources().size()));
			entity.setCurrentSprite(sprite.getName());
		}
		else if(type.equals("player")){
			entity = new Player(null, spriteSet, x, y);
		}
		else{
			throw new UnknownEntityTypeException("Entity type unknown: " + type);
		}
		
		return entity;
	}

}
