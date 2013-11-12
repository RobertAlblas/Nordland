package robertalblas.nordland.entity;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;

public class Player implements Entity {

	private int x, y;
	private int width, height;
	private SpriteSheet spriteSheet;
	private String currentSprite;
	
	public Player(SpriteSheet spriteSheet, int x, int y) {
		this.x = x;
		this.y = y;
		Sprite firstSprite = (Sprite)spriteSheet.getResources().get(0);
		this.currentSprite = firstSprite.getName();
		this.width = firstSprite.getWidth();
		this.height = firstSprite.getHeight();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Screen screen) {
		screen.renderFixedSprite(x, y, (Sprite)spriteSheet.getResource(currentSprite));
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
