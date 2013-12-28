package robertalblas.nordland.input.swing;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.input.InputManager;
import robertalblas.nordland.window.Screen;

public class SwingInputManager implements InputManager {

	private SwingMouse mouse;
	private SwingKeyboard keyboard;
	
	public SwingInputManager(){
		super();
		this.mouse = new SwingMouse();
		this.keyboard = new SwingKeyboard();		
	}
	
	@Override
	public List<InputAction> getInputActions() {
		List<InputAction> inputActions = new ArrayList<InputAction>();
		
		inputActions.addAll(keyboard.getInputActions());
		inputActions.addAll(mouse.getInputActions());
		
		return inputActions;
	}

	@Override
	public void connectScreen(Screen screen) {
		keyboard.connectScreen(screen);
		mouse.connectScreen(screen);
	}

	@Override
	public void tick() {		
		mouse.update();
		keyboard.update();
	}

}
