package robertalblas.nordland.world.tile;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;

@Deprecated
public class DeprecatedTile {

	private int x,y;
	private SpriteSheet spriteSheet;
	private String[] spriteNames;
	private boolean solid;
	
	public DeprecatedTile(SpriteSheet spriteSheet, String[] spriteNames, boolean solid) {
		this.spriteNames = spriteNames;
		this.spriteSheet = spriteSheet;
		this.solid = solid;
	}
	
	public void render(int x, int y, Screen Screen){
		Screen.renderFixedSprite(x << 4, y << 4, (Sprite)spriteSheet.getResource(spriteNames[0]));
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
}
