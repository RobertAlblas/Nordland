package robertalblas.nordland.world.testworld;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.entity.StaticEntity;
import robertalblas.nordland.entity.TerrainEntity;
import robertalblas.nordland.exception.CollisionException;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.system.log.Logger;
import robertalblas.nordland.system.log.LoggerManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public class TestWorld implements World {

	private SpriteManager spriteManager;
	private TickTimerManager tickTimerManager;
	private CollisionMap collisionMap;
	private int width, height;

	private List<Entity> entities;
	private Player player;

	public TestWorld(SpriteManager spriteManager, TickTimerManager tickTimerManager) {
		this.tickTimerManager = tickTimerManager;
		this.spriteManager = spriteManager;
		this.width = 500;
		this.height = 500;
		this.entities = new ArrayList<Entity>();
		this.collisionMap = new CollisionMap(width, height);

		createLevel();
	}

	private void createLevel() {
		createTerrain();
		createPlayer();
	}

	private void createTerrain() {
		Sprite grassSprite = (Sprite) spriteManager.getResourceSet("tileset").getResource("grass");

		int tileHeight = grassSprite.getHeight();
		int tileWidth = grassSprite.getWidth();

		for (int x = 0; x < this.width; x += tileWidth) {
			for (int y = 0; y < this.height; y += tileHeight) {
				TerrainEntity grass = new TerrainEntity(this, (SpriteSet) spriteManager.getResourceSet("tileset"), x + tileWidth / 2, y + tileHeight / 2);
				grass.setCurrentSprite("grass");
				entities.add(grass);
			}
		}
		StaticEntity rock = new StaticEntity(this, (SpriteSet) spriteManager.getResourceSet("tileset"), width / 2, height / 2);
		rock.setCurrentSprite("rock");
		addEntity(rock);
	}

	private void createPlayer() {
		player = new Player(this, (SpriteSet) spriteManager.getResourceSet("player"), 40, 40);
		addEntity(player);
	}

	@Override
	public void update(List<InputAction> inputActions) {
		collisionMap.clear();
		for (Entity entity : entities) {
			entity.update(inputActions);
			if (entity instanceof Collidable) {
				try {
					collisionMap.renderCollidable((Collidable) entity);
				} catch (CollisionException e) {
					LoggerManager.getInstance().getDefaultLogger().log("Collision!", Logger.LOGTYPE_DEBUG);
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
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
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
	public CollisionMap getCollisionMap() {
		return this.collisionMap;
	}

	@Override
	public TickTimerManager getTickTimerManager() {
		return tickTimerManager;
	}

}
