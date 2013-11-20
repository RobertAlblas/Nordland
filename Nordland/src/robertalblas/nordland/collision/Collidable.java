package robertalblas.nordland.collision;

import java.util.List;

import robertalblas.nordland.resource.graphics.Drawable;

public interface Collidable {
	public abstract void onCollision();

	public abstract int getX();

	public abstract int getY();
	
	public abstract boolean isMovable();

	public abstract Drawable getDrawable();

	// The following method is only required for Collidables using
	// checkCollisionWithCollidableAt().
	public abstract List<Drawable> getDrawables();
}
