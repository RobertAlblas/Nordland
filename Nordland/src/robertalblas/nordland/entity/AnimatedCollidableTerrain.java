package robertalblas.nordland.entity;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.world.World;

public class AnimatedCollidableTerrain extends Terrain implements Collidable{

	public AnimatedCollidableTerrain(World world, SpriteSet spriteSheet, int x, int y) {
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
