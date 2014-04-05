package robertalblas.fiore.entity;

import java.util.List;

import robertalblas.nordland.entity.Player;
import robertalblas.nordland.input.InputAction;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.resource.sound.SoundSet;
import robertalblas.nordland.world.World;

public class Fiore extends Player {

	public Fiore(World world, SpriteSet spriteSheet, SoundSet soundSet, int x, int y) {
		super(world, spriteSheet, soundSet, x, y);
	}

	@Override
	public void update(List<InputAction> inputActions) {
		super.update(inputActions);
	}

}
