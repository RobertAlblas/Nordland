package robertalblas.nordland.resource.sound.loader;

import robertalblas.nordland.resource.sound.Sound;
import robertalblas.nordland.resource.sound.WavSound;

public class WavSoundLoaderStrategy implements SoundLoaderStrategy{

	@Override
	public Sound loadSound(String name, String filename) {
		return new WavSound(filename, filename);
	}

}
