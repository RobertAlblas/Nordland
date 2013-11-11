package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.resource.ResourceManager;

public class SpriteManager extends ResourceManager {

	@Override
	public boolean loadResourceSet(String resourceSet) {
		SpriteSheet spriteSheet = new SpriteSheet(resourceSet);
		spriteSheet.load();
		this.resourceSets.put(resourceSet, spriteSheet);
		return false;
	}

	@Override
	public void unloadResourceSet(String resourceSet) {
		resourceSets.get(resourceSet).unload();
		resourceSets.remove(resourceSet);
	}

	@Override
	public void unloadAllResources() {
		for (String rs : resourceSets.keySet()) {
			resourceSets.get(rs).unload();
		}
		resourceSets.clear();
	}
}