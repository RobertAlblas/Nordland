package robertalblas.nordland;

import java.awt.Canvas;

import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.input.InputManager;
import robertalblas.nordland.input.swing.SwingInputManager;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.resource.sound.SoundSet;
import robertalblas.nordland.resource.sound.music.MusicPlayer;
import robertalblas.nordland.resource.sound.music.MusicPlayerImpl;
import robertalblas.nordland.resource.world.WorldResource;
import robertalblas.nordland.resource.world.WorldResourceManager;
import robertalblas.nordland.system.defaults.SystemDefaults;
import robertalblas.nordland.system.log.Logger;
import robertalblas.nordland.system.log.LoggerManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowListener;
import robertalblas.nordland.window.WindowManager;
import robertalblas.nordland.window.swing.SwingScreen;
import robertalblas.nordland.window.swing.SwingWindowManager;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.WorldFactory;
import robertalblas.nordland.world.resourceworld.ResourceWorldFactory;

public class Nordland implements Runnable, WindowListener {

	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 3;

	private Thread thread;
	private World world;
	private boolean running = false;

	private InputManager inputManager;
	private WindowManager windowManager;
	private SpriteManager spriteManager;
	private SoundManager soundManager;
	private WorldResourceManager worldResourceManager;
	private WorldFactory worldFactory;
	private TickTimerManager tickTimerManager;

	public Nordland() {
		LoggerManager.getInstance().getDefaultLogger().log("Loading engine..", Logger.LOGTYPE_DEBUG);

		tickTimerManager = new TickTimerManager();
		inputManager = new SwingInputManager();
		windowManager = new SwingWindowManager();
		spriteManager = new SpriteManager();
		spriteManager.setTickTimerManager(tickTimerManager);
		spriteManager.loadResourceSet("player");
		spriteManager.loadResourceSet("ice");
		spriteManager.loadResourceSet("grass");
		spriteManager.loadResourceSet("rock");

		soundManager = new SoundManager();
		soundManager.loadResourceSet("music");
		
		worldResourceManager = new WorldResourceManager(tickTimerManager, soundManager, spriteManager);
		try {
			worldResourceManager.loadResourceSet("testworld");
		} catch (XMLParseException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownEntityTypeException e) {
			e.printStackTrace();
		}

		MusicPlayer musicPlayer = new MusicPlayerImpl();
		musicPlayer.appendQueue((SoundSet) soundManager.getResourceSet("music"));
		musicPlayer.repeatAll();
		musicPlayer.play();

		worldFactory = new ResourceWorldFactory((WorldResource)worldResourceManager.getResourceSet("testworld").getResource("testworld"));

		windowManager.addWindowListener(this);
		Window window = windowManager.createWindow("Nordland 0.2", WIDTH, HEIGHT, SCALE);

		inputManager.connectScreen(window.getScreen());
		Canvas canvas = ((SwingScreen) window.getScreen()).getCanvas();

		canvas.requestFocus();
		try {
			world = worldFactory.createWorld();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		window.getScreen().setWorld(world);
		window.hideCursor();
		LoggerManager.getInstance().getDefaultLogger().log("Done", Logger.LOGTYPE_DEBUG);
	}

	public void update() {
		inputManager.update();
		windowManager.update(inputManager.getInputActions());
		tickTimerManager.tick();
		world.update(inputManager.getInputActions());
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		LoggerManager.getInstance().getDefaultLogger().log("Starting", Logger.LOGTYPE_DEBUG);
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			LoggerManager.getInstance().getDefaultLogger().log("Shutting down", Logger.LOGTYPE_DEBUG);
			thread.join();
			windowManager.unload();

			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		final double requiredDelta = (60.0 / SystemDefaults.TICKS_PER_SECOND_NORMAL);
		double delta = 0;
		int frames = 0;
		int framerate = 0;
		int updates = 0;
		int updaterate = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= requiredDelta) {
				update();
				delta -= requiredDelta;
				updates++;
			}
			frames++;

			windowManager.renderAllWindows(framerate, updaterate);

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				framerate = frames;
				frames = 0;
				updaterate = updates;
				updates = 0;
			}
		}
	}

	public static void main(String[] args) {
		Nordland nordland = new Nordland();
		nordland.start();

	}

	@Override
	public void onWindowClosed(Window window) {
		if (windowManager.getAmountOfWindows() == 0) {
			stop();
		}
	}
}
