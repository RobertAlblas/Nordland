package robertalblas.nordland.world;

import robertalblas.nordland.exception.ResourceNotFoundException;

public interface WorldFactory {
	public World createWorld() throws ResourceNotFoundException;

}
