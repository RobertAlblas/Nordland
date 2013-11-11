package robertalblas.nordland.entity;

import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public abstract class Entity {
	private int x, y;
	private boolean removed = false;
	private World world;
	private SpriteSheet spriteSheet;
	
	
	public Entity(SpriteSheet spriteSheet){
		this.setSpriteSheet(spriteSheet);
	}
	
	public void init(World world){
		this.setWorld(world);
	}
	
	public void update(){
		
	}
	
	public void render(Screen Screen){
		
	}
	
	public boolean collision(double xa, double ya) {
		return false;
	}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}
