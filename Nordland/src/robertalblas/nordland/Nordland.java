package robertalblas.nordland;

import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.UnknownInitConfigurationException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.init.Initializer;
import robertalblas.nordland.input.InputManager;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.resource.world.WorldResourceManager;
import robertalblas.nordland.system.defaults.SystemDefaults;
import robertalblas.nordland.system.log.Logger;
import robertalblas.nordland.system.log.LoggerManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowListener;
import robertalblas.nordland.window.WindowManager;
import robertalblas.nordland.world.World;

public class Nordland implements Runnable, WindowListener {

	private Thread thread;
	private World world;
	private boolean running = false;

	private InputManager inputManager;
	private WindowManager windowManager;
	private SpriteManager spriteManager;
	private SoundManager soundManager;
	private WorldResourceManager worldResourceManager;
	private TickTimerManager tickTimerManager;

	public Nordland() throws NumberFormatException, XMLParseException, UnknownEntityTypeException, ResourceNotFoundException, UnknownInitConfigurationException {
		LoggerManager.getInstance().getDefaultLogger().log("Loading engine..", Logger.LOGTYPE_DEBUG);
		long startTime = System.currentTimeMillis();

		Initializer.init(this);
		
		LoggerManager.getInstance().getDefaultLogger()
				.log("Done loading in " + (System.currentTimeMillis() - startTime) + "ms", Logger.LOGTYPE_DEBUG);
	}

	public void tick() {
		inputManager.tick();
		windowManager.tick(inputManager.getInputActions());
		tickTimerManager.tick();
		world.tick(inputManager.getInputActions());
	}

	public synchronized void start() {
		running = true;
		if(world.getMusicPlayer() != null){
			//world.getMusicPlayer().play();
		}
		thread = new Thread(this, "Display");
		LoggerManager.getInstance().getDefaultLogger().log("Starting", Logger.LOGTYPE_DEBUG);
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			LoggerManager.getInstance().getDefaultLogger().log("Shutting down!", Logger.LOGTYPE_DEBUG);
			world.getMusicPlayer().pause();
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
				tick();
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
	
	@Override
	public void onWindowClosed(Window window) {
		if (windowManager.getAmountOfWindows() == 0) {
			LoggerManager.getInstance().getDefaultLogger().log("Window closed", Logger.LOGTYPE_DEBUG);
			stop();
		}
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	public WindowManager getWindowManager() {
		return windowManager;
	}

	public void setWindowManager(WindowManager windowManager) {
		this.windowManager = windowManager;
	}

	public SpriteManager getSpriteManager() {
		return spriteManager;
	}

	public void setSpriteManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}

	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

	public WorldResourceManager getWorldResourceManager() {
		return worldResourceManager;
	}

	public void setWorldResourceManager(WorldResourceManager worldResourceManager) {
		this.worldResourceManager = worldResourceManager;
	}

	public TickTimerManager getTickTimerManager() {
		return tickTimerManager;
	}

	public void setTickTimerManager(TickTimerManager tickTimerManager) {
		this.tickTimerManager = tickTimerManager;
	}

	public static void main(String[] args) {
		Nordland nordland;
		try {
			nordland = new Nordland();
			nordland.start();
		} catch (NumberFormatException | XMLParseException | UnknownEntityTypeException | ResourceNotFoundException | UnknownInitConfigurationException e) {
			e.printStackTrace();
		}
	}
}
