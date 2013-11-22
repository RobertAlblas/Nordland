package robertalblas.nordland.resource.sound.loader;

import java.util.HashMap;

import robertalblas.nordland.exception.FiletypeNotSupportedException;
import robertalblas.nordland.resource.sound.Sound;

public class SoundLoader {

	private static final HashMap<String, SoundLoaderStrategy> STRATEGY_MAPPING;
	static {
		STRATEGY_MAPPING = new HashMap<String, SoundLoaderStrategy>();
		STRATEGY_MAPPING.put("wav", new WavSoundLoaderStrategy());
	}

	public SoundLoader() {

	}

	public Sound loadSound(String filename)
			throws FiletypeNotSupportedException {
		String filetype = getFiletypeFromFilePath(filename);
		String name = getNameFromFilePath(filename);

		SoundLoaderStrategy soundLoader = STRATEGY_MAPPING.get(filetype);

		if (soundLoader == null) {
			throw new FiletypeNotSupportedException("Filetype \"" + filetype
					+ "\" not supported (yet?).");
		}

		return soundLoader.loadSound(name, filename);
	}

	private String getNameFromFilePath(String filename) {
		if (filename == null) {
			return null;
		}
		
		int index = filename.lastIndexOf("/");
		filename = filename.substring(index + 1);

		int pos = filename.lastIndexOf(".");
		if (pos == -1) {
			return filename;
		}

		return filename.substring(0, pos);
	}

	private String getFiletypeFromFilePath(String path) {
		String extension = "";

		int i = path.lastIndexOf('.');
		if (i > 0) {
			extension = path.substring(i + 1);
		}

		return extension;
	}
}
