package robertalblas.nordland.entity.loader;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Terrain;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.resource.sound.SoundSet;

public class TerrainFactory extends EntityFactory{

	@Override
	public Entity createEntity(SpriteSet spriteSet, SoundSet soundSet, int x, int y) {
		Entity entity = new Terrain(null, spriteSet, x, y);
		entity.setCurrentDrawable(super.getRandomDrawable(spriteSet));
		return entity;
	}

}
