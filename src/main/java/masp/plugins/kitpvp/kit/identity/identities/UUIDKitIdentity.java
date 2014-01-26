package masp.plugins.kitpvp.kit.identity.identities;

import java.util.UUID;

import masp.plugins.kitpvp.kit.identity.KitIdentity;

public final class UUIDKitIdentity implements KitIdentity {

	private UUID id;

	public UUIDKitIdentity() {
		id = UUID.randomUUID();
	}

	public UUID getUUID() {
		return id;
	}

	@Override
	public boolean is(KitIdentity identity) {
		if (identity instanceof UUIDKitIdentity) {
			return ((UUIDKitIdentity) identity).getUUID().equals(id);
		}
		return false;
	}
}
