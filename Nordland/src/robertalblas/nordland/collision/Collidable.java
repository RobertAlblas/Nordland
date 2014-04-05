package robertalblas.nordland.collision;

import java.util.List;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.resource.graphics.Drawable;

public interface Collidable {
	public abstract void onCollision(Entity e);

	public abstract int getX();

	public abstract int getY();

	public abstract Drawable getDrawable();

	// The following method is only required for Collidables using
	// checkCollisionWithCollidableAt().
	public abstract List<Drawable> getDrawables();
}
