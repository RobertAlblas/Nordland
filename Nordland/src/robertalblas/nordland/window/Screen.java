package robertalblas.nordland.window;

import robertalblas.nordland.world.World;

public interface Screen {

	public void setSize(int width, int height);

	public void render(int mouseX, int mouseY, int framerate, int updaterate);

	public void setWorld(World world);
}
