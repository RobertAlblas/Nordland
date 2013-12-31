package robertalblas.nordland.exception;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.collision.Collidable;

public class CollisionException extends RuntimeException {

	private static final long serialVersionUID = -2837187706129833317L;
	private final List<Collidable> collidables;

	public CollisionException(Collidable collidable, String message) {
		super(message);
		collidables = new ArrayList<Collidable>();
		collidables.add(collidable);
	}
	
	public CollisionException(List<Collidable> collisions, String message) {
		super(message);
		this.collidables = collisions;
	}

	public List<Collidable> getCollidables(){
		return this.collidables;
	}
}
