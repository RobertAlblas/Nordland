package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.resource.Resource;

public class Sprite extends Resource{
	private final int width, height;
	private final int x, y;
	private int[] pixels;
	
	public Sprite(String name, int width, int height, int x, int y) {
		super(name);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void setPixels(int[] pixels){
		this.pixels = pixels;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public int[] getPixels(){
		return pixels;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}