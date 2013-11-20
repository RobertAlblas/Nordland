package robertalblas.nordland.resource.graphics;

import java.util.List;

import robertalblas.nordland.resource.Resource;

public class Animation extends Resource {

	private List<Sprite> sprites;
	private int currentSprite;
	
	public Animation(String name) {
		super(name);
		this.currentSprite = 0;
	}
	
	public Animation(String name, List<Sprite> sprites){
		this(name);
		this.sprites = sprites;
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
	
	public Sprite getSprite(){
		return this.sprites.get(currentSprite);
	}

}
