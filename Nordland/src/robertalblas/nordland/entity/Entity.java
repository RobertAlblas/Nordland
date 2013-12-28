package robertalblas.nordland.entity;

import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public interface Entity {
	public void update(List<InputAction> inputActions);
	public void render(Screen screen);
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public void setWorld(World world);
}
