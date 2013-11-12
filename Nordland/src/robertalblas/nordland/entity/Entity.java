package robertalblas.nordland.entity;

import robertalblas.nordland.window.Screen;

public interface Entity {
	public void update();
	public void render(Screen screen);
	public int getX();
	public int getY();
}
