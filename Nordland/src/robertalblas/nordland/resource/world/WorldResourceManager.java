package robertalblas.nordland.resource.world;

import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.resource.ResourceManager;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.system.timer.TickTimerManager;

public class WorldResourceManager extends ResourceManager {
	private TickTimerManager tickTimerManager;
	private SoundManager soundManager;
	private SpriteManager spriteManager;

	public WorldResourceManager(TickTimerManager tickTimerManager, SoundManager soundManager, SpriteManager spriteManager) {
		super();
		this.tickTimerManager = tickTimerManager;
		this.soundManager = soundManager;
		this.spriteManager = spriteManager;
	}

	@Override
	public void loadResourceSet(String resourceSet) throws XMLParseException, NumberFormatException, UnknownEntityTypeException {
		WorldResourceSet worldResourceSet = new WorldResourceSet(resourceSet, tickTimerManager, soundManager, spriteManager);
		worldResourceSet.load();
		this.getResourceSets().put(resourceSet, worldResourceSet);
	}

}
