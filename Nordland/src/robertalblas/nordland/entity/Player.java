package robertalblas.nordland.entity;

import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.resource.sound.SoundSet;
import robertalblas.nordland.world.World;

public class Player extends BaseEntity implements Collidable {

	private boolean isMoving;
	private Direction direction;

	public Player(World world, SpriteSet spriteSheet, SoundSet soundSet, int x, int y) {
		super(world, spriteSheet, soundSet, x, y);
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
			if (action.equalsIgnoreCase("w")) {
				this.direction = Direction.NORTH;
				isMoving = true;
				break;
			}
			if (action.equalsIgnoreCase("s")) {
				this.direction = Direction.SOUTH;
				isMoving = true;
				break;
			}
			if (action.equalsIgnoreCase("d")) {
				this.direction = Direction.EAST;
				isMoving = true;
				break;
			}
			if (action.equalsIgnoreCase("a")) {
				this.direction = Direction.WEST;
				isMoving = true;
				break;
			}
		}

		if(direction != Direction.NONE && isMoving){
			super.move(direction);
			isMoving = true;
		}
		
	}

	private void updateSprite() {
		switch (this.direction) {
		case NORTH:
			this.setCurrentDrawable("back");
			break;
		case SOUTH:
			this.setCurrentDrawable("front");
			break;
		case EAST:
			this.setCurrentDrawable("right");
			break;
		case WEST:
			this.setCurrentDrawable("left");
			break;
		default:
			break;
		}

		if (isMoving) {
			this.setCurrentSpriteMoving();
		}
	}
	
	public Direction getDirection(){
		return direction;
	}

	@Override
	public void onCollision(Entity e) {

	}
}
