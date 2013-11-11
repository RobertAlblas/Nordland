package robertalblas.nordland.resource;

public abstract class Resource {
	private final String name;
	
	public Resource(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
