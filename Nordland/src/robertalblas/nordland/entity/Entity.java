package robertalblas.nordland.entity;

import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.renderer.Renderer;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.world.World;

public interface Entity {
	public void update(List<InputAction> inputActions);
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public void setWorld(World world);
	public String getCurrentDrawable();
	public void setCurrentDrawable(String drawable);
	public void render(Renderer renderer);
	public Drawable getDrawable();
	public List<Drawable> getDrawables();
}
