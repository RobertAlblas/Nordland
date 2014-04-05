package robertalblas.fiore.initconfiguration;

import robertalblas.fiore.entity.loader.FioreFactory;
import robertalblas.nordland.entity.loader.EntityLoader;
import robertalblas.nordland.init.InitializerDefault;
import robertalblas.nordland.init.configuration.InitConfiguration;

public class Initializer extends InitializerDefault {

	@Override
	public void addEntityFactories(InitConfiguration configuration) {
		super.addEntityFactories(configuration);
		EntityLoader.addFactory("player", new FioreFactory());
	}

}
