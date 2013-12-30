package robertalblas.nordland.world;

import java.util.List;

import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.renderer.Renderer;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.resource.sound.music.MusicPlayer;
import robertalblas.nordland.system.timer.TickTimerManager;

public interface World {
	public void tick(List<InputAction> inputActions);

	public void render(Renderer renderer);

	public void addEntity(Entity e);

	public Player getPlayer();

	public int getWidth();

	public int getHeight();
	
	public CollisionMap getCollisionMap();

	public TickTimerManager getTickTimerManager();
	
	public SpriteManager getSpriteManager();
	
	public SoundManager getSoundManager();

	public void setEntities(List<Entity> entities);

	public void setSoundManager(SoundManager soundManager);

	public void setSpriteManager(SpriteManager spriteManager);

	public void setTickTimerManager(TickTimerManager tickTimerManager);

	public void setWidth(int width);

	public void setHeight(int height);

	public void setCollisionMap(CollisionMap collisionMap);

	public void setPlayer(Player player);

	public MusicPlayer getMusicPlayer();

	public void setMusicPlayer(MusicPlayer musicPlayer);
}
