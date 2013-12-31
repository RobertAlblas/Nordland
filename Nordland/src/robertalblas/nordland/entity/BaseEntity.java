package robertalblas.nordland.entity;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.renderer.Renderer;
import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.graphics.Animation;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.world.World;

public abstract class BaseEntity implements Entity{

	private int x, y;
	private int width, height;
	private SpriteSet spriteSheet;
	private World world;
	private String currentDrawable;
	
	public BaseEntity(World world, SpriteSet spriteSheet, int x, int y){
		this.world = world;
		this.x = x;
		this.y = y;
		this.spriteSheet = spriteSheet;
		Drawable firstDrawable = (Drawable) spriteSheet.getResources().get(0);
		this.currentDrawable = firstDrawable.getName();
		this.width = firstDrawable.getWidth();
		this.height = firstDrawable.getHeight();
	}
	
	@Override
	public void update(List<InputAction> inputActions) {
			
	}

	@Override
	public void render(Renderer renderer) {
		renderer.renderFixedDrawable(x, y,
				(Drawable) spriteSheet.getResource(currentDrawable));
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

	@Override
	public String getCurrentDrawable() {
		return currentDrawable;
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
	
	@Override
	public void setCurrentDrawable(String sprite){
		this.currentDrawable = sprite;
	}
	
	public void setCurrentSpriteMoving(){
		this.currentDrawable += "_moving";
	}
	
	@Override
	public Drawable getDrawable() {
		return (Drawable)this.getSpriteSheet().getResource(this.getCurrentDrawable());
	}

	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for(Resource r :  getSpriteSheet().getResources()){
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
}
