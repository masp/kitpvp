package masp.plugins.kitpvp.kit.identity.managers;

import java.util.ArrayList;
import java.util.List;

import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.identity.identities.EmptyIdentity;

public final class KitResolver {

	private List<String> identifiers;
	private KitIdentity identity;

	public KitResolver(KitIdentity identity, String... identifiers) {
		this.identity = identity;
		this.identifiers = new ArrayList<String>();
		for (String ident : identifiers) {
			this.identifiers.add(ident);
		}
	}

	public KitIdentity resolve(String reqName) {
		for (String ident : identifiers) {
			if (ident.equalsIgnoreCase(reqName)) {
				return identity;
			}
		}
		// Return nothing if didn't work
		return new EmptyIdentity();
	}
}
