package robertalblas.nordland.window.swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public class SwingScreen implements Screen {

	private int screenWidth, screenHeight;
	private int mask = 0xffffff;
	private int[] pixels;
	private int xOffset, yOffset;
	private World world;
	private BufferedImage image;
	private int[] rasterPixels;

	private Canvas canvas;

	public SwingScreen(int width, int height, int scale) {
		this.screenWidth = width;
		this.screenHeight = height;
		canvas = new Canvas();
		pixels = new int[width * height];
		setSize(width * scale, height * scale);
		image = new BufferedImage(screenWidth, screenHeight,
				BufferedImage.TYPE_INT_RGB);
		rasterPixels = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
	}

	public void clear() {
		for (int i = 0; i < pixels.length; ++i) {
			pixels[i] = 0x000000;
		}
	}

	public void setSize(int width, int height) {
		Dimension d = new Dimension(width, height);
		canvas.setPreferredSize(d);
		canvas.setMinimumSize(d);
		canvas.setMaximumSize(d);
	}

	public void render(int mouseX, int mouseY, int framerate, int updaterate) {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		clear();
		world.render(this, xOffset, yOffset);

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

	public void setWorld(World world) {
		this.world = world;
	}

	public void renderFixedSprite(int xPosition, int yPosition, Sprite sprite) {
		xPosition -= xOffset;
		yPosition -= yOffset;
		renderSprite(xPosition, yPosition, sprite);
	}

	public void renderSprite(int xPosition, int yPosition, Sprite sprite) {
		if (spriteIsOffScreen(xPosition, yPosition, sprite)) {
			return;
		}
		
		//Center the sprite
		xPosition = xPosition - sprite.getWidth() / 2;
		yPosition = yPosition - sprite.getHeight() / 2;

		for (int spriteY = 0; spriteY < sprite.getHeight(); ++spriteY) {
			int screenY = spriteY + yPosition;
			for (int spriteX = 0; spriteX < sprite.getWidth(); ++spriteX) {
				int screenX = spriteX + xPosition;
				if (screenX < -sprite.getWidth() || screenX >= screenWidth
						|| screenY < 0 || screenY >= screenHeight)
					break;
				if (screenX < 0)
					screenX = 0;
				int color = sprite.getPixels()[spriteX + spriteY
						* sprite.getWidth()];
				if (color != 0xffff00ff)
					pixels[screenX + screenY * (screenWidth)] = (int) ((sprite
							.getPixels()[spriteX + spriteY * sprite.getWidth()]) & mask);
			}
		}
	}

	private boolean spriteIsOffScreen(int xPosition, int yPosition,
			Sprite sprite) {
		return (xPosition > screenWidth || xPosition + sprite.getWidth() < 0
				|| yPosition > screenHeight || yPosition + sprite.getHeight() < 0);
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void centerAt(int x, int y){
		this.xOffset = x - screenWidth / 2;
		this.yOffset = y - screenHeight / 2;
	}

	public int getMask() {
		return mask;
	}

	public void setMask(int mask) {
		if (mask >= 0x000000 && mask <= 0xffffff) {
			this.mask = mask;
		}
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
