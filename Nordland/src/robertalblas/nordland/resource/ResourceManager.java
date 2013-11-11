package robertalblas.nordland.resource;

import java.util.HashMap;

public abstract class ResourceManager {
	
	private HashMap<String, ResourceSet> resourceSets;
	
	public ResourceManager(){
		this.setResourceSets(new HashMap<String, ResourceSet>());
	}
	
	public ResourceSet getResourceSet(String resourceSet){
		return getResourceSets().get(resourceSet);
		
	}
	
	public abstract boolean loadResourceSet(String resourceSet);
	public abstract void unloadResourceSet(String resourceSet);
	public abstract void unloadAllResources();

	public HashMap<String, ResourceSet> getResourceSets() {
		return resourceSets;
	}

	public void setResourceSets(HashMap<String, ResourceSet> resourceSets) {
		this.resourceSets = resourceSets;
	}
}
