package robertalblas.nordland.resource.graphics;

import java.util.List;

import robertalblas.nordland.system.timer.TickTimerRunnable;

public class Animation extends Drawable implements TickTimerRunnable{

	private List<Sprite> sprites;
	private int currentSprite;
	private int amountOfSpritesPerSecond;
	private boolean isAnimatedIndependently;
	
	public Animation(String name, List<Sprite> sprites, int amountOfSpritesPerSecond){
		super(name, sprites.get(0).getWidth(), sprites.get(0).getHeight());
		this.sprites = sprites;
		this.currentSprite = 0;
		this.setAmountOfSpritesPerSecond(amountOfSpritesPerSecond);
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

	public List<Sprite> getSprites() {
		return sprites;
	}

	public int getAmountOfSpritesPerSecond() {
		return amountOfSpritesPerSecond;
	}

	public void setAmountOfSpritesPerSecond(int amountOfSpritesPerSecond) {
		this.amountOfSpritesPerSecond = amountOfSpritesPerSecond;
	}

	@Override
	public void run() {
		nextSprite();
	}

	public boolean isAnimatedIndependently() {
		return isAnimatedIndependently;
	}

	public void setAnimatedIndependently(boolean isAnimatedIndependently) {
		this.isAnimatedIndependently = isAnimatedIndependently;
	}
}
