package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.resource.ResourceManager;
import robertalblas.nordland.system.timer.TickTimerManager;

public class SpriteManager extends ResourceManager {

	private TickTimerManager tickTimerManager;
	
	public SpriteManager(){
		super();
	}
	
	@Override
	public void loadResourceSet(String resourceSet) {
		SpriteSet spriteSheet = new SpriteSet(resourceSet);
		spriteSheet.setTickTimerManager(tickTimerManager);
		spriteSheet.load();
		this.getResourceSets().put(resourceSet, spriteSheet);
	}

	public void setTickTimerManager(TickTimerManager tickTimerManager) {
		this.tickTimerManager = tickTimerManager;
	}
}