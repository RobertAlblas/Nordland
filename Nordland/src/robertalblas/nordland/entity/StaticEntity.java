package robertalblas.nordland.entity;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.world.World;

public class StaticEntity extends BaseEntity implements Collidable{

	public StaticEntity(World world, SpriteSet spriteSheet, int x, int y) {
		super(world, spriteSheet, x, y);
	}

	@Override
	public void onCollision() {

	}

	@Override
	public boolean isMovable() {
		return false;
	}

}
