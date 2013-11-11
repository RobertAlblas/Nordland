package robertalblas.nordland.resource;

import java.util.HashMap;

public abstract class ResourceManager {
	
	protected HashMap<String, ResourceSet> resourceSets;
	
	public ResourceManager(){
		this.resourceSets = new HashMap<String, ResourceSet>();
	}
	
	public ResourceSet getResourceSet(String resourceSet){
		return resourceSets.get(resourceSet);
		
	}
	
	public abstract boolean loadResourceSet(String resourceSet);
	public abstract void unloadResourceSet(String resourceSet);
	public abstract void unloadAllResources();
}
