package robertalblas.nordland.input.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.input.InputActionType;
import robertalblas.nordland.input.InputDevice;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.window.swing.SwingScreen;

public class SwingMouse implements MouseListener, MouseMotionListener, InputDevice{

	private int mouseX = -1;
	private int mouseY = -1;
	private int mouseB = -1;
	
	private List<InputAction> inputActions;
	
	public SwingMouse(){
		inputActions = new ArrayList<InputAction>();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
		
	}

	@Override
	public void connectScreen(Screen screen) {
		((SwingScreen)screen).getCanvas().addMouseListener(this);
		((SwingScreen)screen).getCanvas().addMouseMotionListener(this);
	}

	@Override
	public List<InputAction> getInputActions() {
		return inputActions;
	}

	@Override
	public void update() {
		inputActions.clear();
		inputActions.add(new InputAction(InputActionType.MOUSE_X,mouseX));
		inputActions.add(new InputAction(InputActionType.MOUSE_Y,mouseY));
		inputActions.add(new InputAction(InputActionType.MOUSE_BUTTON,mouseB));
	}

}
