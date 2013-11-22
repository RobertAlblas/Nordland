package robertalblas.nordland.exception;

import robertalblas.nordland.collision.Collidable;

public class CollisionException extends RuntimeException {

	private static final long serialVersionUID = -2837187706129833317L;
	private final Collidable collidable;

	public CollisionException(Collidable collidable, String message) {
		super(message);
		this.collidable = collidable;
	}
	
	public Collidable getCollidable(){
		return this.collidable;
	}
}
