package robertalblas.nordland.input.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.input.InputActionType;
import robertalblas.nordland.input.InputDevice;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.window.swing.SwingScreen;

public class SwingKeyboard implements KeyListener, InputDevice{
	
	Set<InputAction> inputActions;
	boolean[] keys = new boolean[525];
	
	public SwingKeyboard(){
		this.inputActions = new HashSet<InputAction>();
	}
	
	@Override
	public void update() {
		inputActions.clear();
		
		if(keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]){
			inputActions.add(new InputAction(InputActionType.UP ,1));
		}
		if(keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]){
			inputActions.add(new InputAction(InputActionType.DOWN ,1));
		}
		if(keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]){
			inputActions.add(new InputAction(InputActionType.LEFT ,1));
		}
		if(keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]){
			inputActions.add(new InputAction(InputActionType.RIGHT ,1));
		}
		if(keys[KeyEvent.VK_ESCAPE]){
			inputActions.add(new InputAction(InputActionType.QUIT ,1));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void connectScreen(Screen screen) {
		((SwingScreen)screen).getCanvas().addKeyListener(this);
	}

	@Override
	public List<InputAction> getInputActions() {
		return new ArrayList<InputAction>(inputActions);
	}

}
