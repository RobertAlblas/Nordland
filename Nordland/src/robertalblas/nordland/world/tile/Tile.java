package robertalblas.nordland.world.tile;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.window.Screen;

public class Tile implements Collidable{

	private final int x,y;
	private final int width, height;
	private final Sprite sprite;
	private final boolean solid;
	
	public static Tile createSolidTile(CollisionMap collisionMap, Sprite sprite, int x, int y){
		Tile tile = new Tile(sprite, true, x, y);
		
		for(int i = 0; i < tile.getWidth(); i++){
			for(int j = 0; j < tile.getHeight(); j++){
				collisionMap.setCollidableAt(i + x - tile.getWidth() / 2, j + y - tile.getHeight() / 2, tile);
			}
		}
		
		return tile;
	}
	
	public static Tile createTile(Sprite sprite, int x, int y){
		Tile tile = new Tile(sprite, false, x, y);		
		return tile;
	}
	
	private Tile(Sprite sprite, boolean solid, int x, int y) {
		this.sprite = sprite;
		this.solid = solid;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.x = x;
		this.y = y;
	}
	
	public void render(Screen Screen){
		Screen.renderFixedSprite(x, y, sprite);
	}
	
	public boolean isSolid(){
		return solid;
	}

	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void onCollision() {
		//satisfy the interface
	}
	
}
