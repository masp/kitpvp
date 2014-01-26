package masp.plugins.kitpvp.kit.kits.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.identity.KitIdentityManager;
import masp.plugins.kitpvp.kit.identity.managers.KitResolver;
import masp.plugins.kitpvp.kit.identity.managers.ResolverKitIdentityManager;
import masp.plugins.kitpvp.kit.kits.Kit;
import masp.plugins.kitpvp.kit.kits.KitManager;
import masp.plugins.kitpvp.kit.kits.effects.EmptyKit;

import org.bukkit.event.Listener;

public class BasicKitManager implements KitManager, Listener {

	private ResolverKitIdentityManager kiMan;
	private Map<KitIdentity, Kit> kits;

	public BasicKitManager() {
		kits = new HashMap<KitIdentity, Kit>();

		this.kiMan = new ResolverKitIdentityManager();
	}

	public void addKit(KitIdentity identity, Kit kit, String... names) {
		kits.put(identity, kit);

		kiMan.addResolver(new KitResolver(identity, names));
	}

	@Override
	public Set<KitIdentity> getAllKits() {
		return kits.keySet();
	}

	@Override
	public Kit getKit(KitIdentity identity) {
		if (kits.containsKey(identity)) {
			return kits.get(identity);
		}
		return new EmptyKit();
	}

	@Override
	public KitIdentityManager getIdentities() {
		return kiMan;
	}
}
