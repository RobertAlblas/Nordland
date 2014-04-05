package robertalblas.nordland.entity.loader;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.resource.sound.SoundSet;

public class PlayerFactory extends EntityFactory {

	@Override
	public Entity createEntity(SpriteSet spriteSet, SoundSet soundSet, int x, int y) {
		return new Player(null, spriteSet, soundSet, x, y);
	}

}
