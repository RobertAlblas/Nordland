package robertalblas.nordland.init;

import robertalblas.nordland.Nordland;
import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.init.configuration.InitConfiguration;

public interface InitStrategy {
	public void init(Nordland nordland, InitConfiguration configuration) throws NumberFormatException, XMLParseException, UnknownEntityTypeException, ResourceNotFoundException;
}
