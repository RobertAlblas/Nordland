package robertalblas.nordland.resource.graphics;


public class Sprite extends Drawable{
	private final int x, y;
	private int[] pixels;
	
	public Sprite(String name, int width, int height, int x, int y) {
		super(name, width, height);
		this.x = x;
		this.y = y;
	}
	
	public void setPixels(int[] pixels){
		this.pixels = pixels;
	}
	
	@Override
	public int[] getPixels(){
		return pixels;
	}

	@Override
	public void nextSprite() {
		//unimplemented
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}