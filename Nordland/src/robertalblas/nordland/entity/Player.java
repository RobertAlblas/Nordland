package robertalblas.nordland.entity;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.collision.Collidable;
import robertalblas.nordland.exception.CollisionException;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.graphics.Animation;
import robertalblas.nordland.resource.graphics.Drawable;
import robertalblas.nordland.resource.graphics.Sprite;
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
		for (InputAction inputAction : inputActions) {
			
			int xDelta = 0;
			int yDelta = 0;
			
			String action = inputAction.getActionType();
			if (action.equals("up")) {
				yDelta--;
				this.direction = Direction.NORTH;
				this.isMoving = true;
			}
			if (action.equals("down")) {
				yDelta++;
				this.direction = Direction.SOUTH;
				this.isMoving = true;
			}
			if (action.equals("left")) {
				xDelta--;
				this.direction = Direction.EAST;
				this.isMoving = true;
			}
			if (action.equals("right")) {
				xDelta++;
				this.direction = Direction.WEST;
				this.isMoving = true;
			}
			
			if(xDelta != 0 || yDelta != 0){
				Collidable collidable;
				try {
					collidable = getWorld().getCollisionMap().checkCollisionAt(this, this.getX() + xDelta, this.getY() + yDelta);
					if(collidable == null){
						this.setX(this.getX() + xDelta);
						this.setY(this.getY() + yDelta);
					}
				} catch (CollisionException e) {
					// Don't move
				}
				
			}
		}
	}

	private void updateSprite() {
		switch (this.direction) {
		case NORTH:
			this.setCurrentSprite("back");
			break;
		case SOUTH:
			this.setCurrentSprite("front");
			break;
		case EAST:
			this.setCurrentSprite("left");
			break;
		case WEST:
			this.setCurrentSprite("right");
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

	@Override
	public Drawable getDrawable() {
		return (Drawable) getSpriteSheet().getResource(getCurrentSprite());
	}

	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for(Resource r :  getSpriteSheet().getResources()){
			if(r instanceof Sprite){
				drawables.add((Drawable)r);
			}
			else if(r instanceof Animation){
				for(Sprite s: ((Animation)r).getSprites()){
					drawables.add(s);
				}
			}
		}
		return drawables;
	}
}
