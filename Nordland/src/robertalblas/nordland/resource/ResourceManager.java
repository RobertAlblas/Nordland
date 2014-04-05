package robertalblas.nordland.resource;

import java.util.HashMap;

import robertalblas.nordland.exception.ResourceNotFoundException;
import robertalblas.nordland.exception.UnknownEntityTypeException;
import robertalblas.nordland.exception.XMLParseException;

public abstract class ResourceManager {

	private HashMap<String, ResourceSet> resourceSets;

	public ResourceManager() {
		this.setResourceSets(new HashMap<String, ResourceSet>());
	}

	public ResourceSet getResourceSet(String name) throws ResourceNotFoundException {
		ResourceSet resourceSet =  getResourceSets().get(name);
		if(resourceSet == null){
			throw new ResourceNotFoundException("Unable to load resource: " + resourceSet);
		}
		
		return resourceSet.clone();
	}

	public void unloadResourceSet(String resourceSet) {
		getResourceSets().get(resourceSet).unload();
		getResourceSets().remove(resourceSet);
	}

	public void unloadAllResources() {
		for (String rs : getResourceSets().keySet()) {
			getResourceSets().get(rs).unload();
		}
		getResourceSets().clear();
	}

	public HashMap<String, ResourceSet> getResourceSets() {
		return resourceSets;
	}

	public void setResourceSets(HashMap<String, ResourceSet> resourceSets) {
		this.resourceSets = resourceSets;
	}
	
	public abstract void loadResourceSet(String resourceSet) throws XMLParseException, NumberFormatException, UnknownEntityTypeException, ResourceNotFoundException;
}
