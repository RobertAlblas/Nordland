package robertalblas.fiore;

import robertalblas.fiore.initconfiguration.Initializer;
import robertalblas.nordland.Nordland;
import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.UnknownInitConfigurationException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.init.InitializerTemplate;
import robertalblas.nordland.init.configuration.InitConfigurationDefault;

public class FioreGame {
	private Nordland kernel;

	public FioreGame() {
		kernel = null;

		try {
			InitConfigurationDefault config = new InitConfigurationDefault();
			config.setWorld("world");
			config.setWorldSet("world");
			config.setWindowTitle("Mario");
			InitializerTemplate init = new Initializer();
			kernel = new Nordland(init, config);
		} catch (NumberFormatException | XMLParseException | UnknownEntityTypeException | ResourceNotFoundException
				| UnknownInitConfigurationException e) {
			e.printStackTrace();
		}

		kernel.start();
	}

	public static void main(String[] args) {
		new FioreGame();
	}
}
