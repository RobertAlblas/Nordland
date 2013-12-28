package robertalblas.nordland.window;

import java.util.List;

import robertalblas.nordland.input.InputAction;

public interface WindowManager {
	public Window createWindow(String title, int width, int height, int scale);
	public List<Window> getAllWindows();
	public void windowClosedEvent(Window window);
	public void addWindowListener(WindowListener windowListener);
	public void renderAllWindows(int framerate, int updaterate);
	public int getAmountOfWindows();
	public void unload();
	public void tick(List<InputAction> inputActions);
}
