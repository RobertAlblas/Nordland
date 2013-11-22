package robertalblas.nordland.resource.sound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.resource.ResourceSet;
import robertalblas.nordland.resource.sound.loader.SoundLoader;

public class SoundSet extends ResourceSet {

	public SoundSet(String file) {
		super(file);
	}

	@Override
	public void load() {
		SoundLoader soundLoader = new SoundLoader();
		
		"/sound/" + getFile()
		//for every sound in folder
			//do soundloader
			//add sound to resourceset
		
		
	}
	
	private List<String> getFilesInDirectory(String directory) {
		List<String> files = new ArrayList<String>();
		File directoryFile = new File(directory);
		
	    for (File fileEntry : directoryFile.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	            files.add(fileEntry.getName());
	        }
	    }
	    
	    return files;
	}
}
