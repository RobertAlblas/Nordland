package robertalblas.nordland.input;

import java.util.List;

import robertalblas.nordland.window.Screen;

public interface InputDevice {
	
	public void connectScreen(Screen screen);
	public List<InputAction> getInputActions();
	public void update();
}
