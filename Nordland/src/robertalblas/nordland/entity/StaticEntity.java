package robertalblas.nordland.entity;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.graphics.Animation;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
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
	public Drawable getDrawable() {
		return (Drawable) getSpriteSheet().getResource(getCurrentSprite());
	}

	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for(Resource r :  getSpriteSheet().getResources()){
			if(r instanceof Sprite){
				drawables.add((Drawable)r);
			}
			else if(r instanceof Animation){
				for(Sprite s: ((Animation)r).getSprites()){
					drawables.add(s);
				}
			}
		}
		return drawables;
	}

	@Override
	public boolean isMovable() {
		return false;
	}

}
