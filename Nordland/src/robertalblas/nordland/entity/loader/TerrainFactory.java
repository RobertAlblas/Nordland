package robertalblas.nordland.entity.loader;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Terrain;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class TerrainFactory extends EntityFactory{

	@Override
	public Entity createEntity(SpriteSet spriteSet, int x, int y) {
		Entity entity = new Terrain(null, spriteSet, x, y);
		entity.setCurrentDrawable(super.getRandomDrawable(spriteSet));
		return entity;
	}

}
