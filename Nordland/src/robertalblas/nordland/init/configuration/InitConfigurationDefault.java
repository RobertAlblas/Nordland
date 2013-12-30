package robertalblas.nordland.init.configuration;


public class InitConfigurationDefault implements InitConfiguration {
	
	private int width;
	private int height;
	private int scale;
	private String windowTitle;
	private String resource;
	private String worldSet;
	private String world;

	public InitConfigurationDefault() {
		
		this.width = 400;
		this.height = width / 16 * 9;
		this.scale = 3;
		this.windowTitle = "Nordland Alpha 0.3";
		this.setResource("res");
		this.setWorld("testworld");
		this.setWorldSet("testworld");
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	@Override
	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public String getWorldSet() {
		return worldSet;
	}

	public void setWorldSet(String worldSet) {
		this.worldSet = worldSet;
	}

	@Override
	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}
	
}
