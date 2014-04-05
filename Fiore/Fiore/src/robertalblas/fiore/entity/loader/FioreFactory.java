package robertalblas.fiore.entity.loader;

import robertalblas.fiore.entity.Fiore;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.loader.PlayerFactory;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.resource.sound.SoundSet;

public class FioreFactory extends PlayerFactory {

	@Override
	public Entity createEntity(SpriteSet spriteSet, SoundSet soundSet, int x, int y) {
		return new Fiore(null, spriteSet, soundSet, x, y);
	}

}
