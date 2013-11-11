package robertalblas.nordland.window.swing;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import robertalblas.nordland.window.Screen;
import robertalblas.nordland.window.Window;
import robertalblas.nordland.window.WindowManager;

public class SwingWindow implements Window, java.awt.event.WindowListener{

	private JFrame frame;
	private SwingScreen swingScreen;
	private WindowManager windowManager;

	public SwingWindow(WindowManager windowManager) {
		this.windowManager = windowManager;
		frame = new JFrame();
		frame.setResizable(false);
		frame.pack();
		frame.addWindowListener(this);
		frame.setVisible(true);
	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);
	}

	@Override
	public void hideCursor() {
		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		frame.getContentPane().setCursor(blankCursor);
	}

	@Override
	public Screen getScreen() {
		return swingScreen;
	}

	@Override
	public void setScreen(Screen screen) {
		this.swingScreen = (SwingScreen)screen;
		frame.add(swingScreen.getCanvas());
		frame.pack();		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		windowManager.windowClosedEvent(this);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void dispose() {
		frame.dispose();
	}

}
