package robertalblas.nordland.entity;

import java.util.Random;

import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public abstract class Entity {
	public int x, y;
	private boolean removed = false;
	protected World world;
	protected final Random random = new Random();
	protected SpriteSheet spriteSheet;
	
	
	public Entity(SpriteSheet spriteSheet){
		this.spriteSheet = spriteSheet;
	}
	
	public void init(World world){
		this.world = world;
	}
	
	public void update(){
		
	}
	
	public void render(Screen Screen){
		
	}
	
	public boolean collision(double xa, double ya) {
		return false;
	}
	
	public void remove(){
		//remove from world
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
}
