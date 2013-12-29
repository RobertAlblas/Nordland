package robertalblas.nordland.init.configuration;

public abstract class InitConfiguration {
	private String configurationType;

	public InitConfiguration(String configurationType){
		this.configurationType = configurationType;
	}
	
	public String getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(String configurationType) {
		this.configurationType = configurationType;
	}
	
	
}
