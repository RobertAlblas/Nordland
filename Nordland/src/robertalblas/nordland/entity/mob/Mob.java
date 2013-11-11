package robertalblas.nordland.entity.mob;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.resource.graphics.SpriteSheet;

public abstract class Mob extends Entity {

	protected int dir = 0;
	protected boolean moving = false;	
	
	public Mob(SpriteSheet spriteSheet){
		super(spriteSheet);
	}

	public void move(int xChange, int yChange) {
		if(xChange != 0 && yChange != 0){
			move(xChange,0);
			move(0,yChange);
			return;
		}
		if (xChange > 0)
			dir = 1;
		if (xChange < 0)
			dir = 3;
		if (yChange > 0)
			dir = 2;
		if (yChange < 0)
			dir = 0;

		if (!collision(xChange, yChange)) {
			setX(getX() + xChange);
			setY(getY() + yChange);
		}
	}

	public void update() {

	}

	public void render() {

	}
}
