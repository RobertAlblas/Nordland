package robertalblas.nordland.world.testworld;

import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.resource.graphics.Animator;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.WorldFactory;

public class TestWorldFactory implements WorldFactory {

	private SpriteManager spriteManager;
	
	public TestWorldFactory(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
	}

	@Override
	public World createWorld() throws ResourceNotFoundException {
		if(spriteManager == null){
			throw new ResourceNotFoundException("Spritemanager can not be null.");
		}
		return new TestWorld(spriteManager);
	}

}
