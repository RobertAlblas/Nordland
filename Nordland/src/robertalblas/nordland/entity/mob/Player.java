package robertalblas.nordland.entity.mob;

import java.util.List;

import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;

public class Player extends Mob {

	public Sprite sprite;
	private int animationTick = 0;
	private boolean walking = false;


	public Player(SpriteSheet spriteSheet, int x, int y) {
		super(spriteSheet);
		this.sprite = (Sprite) spriteSheet.getResource("front");
		this.x = x;
		this.y = y;
	}

	@Override
	public void update() {
		if (animationTick < Integer.MAX_VALUE) {
			++animationTick;
		} else {
			animationTick = 0;
		}
	}

	@Override
	public boolean collision(double xa, double ya) {
		boolean solid = false;

		for (int corner = 0; corner < 4; ++corner) {
			int xt = ((x + (int) xa) + corner % 2 * 12 - 7) / 16;
			int yt = ((y + (int) ya) + corner / 2 * 12 + 3) / 16;
			if (world.getTile(xt, yt).isSolid())
				solid = true;
		}
		return solid;
	}

	@Override
	public void render(Screen screen) {
		int xx = x - 16;
		int yy = y - 16;

		if (dir == 0) {
			if (walking) {
				if (animationTick % 20 > 10) {
					sprite = (Sprite) spriteSheet.getResource("back_walking_1");
				} else {
					sprite = (Sprite) spriteSheet.getResource("back_walking_2");
				}
			} else {
				sprite = (Sprite) spriteSheet.getResource("back");
			}
		}
		if (dir == 1) {
			if (walking) {
				if (animationTick % 20 > 10) {
					sprite = (Sprite) spriteSheet
							.getResource("right_walking_1");
				} else {
					sprite = (Sprite) spriteSheet
							.getResource("right_walking_2");
				}
			} else {
				sprite = (Sprite) spriteSheet.getResource("right");
			}
		}
		if (dir == 2) {
			if (walking) {
				if (animationTick % 20 > 10) {
					sprite = (Sprite) spriteSheet
							.getResource("front_walking_1");
				} else {
					sprite = (Sprite) spriteSheet
							.getResource("front_walking_2");
				}
			} else {
				sprite = (Sprite) spriteSheet.getResource("front");
			}
		}

		if (dir == 3) {
			if (walking) {
				if (animationTick % 20 > 10) {
					sprite = (Sprite) spriteSheet.getResource("left_walking_1");
				} else {
					sprite = (Sprite) spriteSheet.getResource("left_walking_2");
				}
			} else {
				sprite = (Sprite) spriteSheet.getResource("left");
			}
		}

		screen.renderSprite(xx, yy, sprite, true);

	}

	public void update(List<InputAction> inputActions) {
		int xa = 0, ya = 0;

		for (InputAction ia : inputActions) {
			String type = ia.getActionType();
			if (type.equals("up")) {
				--ya;
			} else if (type.equals("down")) {
				++ya;
			} else if (type.equals("left")) {
				--xa;
			} else if (type.equals("right")) {
				++xa;
			}
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		update();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
