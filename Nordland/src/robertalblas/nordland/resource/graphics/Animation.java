package robertalblas.nordland.resource.graphics;

import java.util.List;

public class Animation extends Drawable {

	private List<Sprite> sprites;
	private int currentSprite;
	
	public Animation(String name, List<Sprite> sprites){
		super(name, sprites.get(0).getWidth(), sprites.get(0).getHeight());
		this.sprites = sprites;
		this.currentSprite = 0;
	}
	
	public int getLength(){
		return this.sprites.size();
	}
	
	public void addSprite(Sprite sprite){	
		this.sprites.add(sprite);
	}
	
	public void nextSprite(){
		this.currentSprite++;
		if(this.currentSprite == this.getLength()){
			this.currentSprite = 0;
		}
	}

	@Override
	public int[] getPixels() {
		return this.sprites.get(this.currentSprite).getPixels();
	}
}
