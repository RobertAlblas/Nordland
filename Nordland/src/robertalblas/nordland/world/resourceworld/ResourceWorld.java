package robertalblas.nordland.world.resourceworld;

import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.exception.CollisionException;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.system.log.Logger;
import robertalblas.nordland.system.log.LoggerManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public class ResourceWorld implements World {
	
	private SoundManager soundManager;
	private SpriteManager spriteManager;
	private TickTimerManager tickTimerManager;
	private List<Entity> entities;
	private int width, height;
	private CollisionMap collisionMap;
	
	private Player player;

	@Override
	public void update(List<InputAction> inputActions) {
		collisionMap.clear();
		for (Entity entity : entities) {
			entity.update(inputActions);
			if (entity instanceof Collidable) {
				try {
					collisionMap.renderCollidable((Collidable) entity);
				} catch (CollisionException e) {
					LoggerManager.getInstance().getDefaultLogger().log("Collision: " + entity + " and " + e.getCollidable(), Logger.LOGTYPE_DEBUG);
				}
			}
		}
	}

	@Override
	public void render(Screen screen) {
		screen.centerAt(player.getX(), player.getY());
		for (Entity entity : entities) {
			entity.render(screen);
		}
	}

	@Override
	public void addEntity(Entity e) {
		entities.add(e);
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public CollisionMap getCollisionMap() {
		return collisionMap;
	}

	@Override
	public TickTimerManager getTickTimerManager() {
		return tickTimerManager;
	}

	@Override
	public SpriteManager getSpriteManager() {
		return spriteManager;
	}

	@Override
	public SoundManager getSoundManager() {
		return soundManager;
	}

	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

	public void setSpriteManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
	}

	public void setTickTimerManager(TickTimerManager tickTimerManager) {
		this.tickTimerManager = tickTimerManager;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setCollisionMap(CollisionMap collisionMap) {
		this.collisionMap = collisionMap;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
