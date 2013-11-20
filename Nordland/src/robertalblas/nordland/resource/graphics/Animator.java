package robertalblas.nordland.resource.graphics;

import java.util.ArrayList;
import java.util.List;

public class Animator {

	private int updatesPerSecond;
	private int animationCounter;

	private List<Animation> animations;

	public Animator(int updatesPerSecond) {
		this.animationCounter = 0;
		this.updatesPerSecond = updatesPerSecond;
		this.animations = new ArrayList<Animation>();
	}

	public void addAnimation(Animation animation) {
		animations.add(animation);
	}

	public void update(){
		if (animationCounter == Integer.MAX_VALUE) {
			animationCounter = 0;
		} else {
			animationCounter++;
		}
		
		for(Animation animation : animations){
			if (animationCounter
					% (updatesPerSecond / animation.getAmountOfSpritesPerSecond()) == 0) {
				animation.nextSprite();
			}
		}
	}
}
