package robertalblas.nordland.entity.loader;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.StaticEntity;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class StaticEntityFactory extends EntityFactory {

	@Override
	public Entity createEntity(SpriteSet spriteSet, int x, int y) {
		Entity entity = new StaticEntity(null, spriteSet, x, y);
		entity.setCurrentDrawable(super.getRandomDrawable(spriteSet));
		return entity;
	}

}
