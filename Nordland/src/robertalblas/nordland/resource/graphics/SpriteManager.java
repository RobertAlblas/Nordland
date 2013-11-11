package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.resource.ResourceManager;

public class SpriteManager extends ResourceManager {

	@Override
	public boolean loadResourceSet(String resourceSet) {
		SpriteSheet spriteSheet = new SpriteSheet(resourceSet);
		spriteSheet.load();
		this.getResourceSets().put(resourceSet, spriteSheet);
		return false;
	}

	@Override
	public void unloadResourceSet(String resourceSet) {
		getResourceSets().get(resourceSet).unload();
		getResourceSets().remove(resourceSet);
	}

	@Override
	public void unloadAllResources() {
		for (String rs : getResourceSets().keySet()) {
			getResourceSets().get(rs).unload();
		}
		getResourceSets().clear();
	}
}