package masp.plugins.kitpvp.kit.identity.managers;

import java.util.ArrayList;
import java.util.List;

import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.identity.KitIdentityManager;
import masp.plugins.kitpvp.kit.identity.identities.EmptyIdentity;

public final class ResolverKitIdentityManager implements KitIdentityManager {

	private List<KitResolver> resolvers;

	public ResolverKitIdentityManager() {
		this.resolvers = new ArrayList<KitResolver>();
	}

	@Override
	public KitIdentity getIdentifier(String identifier) {
		for (KitResolver resolver : resolvers) {
			KitIdentity identity = resolver.resolve(identifier);
			if (!identity.is(new EmptyIdentity())) {
				return resolver.resolve(identifier);	
			}
		}
		return new EmptyIdentity();
	}

	public void addResolver(KitResolver resolver) {
		resolvers.add(resolver);
	}

}
