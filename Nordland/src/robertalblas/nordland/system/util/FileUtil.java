package robertalblas.nordland.system.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileUtil {
	
	public static File[] findFiles(final File directory, final String extension) {

		return directory.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(extension);
			}
		});

	}
}
