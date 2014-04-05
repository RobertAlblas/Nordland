package robertalblas.nordland.resource;

public abstract class Resource implements Cloneable {

	private final String name;

	public Resource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Resource getClone() {
		try {
			return (Resource) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
