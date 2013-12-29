package robertalblas.nordland.renderer;

import robertalblas.nordland.exception.InvalidRenderingMaskException;
import robertalblas.nordland.resource.graphics.Drawable;

public class Renderer {
	private int darkMask = 0xffffff;
	private int lightMask = 0x000000;
	private int[] pixels;
	private int width, height;
	private int xOffset, yOffset;
	
	public Renderer(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; ++i) {
			pixels[i] = 0x000000;
		}
	}

	public void renderFixedDrawable(int xPosition, int yPosition, Drawable drawable) {
		xPosition -= xOffset;
		yPosition -= yOffset;
		renderDrawable(xPosition, yPosition, drawable);
	}

	public void renderDrawable(int xPosition, int yPosition, Drawable drawable) {
		// Center the drawable
		xPosition = xPosition - drawable.getWidth() / 2;
		yPosition = yPosition - drawable.getHeight() / 2;

		if (drawableIsOffScreen(xPosition, yPosition, drawable)) {
			return;
		}

		for (int spriteY = 0; spriteY < drawable.getHeight(); ++spriteY) {
			int screenY = spriteY + yPosition;
			for (int spriteX = 0; spriteX < drawable.getWidth(); ++spriteX) {
				int screenX = spriteX + xPosition;
				if (screenX < -drawable.getWidth() || screenX >= width || screenY < 0 || screenY >= height)
					break;
				if (screenX < 0)
					screenX = 0;
				int color = drawable.getPixels()[spriteX + spriteY * drawable.getWidth()];
				if (color != 0xffff00ff)
					pixels[screenX + screenY * (width)] = (int) ((drawable.getPixels()[spriteX + spriteY
							* drawable.getWidth()])
							& darkMask | lightMask);
			}
		}
	}

	private boolean drawableIsOffScreen(int xPosition, int yPosition, Drawable drawable) {
		return (xPosition > width || xPosition + drawable.getWidth() < 0 || yPosition > height || yPosition
				+ drawable.getHeight() < 0);
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void centerAt(int x, int y) {
		this.xOffset = x - width / 2;
		this.yOffset = y - height / 2;
	}

	public int getMask() {
		return darkMask;
	}

	public void setDarkMask(int mask) {
		if (mask >= 0x000000 && mask <= 0xffffff) {
			this.darkMask = mask;
		} else {
			throw new InvalidRenderingMaskException("Mask must a valid value between 0x000000 and 0xffffff");
		}
	}

	public void setLightMask(int mask) {
		if (mask >= 0x000000 && mask <= 0xffffff) {
			this.lightMask = mask;
		} else {
			throw new InvalidRenderingMaskException("Mask must a valid value between 0x000000 and 0xffffff");
		}
	}
	
	public int getScreenWidth() {
		return width;
	}

	public int getScreenHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}
}
