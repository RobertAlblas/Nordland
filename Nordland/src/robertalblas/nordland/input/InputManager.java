package robertalblas.nordland.input;

import java.util.List;

import robertalblas.nordland.window.Screen;

public interface InputManager {

	public List<InputAction> getInputActions();
	public void connectScreen(Screen screen);
	public void tick();
}
