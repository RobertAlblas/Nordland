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
import robertalblas.nordland.resource.graphics.SpriteSheet;
import robertalblas.nordland.util.log.Logger;
import robertalblas.nordland.util.log.LoggerManager;
import robertalblas.nordland.window.Screen;
import robertalblas.nordland.world.World;

public class Player implements Entity, Collidable {

	private int x, y;
	private int width, height;
	private SpriteSheet spriteSheet;
	private String currentSprite;
	private boolean isMoving;
	private Direction direction;
	private World world;

	public Player(World world, SpriteSheet spriteSheet, int x, int y) {
		this.world = world;
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
					collidable = world.getCollisionMap().checkCollisionAt(this, this.x + xDelta, this.y + yDelta);
					if(collidable == null){
						x += xDelta;
						y += yDelta;
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

	@Override
	public void onCollision() {
		LoggerManager.getInstance().getDefaultLogger().log("Collision", Logger.LOGTYPE_DEBUG);
	}

	@Override
	public Drawable getDrawable() {
		return (Drawable) spriteSheet.getResource(currentSprite);
	}

	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for(Resource r :  spriteSheet.getResources()){
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

	@Override
	public boolean isMovable() {
		return true;
	}

}
