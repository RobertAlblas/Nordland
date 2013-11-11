package robertalblas.nordland;

import java.awt.Canvas;

import robertalblas.nordland.input.InputManager;
import robertalblas.nordland.input.swing.SwingInputManager;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.util.log.Logger;
import robertalblas.nordland.util.log.LoggerManager;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowListener;
import robertalblas.nordland.window.WindowManager;
import robertalblas.nordland.window.swing.SwingScreen;
import robertalblas.nordland.window.swing.SwingWindowManager;
import robertalblas.nordland.world.SpawnWorld;
import robertalblas.nordland.world.World;

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

	public Nordland() {
		LoggerManager.getInstance().getDefaultLogger().log("Loading engine..", Logger.LOGTYPE_DEBUG);
		
		inputManager = new SwingInputManager();
		windowManager = new SwingWindowManager();
		spriteManager = new SpriteManager();
			spriteManager.loadResourceSet("player");
			spriteManager.loadResourceSet("tileset");
		
		windowManager.addWindowListener(this);
		Window window = windowManager.createWindow("Nordland 0.2", WIDTH, HEIGHT, SCALE);
		
		
		inputManager.connectScreen(window.getScreen());
		Canvas canvas = ((SwingScreen)window.getScreen()).getCanvas();
		
		canvas.requestFocus();
		world = new SpawnWorld("/world/spawnlevel.png", spriteManager);
		window.getScreen().setWorld(world);
		window.hideCursor();
		LoggerManager.getInstance().getDefaultLogger().log("Done", Logger.LOGTYPE_DEBUG);
	}

	public void update() {
		inputManager.update();
		windowManager.update(inputManager.getInputActions());
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
		double delta = 0;
		int frames = 0;
		int framerate = 0;
		int updates = 0;
		int updaterate = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				--delta;
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
				
				if(updaterate < 60){
					LoggerManager.getInstance().getDefaultLogger().log(
							"Updaterate dropped below 60 UPS: " + updaterate + " UPS",
							Logger.LOGTYPE_WARNING);
				}
			}
		}
	}

	public static void main(String[] args) {
		Nordland nordland = new Nordland();
		nordland.start();
	}


	@Override
	public void onWindowClosed(Window window) {
		if(windowManager.getAmountOfWindows() == 0){
			stop();
		}
	}
}
