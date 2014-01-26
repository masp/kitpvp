package masp.plugins.kitpvp.player.defaults;

import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.identity.KitIdentityFactory;
import masp.plugins.kitpvp.kit.kits.Kit;
import masp.plugins.kitpvp.kit.kits.KitManager;
import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.PlayerAttribute;
import masp.plugins.kitpvp.player.defaults.persist.YamlPersister;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

public class KitAttributeDefault implements AttributeFactory<KitIdentity> {

	private KitIdentityFactory idFactory;
	private KitManager kManager;

	public KitAttributeDefault(KitManager kManager, KitIdentityFactory factory) {
		this.idFactory = factory;
		this.kManager = kManager;
	}

	@Override
	public PlayerAttribute<KitIdentity> create(final KitPlayer player, 
											   YamlPersister persister) {
		ConfigurationSection config = persister.getConfiguration(player.getName());

		String value = config.getString("kit.kit", "null");
		if ("null".equals(value)) {
			config.set("kit.kit", "empty");
			value = "empty";
		}

		KitIdentity identity = idFactory.createIdentity(value);
		Kit kit = kManager.getKit(identity);
		kit.renew(player);

		return new PlayerAttribute<KitIdentity>(player.getName(), identity, KitIdentity.class);
	}

	@Override
	public void persist(final KitPlayer player,
						PlayerAttribute<?> attribute, 
						YamlPersister persister) {
		if (attribute.isType(KitIdentity.class)) {
			KitIdentity value = (KitIdentity) attribute.get();
			Configuration config = persister.getConfiguration(player.getName());
			config.set("kit.kit", value.toString());
			kManager.getKit(value).suspend(player);
		} else {
			throw new IllegalArgumentException("Not allowed to pass a non-kit identity attribute to the KitIdentity factory method persist!");
		}
	}

}
