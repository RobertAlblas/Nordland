package robertalblas.nordland.init;

import robertalblas.nordland.Nordland;
import robertalblas.nordland.entity.loader.EntityLoader;
import robertalblas.nordland.entity.loader.PlayerFactory;
import robertalblas.nordland.entity.loader.TerrainFactory;
import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.UnknownInitConfigurationException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.init.configuration.InitConfiguration;
import robertalblas.nordland.init.configuration.InitConfigurationDefault;
import robertalblas.nordland.input.InputManager;
import robertalblas.nordland.resource.ResourceLoader;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.resource.world.WorldResource;
import robertalblas.nordland.resource.world.WorldResourceManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowManager;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.WorldFactory;
import robertalblas.nordland.world.resourceworld.ResourceWorldFactory;

public abstract class InitializerTemplate {

	public void init(Nordland nordland) throws NumberFormatException, XMLParseException, UnknownEntityTypeException,
			ResourceNotFoundException, UnknownInitConfigurationException {
		init(nordland, new InitConfigurationDefault());
	}

	public void init(Nordland nordland, InitConfiguration configuration) throws UnknownInitConfigurationException,
			NumberFormatException, XMLParseException, UnknownEntityTypeException, ResourceNotFoundException {
		addEntityFactories(configuration);
		WorldFactory worldFactory = loadResources(nordland, configuration);
		
		WindowManager windowManager = loadWindowManager(configuration);
		windowManager.addWindowListener(nordland);
		Window window = createWindow(configuration, windowManager);
		
		InputManager inputManager = loadInputManager(configuration);
		inputManager.connectScreen(window.getScreen());
		
		World world = worldFactory.createWorld();
		window.getScreen().setWorld(world);
		window.hideCursor();
		
		nordland.setWorld(world);		
		nordland.setWindowManager(windowManager);
		nordland.setInputManager(inputManager);
	}

	public abstract InputManager loadInputManager(InitConfiguration configuration);
	public abstract Window createWindow(InitConfiguration configuration, WindowManager windowManager);
	public abstract WindowManager loadWindowManager(InitConfiguration configuration);

	public void addEntityFactories(InitConfiguration configuration) {
		EntityLoader.addFactory("player", new PlayerFactory());
		EntityLoader.addFactory("terrain", new TerrainFactory());
	}

	public WorldFactory loadResources(Nordland nordland, InitConfiguration configuration) throws NumberFormatException, XMLParseException,
			UnknownEntityTypeException, ResourceNotFoundException {
		TickTimerManager tickTimerManager = new TickTimerManager();
		SpriteManager spriteManager = new SpriteManager();
		spriteManager.setTickTimerManager(tickTimerManager);
		SoundManager soundManager = new SoundManager();
		WorldResourceManager worldResourceManager = new WorldResourceManager(tickTimerManager, soundManager, spriteManager);

		ResourceLoader resourceLoader = new ResourceLoader();
		resourceLoader.addResourceManager(spriteManager);
		resourceLoader.addResourceManager(soundManager);
		resourceLoader.addResourceManager(worldResourceManager);
		resourceLoader.loadResources(configuration.getResource());
		
		
		nordland.setTickTimerManager(tickTimerManager);
		nordland.setSpriteManager(spriteManager);
		nordland.setSoundManager(soundManager);
		nordland.setWorldResourceManager(worldResourceManager);
		
		return new ResourceWorldFactory((WorldResource) worldResourceManager.getResourceSet(configuration.getWorldSet())
				.getResource(configuration.getWorld()));
	}

}
