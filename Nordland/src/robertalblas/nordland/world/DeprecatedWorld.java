package robertalblas.nordland.world;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.mob.Player;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.tile.DeprecatedTile;

public abstract class DeprecatedWorld {

	private int width, height;
	private int[] tiles;
	private List<Entity> entities = new ArrayList<Entity>();
	private SpriteManager spriteManager;
	
	private DeprecatedTile rockTile, iceTile, grassTile;

	private Player player;

	public DeprecatedWorld(int width, int height, SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
		this.setWidth(width);
		this.setHeight(height);
		setTiles(new int[width * height]);
		createTiles();
		generateLevel();
		addPlayer((SpriteSheet) spriteManager.getResourceSet("player"));
	}

	public void createTiles(){
		rockTile = new DeprecatedTile(
				(SpriteSheet)spriteManager.getResourceSet("tileset"),
				new String[]{"rocktile"},
				true);
		grassTile = new DeprecatedTile(
				(SpriteSheet)spriteManager.getResourceSet("tileset"),
				new String[]{"grasstile"},
				false);
		iceTile = new DeprecatedTile(
				(SpriteSheet)spriteManager.getResourceSet("tileset"),
				new String[]{"icetile"},
				false);
	}
	
	public DeprecatedWorld(String path, SpriteManager spriteManager) {
		loadWorld(path);
		this.spriteManager = spriteManager;
		createTiles();
		addPlayer((SpriteSheet) spriteManager.getResourceSet("player"));
	}

	private void addPlayer(SpriteSheet spritesheet) {
		player = new Player(spritesheet, 16 * getWidth() / 2, 16 * getHeight() / 2);
		player.init(this);
		entities.add(player);
	}

	protected void loadWorld(String path) {

	}

	protected void generateLevel() {

	}

	public boolean collisionTile(int x, int y, int size, int xOffset,
			int yOffset) {
		boolean solid = false;

		for (int corner = 0; corner < 4; ++corner) {
			int xt = (x - corner % 2 * size + xOffset) / 16;
			int yt = (y - corner / 2 * size + yOffset) / 16;
			if (getTile(xt, yt).isSolid())
				solid = true;
		}
		return solid;
	}

	public void update(List<InputAction> inputActions) {
		List<Entity> updateEntities = new ArrayList<Entity>();
		updateEntities.addAll(entities);

		for (Entity e : updateEntities) {
			if (e instanceof Player) {
				((Player) e).update(inputActions);
			} else {
				e.update();
			}
			if (e.isRemoved()) {
				entities.remove(e);
			}
		}
	}

	protected void time() {

	}

	public void render(Screen screen, int xScroll, int yScroll) {
		screen.centerAt(player.getX(), player.getY());

		int x0 = xScroll >> 4;
		int y0 = yScroll >> 4;
		int x1 = (xScroll + screen.getScreenWidth() + 16) >> 4;
		int y1 = (yScroll + screen.getScreenHeight() + 16) >> 4;

		for (int y = y0; y < y1; ++y) {
			for (int x = x0; x < x1; ++x) {
				getTile(x, y).render(x, y, screen);
			}
		}

		for (Entity e : entities) {
			e.render(screen);
		}
	}

	public DeprecatedTile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getWidth()) {
			return rockTile;
		}
		switch (getTiles()[x + y * getWidth()]) {
		case 0xff000000:
			return rockTile;
		case 0xffffffff:
			return iceTile;
		case 0xff00ff00:
			return grassTile;
		default:
			return rockTile;
		}
	}

	public void addEntity(Entity e) {
		e.init(this);
		entities.add(e);
	}
	
	public Player getPlayer(){
		return player;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getTiles() {
		return tiles;
	}

	public void setTiles(int[] tiles) {
		this.tiles = tiles;
	}
}
