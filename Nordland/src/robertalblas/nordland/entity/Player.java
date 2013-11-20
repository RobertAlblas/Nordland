package robertalblas.nordland.entity;

import java.util.List;

import robertalblas.nordland.Nordland;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.Animation;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.window.Screen;

public class Player implements Entity {

	private int x, y;
	private int width, height;
	private SpriteSheet spriteSheet;
	private String currentSprite;
	private boolean isMoving;
	private Direction direction;

	public Player(SpriteSheet spriteSheet, int x, int y) {
		this.x = x;
		this.y = y;
		this.spriteSheet = spriteSheet;
		Sprite firstSprite = (Sprite) spriteSheet.getResources().get(0);
		this.currentSprite = firstSprite.getName();
		this.width = firstSprite.getWidth();
		this.height = firstSprite.getHeight();
		this.isMoving = false;
		this.direction = Direction.NONE;
	}

	@Override
	public void update(List<InputAction> inputActions) {
		processInputActions(inputActions);
		updateSprite();
	}

	private void processInputActions(List<InputAction> inputActions) {
		isMoving = false;
		for (InputAction inputAction : inputActions) {
			String action = inputAction.getActionType();
			if (action.equals("up")) {
				this.y--;
				this.direction = Direction.NORTH;
				this.isMoving = true;
			}
			if (action.equals("down")) {
				this.y++;
				this.direction = Direction.SOUTH;
				this.isMoving = true;
			}
			if (action.equals("left")) {
				this.x--;
				this.direction = Direction.EAST;
				this.isMoving = true;
			}
			if (action.equals("right")) {
				this.x++;
				this.direction = Direction.WEST;
				this.isMoving = true;
			}
		}
	}

	private void updateSprite() {
		switch (this.direction) {
		case NORTH:
			this.currentSprite = "back";
			break;
		case SOUTH:
			this.currentSprite = "front";
			break;
		case EAST:
			this.currentSprite = "left";
			break;
		case WEST:
			this.currentSprite = "right";
			break;
		default:
			break;
		}

		if (isMoving) {
			this.currentSprite += "_moving";
		}
	}

	@Override
	public void render(Screen screen) {
		screen.renderFixedDrawable(x, y,
				(Drawable) spriteSheet.getResource(currentSprite));
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
