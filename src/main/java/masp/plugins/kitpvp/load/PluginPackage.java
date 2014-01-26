package masp.plugins.kitpvp.load;

import masp.plugins.kitpvp.config.populator.managers.FactoryConfigManager;
import masp.plugins.kitpvp.kit.kits.effects.KitEffect;
import masp.plugins.kitpvp.kit.valid.KitValidator;
import masp.plugins.kitpvp.player.factory.KitPlayerFactory;

public class PluginPackage {

	private KitPlayerFactory plFact;
	private FactoryConfigManager<KitValidator> valFact;
	private FactoryConfigManager<KitEffect> effFact;

	public PluginPackage(KitPlayerFactory plFact,
			FactoryConfigManager<KitValidator> valFact,
			FactoryConfigManager<KitEffect> effFact) {
		this.plFact = plFact;
		this.valFact = valFact;
		this.effFact = effFact;
	}

	public KitPlayerFactory getPlayerFactory() {
		return plFact;
	}

	public FactoryConfigManager<KitValidator> getValidatorsFactory() {
		return valFact;
	}

	public FactoryConfigManager<KitEffect> getEffectsFactory() {
		return effFact;
	}
}
