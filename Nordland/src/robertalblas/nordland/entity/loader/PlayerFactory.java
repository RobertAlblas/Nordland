package robertalblas.nordland.entity.loader;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.resource.graphics.SpriteSet;

public class PlayerFactory extends EntityFactory {

	@Override
	public Entity createEntity(SpriteSet spriteSet, int x, int y) {
		return new Player(null, spriteSet, x, y);
	}

}
