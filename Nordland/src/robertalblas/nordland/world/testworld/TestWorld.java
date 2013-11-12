package robertalblas.nordland.world.testworld;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.tile.Tile;

public class TestWorld implements World{

	private SpriteManager spriteManager;
	private int width, height;
	
	private ArrayList<Tile> tiles;
	private List<Entity> entities;
	private Player player;
	
	public TestWorld(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
		this.width = 20 * ((Sprite)spriteManager.getResourceSet("tileset").getResource("grass")).getWidth();
		this.height = 20 * ((Sprite)spriteManager.getResourceSet("tileset").getResource("grass")).getHeight();
		this.tiles = new ArrayList<Tile>();
		this.entities = new ArrayList<Entity>();
		
		createLevel();
	}
	
	private void createLevel(){
		createTiles();
		createPlayer();
	}
	
	private void createTiles(){
		Sprite grassSprite = (Sprite)spriteManager.getResourceSet("tileset").getResource("grass");
		
		int tileHeight = grassSprite.getHeight();
		int tileWidth = grassSprite.getWidth();
		
		for(int x = 0; x < this.width; x++){
			for(int y = 0; y < this.height; y++){
				tiles.add(Tile.createTile(grassSprite, x * tileWidth + tileWidth / 2, y * tileHeight + tileHeight / 2));
			}
		}
	}
	
	private void createPlayer(){
		player = new Player((SpriteSheet)spriteManager.getResourceSet("player"),10,10);
	}

	@Override
	public void update(List<InputAction> inputActions) {
		for(Entity entity: entities){
			entity.update();
		}
	}

	@Override
	public void render(Screen screen) {
		for(Tile tile : tiles){
			tile.render(screen);
		}
		for(Entity entity : entities){
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

}
