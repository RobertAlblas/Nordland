package robertalblas.nordland.entity;

import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.world.World;

public class Terrain extends BaseEntity {

	public Terrain(World world, SpriteSet spriteSheet, int x, int y) {
		super(world, spriteSheet, null, x, y);
	}	

}
