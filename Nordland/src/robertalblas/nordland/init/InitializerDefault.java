package robertalblas.nordland.init;

import java.awt.Canvas;

import robertalblas.nordland.init.configuration.InitConfiguration;
import robertalblas.nordland.input.InputManager;
import robertalblas.nordland.input.swing.SwingInputManager;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowManager;
import robertalblas.nordland.window.swing.SwingScreen;
import robertalblas.nordland.window.swing.SwingWindow;
import robertalblas.nordland.window.swing.SwingWindowManager;

public class InitializerDefault extends InitializerTemplate {

	@Override
	public Window createWindow(InitConfiguration configuration, WindowManager windowManager) {
		SwingWindow window = (SwingWindow) windowManager.createWindow(configuration.getWindowTitle(), configuration.getWidth(),
				configuration.getHeight(), configuration.getScale());
		Canvas canvas = ((SwingScreen) window.getScreen()).getCanvas();
		canvas.requestFocus();
		return window;
	}

	@Override
	public WindowManager loadWindowManager(InitConfiguration configuration) {
		return new SwingWindowManager();
	}

	@Override
	public InputManager loadInputManager(InitConfiguration configuration) {
		return new SwingInputManager();
	}

}
