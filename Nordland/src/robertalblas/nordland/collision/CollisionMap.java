package robertalblas.nordland.collision;

public class CollisionMap {

	private Collidable[] collidables;
	private final int width;
	
	public CollisionMap(int width, int height) {
		this.collidables = new Collidable[width * height];
		this.width = width;
	}
	
	public Collidable getCollidableAt(int x, int y){
		return collidables[x + y * width];
	}
	
	public void setCollidableAt(int x, int y, Collidable collidable){
		collidables[x + y * width] = collidable;
	}

}
