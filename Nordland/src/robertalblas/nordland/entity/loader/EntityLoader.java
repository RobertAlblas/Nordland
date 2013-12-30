package robertalblas.nordland.entity.loader;

import java.util.HashMap;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class EntityLoader {

	private static HashMap<String, EntityFactory> factories = new HashMap<String, EntityFactory>();

	public static void addFactory(String entityType, EntityFactory factory) {
		factories.put(entityType, factory);
	}

	public static Entity createEntity(String type, SpriteSet spriteSet, int x, int y) throws UnknownEntityTypeException {
		 EntityFactory factory = factories.get(type);
		 if(factory == null){
			 throw new UnknownEntityTypeException("Unkown Entity type: " + type);
		 }
		 return factory.createEntity(spriteSet, x, y);
	}
}
