package masp.plugins.kitpvp.kit.identity.identities;

import masp.plugins.kitpvp.kit.identity.KitIdentity;

/**
 * @purpose
 * Represents a null-identity that will not equal anything
 * except another empty identity
 */
public final class EmptyIdentity implements KitIdentity {

	@Override
	public boolean is(KitIdentity identity) {
		return (identity instanceof EmptyIdentity);
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof EmptyIdentity);
	}

	@Override
	public int hashCode() {
		return 39;
	}

	@Override
	public String toString() {
		return "null";
	}

}
