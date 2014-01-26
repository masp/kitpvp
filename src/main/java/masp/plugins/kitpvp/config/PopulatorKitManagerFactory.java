package masp.plugins.kitpvp.config;

import masp.plugins.kitpvp.config.populator.KitPopulator;
import masp.plugins.kitpvp.config.populator.KitPopulatorFactory;
import masp.plugins.kitpvp.kit.kits.Kit;
import masp.plugins.kitpvp.kit.kits.KitManager;
import masp.plugins.kitpvp.kit.kits.managers.BasicKitManager;

import org.bukkit.configuration.ConfigurationSection;

public class PopulatorKitManagerFactory implements KitManagerFactory {

	private KitPopulatorFactory kPopFactory;

	public PopulatorKitManagerFactory(KitPopulatorFactory factory) {
		this.kPopFactory = factory;
	}

	@Override
	public KitManager createKitManager(ConfigurationSection config) throws ConfigException {
		BasicKitManager kManager = new BasicKitManager();	
		for (String kitName : config.getKeys(false)) {	
			KitPopulator populator = kPopFactory.createPopulator(config.getConfigurationSection(kitName));
			Kit kit = populator.getKit();
			kManager.addKit(populator.getIdentity(), kit, kit.getInfo().getDisplayName().toLowerCase().replaceAll("\\s", ""), kitName.toLowerCase());
		}
		return kManager;
	}

}
