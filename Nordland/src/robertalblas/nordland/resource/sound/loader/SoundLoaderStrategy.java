package robertalblas.nordland.resource.sound.loader;

import robertalblas.nordland.resource.sound.Sound;

public interface SoundLoaderStrategy {
	public Sound loadSound(String name, String filename);
}
