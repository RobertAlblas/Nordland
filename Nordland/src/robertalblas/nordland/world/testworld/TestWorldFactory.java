package robertalblas.nordland.world.testworld;

import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.WorldFactory;

public class TestWorldFactory implements WorldFactory {

	private SpriteManager spriteManager;
	private TickTimerManager tickTimerManager;
	
	public TestWorldFactory(SpriteManager spriteManager, TickTimerManager tickTimerManager) {
		this.spriteManager = spriteManager;
		this.tickTimerManager = tickTimerManager;
	}

	@Override
	public World createWorld() throws ResourceNotFoundException {
		if(spriteManager == null){
			throw new ResourceNotFoundException("Spritemanager can not be null.");
		}
		if(tickTimerManager == null){
			throw new ResourceNotFoundException("Timetickmanager can not be null.");
		}
		return new TestWorld(spriteManager, tickTimerManager);
	}

}
