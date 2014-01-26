package masp.plugins.kitpvp.kit.kits;

import java.util.Set;

import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.identity.KitIdentityManager;

public interface KitManager {

	public Set<KitIdentity> getAllKits();

	public Kit getKit(KitIdentity identity);

	public KitIdentityManager getIdentities();

}
