package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.resource.ResourceManager;

public class SpriteManager extends ResourceManager {

	private Animator animator;
	
	public SpriteManager(int updatesPerSecond){
		super();
		this.animator = new Animator(updatesPerSecond);
	}
	
	@Override
	public boolean loadResourceSet(String resourceSet) {
		SpriteSheet spriteSheet = new SpriteSheet(resourceSet, animator);
		spriteSheet.load();
		this.getResourceSets().put(resourceSet, spriteSheet);
		return false;
	}

	public void update() {
		animator.update();		
	}
}