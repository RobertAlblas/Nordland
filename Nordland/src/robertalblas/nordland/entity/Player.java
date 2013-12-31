package robertalblas.nordland.entity;

import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.exception.CollisionException;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.world.World;

public class Player extends BaseEntity implements Collidable{
	
	private boolean isMoving;
	private Direction direction;

	public Player(World world, SpriteSet spriteSheet, int x, int y) {
		super(world,spriteSheet,x,y);
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
		
		int xDelta = 0;
		int yDelta = 0;
		
		for (InputAction inputAction : inputActions) {
			String action = inputAction.getActionType();
			if (action.equalsIgnoreCase("w")) {
				yDelta--;
				this.direction = Direction.NORTH;
				break;
			}
			if (action.equalsIgnoreCase("s")) {
				yDelta++;
				this.direction = Direction.SOUTH;
				break;
			}
			if (action.equalsIgnoreCase("d")) {
				xDelta++;
				this.direction = Direction.EAST;
				break;
			}
			if (action.equalsIgnoreCase("a")) {
				xDelta--;
				this.direction = Direction.WEST;
				break;
			}
		}
		
		if (xDelta != 0 || yDelta != 0) {
			Collidable collidable;
			this.isMoving = true;
			try {
				collidable = getWorld().getCollisionMap().checkCollisionAt(this, this.getX() + xDelta, this.getY() + yDelta);
				if (collidable == null) {
					this.setX(this.getX() + xDelta);
					this.setY(this.getY() + yDelta);
				}
			} catch (CollisionException e) {
				// Don't move
			}
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
	
	@Override
	public boolean isMovable(){
		return true;
	}

	@Override
	public void onCollision() {
		
	}
}
