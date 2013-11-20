package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.resource.Resource;

public abstract class Drawable extends Resource {
	private final int width, height;

	public Drawable(String name, int width, int height) {
		super(name);
		this.width = width;
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public abstract int[] getPixels();
	public abstract void nextSprite();
	
}
