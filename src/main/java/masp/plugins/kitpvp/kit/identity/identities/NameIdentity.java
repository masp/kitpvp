package masp.plugins.kitpvp.kit.identity.identities;

import masp.plugins.kitpvp.kit.identity.KitIdentity;

public class NameIdentity implements KitIdentity {

	private String name;

	public NameIdentity(String name) {
		this.name = name;
	}

	@Override
	public boolean is(KitIdentity identity) {
		return equals(identity);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NameIdentity) {
			return ((NameIdentity) o).name.equals(this.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}

}
