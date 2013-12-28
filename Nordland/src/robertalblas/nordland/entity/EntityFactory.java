package robertalblas.nordland.entity;

import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class EntityFactory {

	public static Entity createEntity(String type, SpriteSet spriteSet, int x, int y) throws UnknownEntityTypeException {
		Entity entity;
		
		if(type.equals("staticEntity")){
			entity = new StaticEntity(null, spriteSet, x, y);
		}
		else if(type.equals("terrainEntity")){
			entity = new TerrainEntity(null, spriteSet, x, y);
			// pick random sprite
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
