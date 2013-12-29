package robertalblas.nordland.init;

import java.util.HashMap;

import robertalblas.nordland.Nordland;
import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.UnknownInitConfigurationException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.init.configuration.InitConfiguration;
import robertalblas.nordland.init.configuration.InitConfigurationDefault;

public class Initializer {
	private static final HashMap<String, InitStrategy> STRATEGY_MAPPING;
	static {
		STRATEGY_MAPPING = new HashMap<String, InitStrategy>();
		STRATEGY_MAPPING.put(null, new InitDefault());
		STRATEGY_MAPPING.put("default", new InitDefault());
	}

	public static void init(Nordland nordland) throws NumberFormatException, XMLParseException, UnknownEntityTypeException,
			ResourceNotFoundException {
		STRATEGY_MAPPING.get(null).init(nordland, new InitConfigurationDefault());
	}

	public static void init(Nordland nordland, InitConfiguration configuration) throws UnknownInitConfigurationException,
			NumberFormatException, XMLParseException, UnknownEntityTypeException, ResourceNotFoundException {
		if (STRATEGY_MAPPING.get(configuration.getConfigurationType()) == null) {
			throw new UnknownInitConfigurationException("Unknown configuration: " + configuration.getConfigurationType());
		} else {
			STRATEGY_MAPPING.get(configuration.getConfigurationType()).init(nordland, configuration);
		}
	}
}
