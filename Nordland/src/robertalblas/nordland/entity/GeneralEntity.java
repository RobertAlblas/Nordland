package robertalblas.nordland.entity;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.graphics.Animation;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.system.log.Logger;
import robertalblas.nordland.system.log.LoggerManager;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public abstract class GeneralEntity implements Entity, Collidable{

	private int x, y;
	private int width, height;
	private SpriteSet spriteSheet;
	private World world;
	private String currentSprite;
	
	public GeneralEntity(World world, SpriteSet spriteSheet, int x, int y){
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

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public void onCollision() {
		LoggerManager.getInstance().getDefaultLogger().log("Collision", Logger.LOGTYPE_DEBUG);
	}

	@Override
	public Drawable getDrawable() {
		return (Drawable) spriteSheet.getResource(currentSprite);
	}

	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for(Resource r :  spriteSheet.getResources()){
			if(r instanceof Sprite){
				drawables.add((Drawable)r);
			}
			else if(r instanceof Animation){
				for(Sprite s: ((Animation)r).getSprites()){
					drawables.add(s);
				}
			}
		}
		return drawables;
	}

	@Override
	public boolean isMovable() {
		return false;
	}

	public World getWorld() {
		return world;
	}
	
	public void setCurrentSprite(String sprite){
		this.currentSprite = sprite;
	}
	
	public void setCurrentSpriteMoving(){
		this.currentSprite += "_moving";
	}
}
