package robertalblas.nordland.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class ResourceSet {
	private List<Resource> resources;
	private final String file;

	public ResourceSet(String file) {
		this.file = file;
		this.setResources(new ArrayList<Resource>());
	}

	public abstract void load();

	public Resource getResource(String resourceName) {
		for (Resource r : getResources()) {
			if (r.getName().equals(resourceName)) {
				return r;
			}
		}
		return null;
	}

	public void unload() {
		getResources().clear();
		setResources(null);
	}

	public String getFile() {
		return file;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}
