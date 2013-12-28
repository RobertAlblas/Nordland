package robertalblas.nordland.entity;

import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public abstract class BaseEntity implements Entity{

	private int x, y;
	private int width, height;
	private SpriteSet spriteSheet;
	private World world;
	private String currentSprite;
	
	public BaseEntity(World world, SpriteSet spriteSheet, int x, int y){
		this.world = world;
		this.x = x;
		this.y = y;
		this.spriteSheet = spriteSheet;
		Sprite firstSprite = (Sprite) spriteSheet.getResources().get(0);
		this.currentSprite = firstSprite.getName();
		this.width = firstSprite.getWidth();
		this.height = firstSprite.getHeight();
	}
	
	@Override
	public void update(List<InputAction> inputActions) {
			
	}

	@Override
	public void render(Screen screen) {
		screen.renderFixedDrawable(x, y,
				(Drawable) spriteSheet.getResource(currentSprite));
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

	public SpriteSet getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public String getCurrentSprite() {
		return currentSprite;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public World getWorld() {
		return world;
	}
	
	@Override
	public void setWorld(World world){
		this.world = world;
	}
	
	public void setCurrentSprite(String sprite){
		this.currentSprite = sprite;
	}
	
	public void setCurrentSpriteMoving(){
		this.currentSprite += "_moving";
	}
}
