package robertalblas.nordland.entity;

import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.renderer.Renderer;
import robertalblas.nordland.world.World;

public interface Entity {
	public void update(List<InputAction> inputActions);
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public void setWorld(World world);
	public String getCurrentSprite();
	public void setCurrentSprite(String sprite);
	public void render(Renderer renderer);
}
