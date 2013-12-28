package robertalblas.nordland.world.resourceworld;

import robertalblas.nordland.entity.Entity;
import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.resource.world.WorldData;
import robertalblas.nordland.resource.world.WorldResource;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.world.World;
import robertalblas.nordland.world.WorldFactory;

public class ResourceWorldFactory implements WorldFactory {

	private TickTimerManager tickTimerManager;
	private SpriteManager spriteManager;
	private SoundManager soundManager;
	private WorldData worldData;

	public ResourceWorldFactory(WorldResource worldResource) {
		tickTimerManager = worldResource.getTickTimerManager();
		spriteManager = worldResource.getSpriteManager();
		soundManager = worldResource.getSoundManager();
		worldData = worldResource.getWorldData();
	}

	@Override
	public World createWorld() throws ResourceNotFoundException {
		if (tickTimerManager == null) {
			throw new ResourceNotFoundException("Missing tickTimerManager");
		}
		if (spriteManager == null) {
			throw new ResourceNotFoundException("Missing spriteManager");
		}
		if (soundManager == null) {
			throw new ResourceNotFoundException("Missing soundManager");
		}
		if (worldData == null) {
			throw new ResourceNotFoundException("Missing worldData");
		}
		ResourceWorld resourceWorld = new ResourceWorld();
		resourceWorld.setTickTimerManager(tickTimerManager);
		resourceWorld.setSpriteManager(spriteManager);
		resourceWorld.setSoundManager(soundManager);
		resourceWorld.setCollisionMap(worldData.getCollisionMap());
		resourceWorld.setWidth(worldData.getWidth());
		resourceWorld.setHeight(worldData.getHeight());
		
		for(Entity e: worldData.getEntities()){
			e.setWorld(resourceWorld);
		}
		
		resourceWorld.setEntities(worldData.getEntities());
		resourceWorld.setPlayer(worldData.getPlayer());

		return resourceWorld;
	}

}
