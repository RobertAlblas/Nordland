package robertalblas.nordland.world.testworld;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.exception.CollisionException;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.util.log.Logger;
import robertalblas.nordland.util.log.LoggerManager;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.tile.Tile;

public class TestWorld implements World {

	private SpriteManager spriteManager;
	private CollisionMap collisionMap;
	private int width, height;

	private ArrayList<Tile> tiles;
	private List<Entity> entities;
	private Player player;

	public TestWorld(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
		this.width = 10 * ((Sprite) spriteManager.getResourceSet("tileset")
				.getResource("grass")).getWidth();
		this.height = 10 * ((Sprite) spriteManager.getResourceSet("tileset")
				.getResource("grass")).getHeight();
		this.tiles = new ArrayList<Tile>();
		this.entities = new ArrayList<Entity>();
		this.collisionMap = new CollisionMap(width, height);

		createLevel();
	}

	private void createLevel() {
		createTiles();
		createPlayer();
	}

	private void createTiles() {
		Sprite grassSprite = (Sprite) spriteManager.getResourceSet("tileset")
				.getResource("grass");

		int tileHeight = grassSprite.getHeight();
		int tileWidth = grassSprite.getWidth();

		for (int x = 0; x < this.width; x += tileWidth) {
			for (int y = 0; y < this.height; y += tileHeight) {
				tiles.add(Tile.createTile(grassSprite, x + tileWidth / 2, y
						+ tileHeight / 2));
			}
		}
		tiles.add(Tile.createSolidTile(collisionMap, (Sprite) spriteManager
				.getResourceSet("tileset").getResource("rock"), width / 2,
				height / 2));
	}

	private void createPlayer() {
		player = new Player(this,
				(SpriteSheet) spriteManager.getResourceSet("player"), 40, 40);
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
					LoggerManager.getInstance().getDefaultLogger()
							.log("Collision!", Logger.LOGTYPE_DEBUG);
				}
			}
		}
		spriteManager.update();
	}

	@Override
	public void render(Screen screen) {
		screen.centerAt(player.getX(), player.getY());
		for (Tile tile : tiles) {
			tile.render(screen);
		}
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

}
