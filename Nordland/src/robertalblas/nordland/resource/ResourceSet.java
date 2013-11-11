package robertalblas.nordland.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class ResourceSet {
	protected List<Resource> resources;
	protected final String file;
	
	public ResourceSet(String file){
		this.file = file;
		this.resources = new ArrayList<Resource>();
	}
	
	public abstract void load();
	
	public Resource getResource(String resourceName){
		for(Resource r : resources){
			if(r.getName().equals(resourceName)){
				return r;
			}
		}
		return null;
	}
	
	public abstract void unload();
}
