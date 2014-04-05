package robertalblas.nordland.entity;

public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST,
	NONE;
	
	public Direction getOppositeDirection(Direction d){
		if(d == NORTH){
			return SOUTH;
		}
		if(d == SOUTH){
			return NORTH;
		}
		if(d == EAST){
			return WEST;
		}
		if(d == WEST){
			return EAST;
		}
		
		return null;
	}
}
