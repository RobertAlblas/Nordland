package robertalblas.nordland.resource.world;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.entity.Player;

public class WorldData {
	private List<Entity> entities;
	private Player player;
	private int width, height;
	private CollisionMap collisionMap;
	
	public WorldData(){
		this.entities = new ArrayList<Entity>();
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public CollisionMap getCollisionMap() {
		return collisionMap;
	}

	public void setCollisionMap(CollisionMap collisionMap) {
		this.collisionMap = collisionMap;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
