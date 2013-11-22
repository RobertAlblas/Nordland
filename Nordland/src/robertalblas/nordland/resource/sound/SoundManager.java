package robertalblas.nordland.resource.sound;

import robertalblas.nordland.resource.ResourceManager;

public class SoundManager extends ResourceManager {

	@Override
	public void loadResourceSet(String resourceSet) {
		SoundSet soundSet = new SoundSet(resourceSet);
		soundSet.load();
		this.getResourceSets().put(resourceSet, soundSet);
	}
}
