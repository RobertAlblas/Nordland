package robertalblas.nordland.window;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.world.World;

public interface Screen {

	public void clear();
	public void setSize(int width, int height);
	public void render(int mouseX, int mouseY, int framerate, int updaterate);
	public void setWorld(World world);
	public void renderFixedSprite(int xPosition, int yPosition, Sprite sprite);
	public void renderSprite(int xPosition, int yPosition, Sprite sprite);
	public void setOffset(int xOffset, int yOffset);
	public void centerAt(int x, int y);
	public int getMask();
	public void setMask(int mask);
	public int getScreenWidth();
	public int getScreenHeight();
}
