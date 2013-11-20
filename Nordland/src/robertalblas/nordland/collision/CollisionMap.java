package robertalblas.nordland.collision;

import robertalblas.nordland.exception.CollisionException;
import robertalblas.nordland.resource.graphics.Drawable;

public class CollisionMap {

	private Collidable[] collidables;
	private final int width;

	public CollisionMap(int width, int height) {
		this.collidables = new Collidable[width * height];
		this.width = width;
	}

	public void clear() {
		for (int i = 0; i < collidables.length; i++) {
			if (collidables[i] != null) {
				if (collidables[i].isMovable()) {
					collidables[i] = null;
				}
			}
		}
	}

	public void renderCollidable(Collidable collidable)
			throws CollisionException {
		Drawable drawable = collidable.getDrawable();
		// Center drawable coordinates
		int collidableXPosition = collidable.getX() - drawable.getWidth() / 2;
		int collidableYPosition = collidable.getY() - drawable.getHeight() / 2;
		
		int pixels[] = drawable.getPixels();
		for (int x = 0; x < drawable.getWidth(); x++) {
			for (int y = 0; y < drawable.getHeight(); y++) {
				if (pixels[x + y * drawable.getWidth()] != 0xffff00ff) {
					int mapIndex = x + collidableXPosition
							+ ((y + collidableYPosition) * this.width);
					try {
						if (collidables[mapIndex] != null) {
							throw new CollisionException(collidables[mapIndex],
									"Collision detected");
						} else {
							collidables[mapIndex] = collidable;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						// When an entity moves out of the map
					}

				}
			}
		}
	}

	public Collidable checkCollisionAt(Collidable collidable,
			int xPosition, int yPosition) throws CollisionException {
		for (Drawable d : collidable.getDrawables()) {
			Collidable collision = checkCollisionForDrawableAt(d,
					xPosition, yPosition);
			if (collision != null) {
				return collision;
			}
		}
		return null;
	}

	private Collidable checkCollisionForDrawableAt(Drawable drawable,
			int xPosition, int yPosition) throws CollisionException {

		// Center drawable coordinates
		int centeredXPosition = xPosition - drawable.getWidth() / 2;
		int centeredYPosition = yPosition - drawable.getHeight() / 2;

		int pixels[] = drawable.getPixels();

		for (int x = 0; x < drawable.getWidth(); x++) {
			for (int y = 0; y < drawable.getHeight(); y++) {
				if (pixels[x + y * drawable.getWidth()] != 0xffff00ff) {
					int mapIndex = x + centeredXPosition
							+ ((y + centeredYPosition) * this.width);
					try {
						if (collidables[mapIndex] != null) {
							return collidables[mapIndex];
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						// When an entity moves out of the map
					}

				}
			}
		}

		return null;
	}
}
