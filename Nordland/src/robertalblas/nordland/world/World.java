package robertalblas.nordland.world;

import java.util.List;

import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.window.Screen;

public interface World {
	public void update(List<InputAction> inputActions);

	public void render(Screen screen);

	public void addEntity(Entity e);

	public Player getPlayer();

	public int getWidth();

	public int getHeight();
	
	public CollisionMap getCollisionMap();
}
