package robertalblas.nordland.init;

import java.awt.Canvas;

import robertalblas.nordland.Nordland;
import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.init.configuration.InitConfiguration;
import robertalblas.nordland.init.configuration.InitConfigurationDefault;
import robertalblas.nordland.input.InputManager;
import robertalblas.nordland.input.swing.SwingInputManager;
import robertalblas.nordland.resource.ResourceLoader;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.resource.world.WorldResource;
import robertalblas.nordland.resource.world.WorldResourceManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowManager;
import robertalblas.nordland.window.swing.SwingScreen;
import robertalblas.nordland.window.swing.SwingWindowManager;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.WorldFactory;
import robertalblas.nordland.world.resourceworld.ResourceWorldFactory;

public class InitDefault implements InitStrategy {

	public  void init(Nordland nordland, InitConfiguration configuration) throws NumberFormatException, XMLParseException, UnknownEntityTypeException, ResourceNotFoundException{
		InitConfigurationDefault config = (InitConfigurationDefault) configuration;
		
		TickTimerManager tickTimerManager = new TickTimerManager();
		InputManager inputManager = new SwingInputManager();
		WindowManager windowManager = new SwingWindowManager();
		SpriteManager spriteManager = new SpriteManager();
		spriteManager.setTickTimerManager(tickTimerManager);
		SoundManager soundManager = new SoundManager();
		WorldResourceManager worldResourceManager = new WorldResourceManager(tickTimerManager, soundManager, spriteManager);

		ResourceLoader resourceLoader = new ResourceLoader();
		resourceLoader.addResourceManager(spriteManager);
		resourceLoader.addResourceManager(soundManager);
		resourceLoader.addResourceManager(worldResourceManager);
		resourceLoader.loadResources(config.getResource());

		WorldFactory worldFactory = new ResourceWorldFactory((WorldResource) worldResourceManager.getResourceSet(config.getWorldSet()).getResource(
				config.getWorld()));

		windowManager.addWindowListener(nordland);
		
		Window window = windowManager.createWindow(config.getWindowTitle(), config.getWidth(), config.getHeight(), config.getScale());

		inputManager.connectScreen(window.getScreen());
		Canvas canvas = ((SwingScreen) window.getScreen()).getCanvas();

		canvas.requestFocus();
		World world = worldFactory.createWorld();
		window.getScreen().setWorld(world);
		window.hideCursor();
		
		nordland.setInputManager(inputManager);
		nordland.setTickTimerManager(tickTimerManager);
		nordland.setWindowManager(windowManager);
		nordland.setSpriteManager(spriteManager);
		nordland.setSoundManager(soundManager);
		nordland.setWorldResourceManager(worldResourceManager);
		nordland.setWorld(world);
	}
}
