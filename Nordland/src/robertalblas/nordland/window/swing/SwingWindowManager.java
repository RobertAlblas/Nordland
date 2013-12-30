package robertalblas.nordland.window.swing;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.input.MouseInputActionType;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowListener;
import robertalblas.nordland.window.WindowManager;

public class SwingWindowManager implements WindowManager{

	private List<WindowListener> windowListeners;
	private List<SwingWindow> swingWindows;
	
	private int mouseX = 0;
	private int mouseY = 0;
	
	public SwingWindowManager(){
		this.swingWindows = new ArrayList<SwingWindow>();
		this.windowListeners = new ArrayList<WindowListener>();
	}
	
	@Override
	public Window createWindow(String title, int width, int height, int scale) {
		Window window = new SwingWindow(this);
		window.setTitle(title);
		
		SwingScreen swingScreen = new SwingScreen(width, height, scale);
		window.setScreen(swingScreen);
		swingWindows.add((SwingWindow)window);
		return window;
	}

	@Override
	public List<Window> getAllWindows() {
		List<Window> windows = new ArrayList<Window>();
		for(SwingWindow w: swingWindows){
			windows.add((Window)w);
		}
		return windows;
	}

	@Override
	public void windowClosedEvent(Window window) {
		swingWindows.remove(window);
		for(WindowListener wl : windowListeners){
			wl.onWindowClosed(window);
		}
	}

	@Override
	public void addWindowListener(WindowListener windowListener) {
		this.windowListeners.add(windowListener);
	}

	@Override
	public void renderAllWindows(int framerate, int updaterate) {
		for(int i = 0; i  < swingWindows.size(); ++i){
			SwingWindow w = swingWindows.get(i);
			if(w != null){
				w.getScreen().render(mouseX, mouseY, framerate, updaterate);
			}
		}
	}
	
	public int getAmountOfWindows(){
		return swingWindows.size();
	}

	@Override
	public void unload() {
		for(int i = 0; i < swingWindows.size(); i++){
			Window w = swingWindows.get(i);
			w.dispose();
			swingWindows.remove(i);
			for(WindowListener wl : windowListeners){
				wl.onWindowClosed(w);
			}
			i--;
		}
	}

	@Override
	public void tick(List<InputAction> inputActions) {
		for(InputAction ia : inputActions){
			if(ia.getActionType().equals(MouseInputActionType.MOUSE_X)){
				mouseX = ia.getValue();
			}
			else if(ia.getActionType().equals(MouseInputActionType.MOUSE_Y)){
				mouseY = ia.getValue();
			}
			if(ia.getActionType().equals("ESC")){
				unload();
			}
		}
	}

}
