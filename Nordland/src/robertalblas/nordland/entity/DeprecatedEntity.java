package robertalblas.nordland.entity;

import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.DeprecatedWorld;

@Deprecated
public abstract class DeprecatedEntity {
	private int x, y;
	private boolean removed = false;
	private DeprecatedWorld world;
	private SpriteSheet spriteSheet;
	
	
	public DeprecatedEntity(SpriteSheet spriteSheet){
		this.setSpriteSheet(spriteSheet);
	}
	
	public void init(DeprecatedWorld world){
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

	public DeprecatedWorld getWorld() {
		return world;
	}

	public void setWorld(DeprecatedWorld world) {
		this.world = world;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}
