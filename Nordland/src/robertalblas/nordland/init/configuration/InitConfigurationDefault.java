package robertalblas.nordland.init.configuration;

import robertalblas.nordland.entity.loader.AnimatedCollidableTerrainFactory;
import robertalblas.nordland.entity.loader.EntityLoader;
import robertalblas.nordland.entity.loader.PlayerFactory;
import robertalblas.nordland.entity.loader.StaticEntityFactory;
import robertalblas.nordland.entity.loader.TerrainFactory;

public class InitConfigurationDefault extends InitConfiguration {
	
	private int width;
	private int height;
	private int scale;
	private String windowTitle;
	private String resource;
	private String worldSet;
	private String world;

	public InitConfigurationDefault() {
		super("default");
		
		this.width = 400;
		this.height = width / 16 * 9;
		this.scale = 3;
		this.windowTitle = "Nordland Alpha 0.3";
		this.setResource("res");
		this.setWorld("testworld");
		this.setWorldSet("testworld");
		
		EntityLoader.addFactory("player", new PlayerFactory());
		EntityLoader.addFactory("animatedCollidableTerrain", new AnimatedCollidableTerrainFactory());
		EntityLoader.addFactory("terrain", new TerrainFactory());
		EntityLoader.addFactory("staticEntity", new StaticEntityFactory());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getWorldSet() {
		return worldSet;
	}

	public void setWorldSet(String worldSet) {
		this.worldSet = worldSet;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}
	
}
