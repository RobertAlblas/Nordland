package robertalblas.nordland.entity.loader;

import robertalblas.nordland.entity.AnimatedCollidableTerrain;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class AnimatedCollidableTerrainFactory extends EntityFactory {

	@Override
	public Entity createEntity(SpriteSet spriteSet, int x, int y) {
		Entity entity = new AnimatedCollidableTerrain(null, spriteSet, x, y);
		entity.setCurrentDrawable(super.getRandomDrawable(spriteSet));
		return entity;
	}

}
