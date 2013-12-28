package robertalblas.nordland.resource.world;

import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.system.timer.TickTimerManager;

public class WorldResource extends Resource{

	private SpriteManager spriteManager;
	private SoundManager soundManager;
	private TickTimerManager tickTimerManager;
	private WorldData worldData;
	
	public WorldResource(String name) {
		super(name);
	}

	public TickTimerManager getTickTimerManager() {
		return tickTimerManager;
	}

	public void setTickTimerManager(TickTimerManager tickTimerManager) {
		this.tickTimerManager = tickTimerManager;
	}

	public WorldData getWorldData() {
		return worldData;
	}

	public void setWorldData(WorldData worldData) {
		this.worldData = worldData;
	}

	public SpriteManager getSpriteManager() {
		return spriteManager;
	}

	public void setSpriteManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}

	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

}
