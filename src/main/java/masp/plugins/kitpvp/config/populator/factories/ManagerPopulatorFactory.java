package masp.plugins.kitpvp.config.populator.factories;

import masp.plugins.kitpvp.config.populator.KitPopulator;
import masp.plugins.kitpvp.config.populator.KitPopulatorFactory;
import masp.plugins.kitpvp.config.populator.managers.FactoryConfigManager;
import masp.plugins.kitpvp.config.populator.populators.BasicKitPopulator;
import masp.plugins.kitpvp.kit.kits.effects.KitEffect;
import masp.plugins.kitpvp.kit.valid.KitValidator;

import org.bukkit.configuration.ConfigurationSection;

public class ManagerPopulatorFactory implements KitPopulatorFactory {

	private FactoryConfigManager<KitValidator> valManager;
	private FactoryConfigManager<KitEffect> effManager;

	public ManagerPopulatorFactory(FactoryConfigManager<KitValidator> valManager, FactoryConfigManager<KitEffect> effManager) {
		this.valManager = valManager;
		this.effManager = effManager;
	}

	@Override
	public KitPopulator createPopulator(ConfigurationSection section) {
		return new BasicKitPopulator(valManager, effManager, section);
	}

}
