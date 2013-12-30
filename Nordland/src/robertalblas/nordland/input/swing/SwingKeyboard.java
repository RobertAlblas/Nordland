package robertalblas.nordland.input.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.input.InputDevice;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.window.swing.SwingScreen;

public class SwingKeyboard implements KeyListener, InputDevice {

	Set<InputAction> inputActions;
	boolean[] keys = new boolean[525];

	public SwingKeyboard() {
	}

	@Override
	public void update() {
		inputActions = new HashSet<InputAction>();
		
		for(int i = 0; i < keys.length; i++){
			if(keys[i]){
				inputActions.add(new InputAction(KeyEvent.getKeyText(i),1));
			}
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
		((SwingScreen) screen).getCanvas().addKeyListener(this);
	}

	@Override
	public List<InputAction> getInputActions() {
		return new ArrayList<InputAction>(inputActions);
	}

}
