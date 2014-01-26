package masp.plugins.kitpvp.kit.identity;

import masp.plugins.kitpvp.kit.identity.identities.EmptyIdentity;
import masp.plugins.kitpvp.kit.identity.identities.NameIdentity;

public final class KitIdentityFactory {

	public KitIdentity createIdentity(String value) {
		if ("empty".equals(value)) {
			return new EmptyIdentity();
		}
		return new NameIdentity(value);
	}

}
