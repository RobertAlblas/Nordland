package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.resource.ResourceManager;
import robertalblas.nordland.system.timer.TickTimerManager;

public class SpriteManager extends ResourceManager {

	private TickTimerManager tickTimerManager;

	public SpriteManager() throws NumberFormatException, XMLParseException, UnknownEntityTypeException {
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