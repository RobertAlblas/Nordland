package robertalblas.nordland.window;

public interface Window {
	public void setTitle(String title);
	public void hideCursor();
	public Screen getScreen();
	public void setScreen(Screen screen);
	public void dispose();
}
