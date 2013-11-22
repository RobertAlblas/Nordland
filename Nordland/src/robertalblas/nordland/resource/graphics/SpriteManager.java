package robertalblas.nordland.resource.graphics;

import robertalblas.nordland.resource.ResourceManager;

public class SpriteManager extends ResourceManager {

	private Animator animator;
	
	public SpriteManager(int updatesPerSecond){
		super();
		this.animator = new Animator(updatesPerSecond);
	}
	
	@Override
	public void loadResourceSet(String resourceSet) {
		SpriteSet spriteSheet = new SpriteSet(resourceSet, animator);
		spriteSheet.load();
		this.getResourceSets().put(resourceSet, spriteSheet);
	}

	public void update() {
		animator.update();		
	}
}