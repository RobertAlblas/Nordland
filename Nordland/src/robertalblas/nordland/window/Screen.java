package robertalblas.nordland.window;

import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.world.World;

public interface Screen {

	public void clear();
	public void setSize(int width, int height);
	public void render(int mouseX, int mouseY, int framerate, int updaterate);
	public void setWorld(World world);
	public void renderFixedDrawable(int xPosition, int yPosition, Drawable drawable);
	public void renderDrawable(int xPosition, int yPosition, Drawable drawable);
	public void setOffset(int xOffset, int yOffset);
	public void centerAt(int x, int y);
	public int getMask();
	public void setDarkMask(int mask);
	public void setLightMask(int mask);
	public int getScreenWidth();
	public int getScreenHeight();
}
