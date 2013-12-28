package robertalblas.nordland.world;

import java.util.List;

import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.window.Screen;

public interface World {
	public void tick(List<InputAction> inputActions);

	public void render(Screen screen);

	public void addEntity(Entity e);

	public Player getPlayer();

	public int getWidth();

	public int getHeight();
	
	public CollisionMap getCollisionMap();

	public TickTimerManager getTickTimerManager();
	
	public SpriteManager getSpriteManager();
	
	public SoundManager getSoundManager();
}
