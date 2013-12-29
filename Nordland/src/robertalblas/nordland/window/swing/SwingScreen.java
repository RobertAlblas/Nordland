package robertalblas.nordland.window.swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import robertalblas.nordland.renderer.Renderer;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public class SwingScreen implements Screen {
	
	private World world;
	private BufferedImage image;
	private int[] rasterPixels;

	private Canvas canvas;
	private Renderer renderer;

	public SwingScreen(int width, int height, int scale) {
		this.renderer = new Renderer(width, height);
		canvas = new Canvas();
		setSize(width * scale, height * scale);
		image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		rasterPixels = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
	}

	@Override
	public void setSize(int width, int height) {
		Dimension d = new Dimension(width, height);
		canvas.setPreferredSize(d);
		canvas.setMinimumSize(d);
		canvas.setMaximumSize(d);
	}

	@Override
	public void render(int mouseX, int mouseY, int framerate, int updaterate) {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		renderer.clear();
		world.render(renderer);

		int[] pixels = renderer.getPixels();
		for (int i = 0; i < pixels.length; i++) {
			rasterPixels[i] = pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);

		g.setColor(Color.BLUE);
		g.setFont(new Font("Verdana", 1, 15));
		g.drawString("FPS: " + framerate, 20, 20);
		g.drawString("UPS: " + updaterate, 20, 40);

		g.setColor(Color.RED);
		drawCrosshair(g, mouseX, mouseY);

		g.dispose();
		bs.show();
	}

	private void drawCrosshair(Graphics g, int x, int y) {
		g.drawLine(x, y - 10, x, y + 10);
		g.drawLine(x - 1, y - 10, x - 1, y + 10);
		g.drawLine(x + 1, y - 10, x + 1, y + 10);

		g.drawLine(x - 10, y, x + 10, y);
		g.drawLine(x - 10, y - 1, x + 10, y - 1);
		g.drawLine(x - 10, y + 1, x + 10, y + 1);
	}

	@Override
	public void setWorld(World world) {
		this.world = world;
	}	
	
	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public Renderer getRenderer() {
		return renderer;
	}

	@Override
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

}
